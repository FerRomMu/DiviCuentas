package ar.edu.unq.divicuentas.service.impl

import ar.edu.unq.divicuentas.modelo.Product
import ar.edu.unq.divicuentas.modelo.Restaurant
import ar.edu.unq.divicuentas.persistencia.IProductRepository
import ar.edu.unq.divicuentas.service.IProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService: IProductService {
    @Autowired
    lateinit var repository: IProductRepository

    override fun create(product: Product): Product {
        return repository.save(product)
    }

    override fun update(product: Product): Product {
        return repository.save(product)
    }

    override fun deleteById(productId: Long) {
        repository.deleteById(productId)
    }

    override fun getById(productId: Long): Product? {
        return repository.findById(productId).orElse(null)
    }

}