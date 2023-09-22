package ar.edu.unq.epers.tactics.spring.service.impl

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.persistencia.AventureroDAO
import ar.edu.unq.epers.tactics.spring.service.AventureroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AventureroServiceImpl(): AventureroService {

    @Autowired private lateinit var aventureroDAO: AventureroDAO

    override fun actualizar(aventurero: Aventurero): Aventurero {
        validateExists(aventurero.id)
        return aventureroDAO.save(aventurero)
    }

    override fun recuperar(idDelAventurero: Long): Aventurero {
        return aventureroDAO.findById(idDelAventurero).get()
    }

    override fun eliminar(aventurero: Aventurero) {
        validateExists(aventurero.id)
        aventureroDAO.delete(aventurero)
    }

    override fun guardarAventurero(aventurero : Aventurero) {
        aventureroDAO.save(aventurero)
    }

    private fun validateExists(idDelAventurero: Long?) {
        if( idDelAventurero == null || !aventureroDAO.findById(idDelAventurero).isPresent ) {
            throw NoSuchElementException("El aventurero dado debe estar en la base datos.")
        }
    }

    override fun eliminarTodos() {
        aventureroDAO.deleteAll()
    }
}