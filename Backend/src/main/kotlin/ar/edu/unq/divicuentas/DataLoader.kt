package ar.edu.unq.divicuentas

import ar.edu.unq.divicuentas.persistencia.IProductRepository
import ar.edu.unq.divicuentas.service.impl.DataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(private val dataService: DataService) : CommandLineRunner {
    @Autowired private lateinit var productRepository: IProductRepository
    override fun run(vararg args: String?) {
        if (!datosInicialesYaCreados()) {
            dataService.crearSetDeDatosIniciales()
        }
    }

    private fun datosInicialesYaCreados(): Boolean {
        val count = productRepository.count()
        return count > 0
    }
}