package ar.edu.unq.epers.tactics.spring.modelo

import ar.edu.unq.epers.tactics.spring.exceptions.BadAtributeException
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Atributo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var fuerza: Double = 1.0
    var destreza: Double = 1.0
    var constitucion: Double = 1.0
    var inteligencia: Double = 1.0

    fun setAtributes(fuerza: Double, destreza: Double, constitucion: Double, inteligencia: Double) {
        validateAtribute(listOf(fuerza,destreza,constitucion,inteligencia))
        this.fuerza = fuerza
        this.destreza = destreza
        this.constitucion = constitucion
        this.inteligencia = inteligencia
    }

    fun validateAtribute (atributos: List<Double>){
        if (atributos.any { it < 1.0 }){ throw BadAtributeException("Los atributos no pueden tener valor negativo.") }
        if (atributos.any { it > 100.0 }){ throw BadAtributeException("Los atributos no pueden tener valor negativo.") }
    }
}