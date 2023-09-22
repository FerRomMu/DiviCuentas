package ar.edu.unq.epers.tactics.spring.modelo.tacticas

import ar.edu.unq.epers.tactics.spring.exceptions.InvalidValueException
import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Tactica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    lateinit var receptor: TipoDeReceptor
    lateinit var estadistica: TipoDeEstadistica
    lateinit var criterio: Criterio
    var valor: Double = 0.0
    lateinit var accion: Accion
    var prioridad: Int = 0

    constructor(receptor: TipoDeReceptor, estadistica: TipoDeEstadistica, criterio: Criterio, valor: Double, accion: Accion, prioridad: Int) {
        if(prioridad < 0) { throw InvalidValueException("La prioridad no puede ser negativa.")
        }
        this.receptor = receptor
        this.estadistica = estadistica
        this.criterio = criterio
        this.valor = valor
        this.accion = accion
        this.prioridad = prioridad
    }
    fun ejecutar(emisor: Aventurero, aliados: List<Aventurero>, enemigos: List<Aventurero>): Habilidad? {
        return when (receptor) {
            TipoDeReceptor.UNO_MISMO -> compararUnoMismo(emisor)
            TipoDeReceptor.ALIADO -> compararLista(aliados,emisor)
            TipoDeReceptor.ENEMIGO -> compararLista(enemigos,emisor)
        }
    }

    private fun compararUnoMismo(aventurero: Aventurero): Habilidad? {
        return when (estadistica) {
            TipoDeEstadistica.ARMADURA -> definirCriterio(aventurero.armadura(), aventurero)
            TipoDeEstadistica.VIDA -> definirCriterio(aventurero.vidaActual, aventurero)
            TipoDeEstadistica.MANA -> definirCriterio(aventurero.manaActual, aventurero)
            TipoDeEstadistica.VELOCIDAD -> definirCriterio(aventurero.velocidad(), aventurero)
            TipoDeEstadistica.DANIO_FISICO -> definirCriterio(aventurero.dañoF(), aventurero)
            TipoDeEstadistica.DANIO_MAGICO -> definirCriterio(aventurero.dañoM(), aventurero)
            TipoDeEstadistica.PRECISION_FISICA -> definirCriterio(aventurero.pres(), aventurero)
        }
    }

    private fun definirCriterio(valor1: Double, av: Aventurero): Habilidad? {
        return when (criterio) {
            Criterio.MAYOR_QUE -> if (valor1 > valor) { definirAccion(av, av) } else { null }
            Criterio.IGUAL -> if (valor1 == valor) { definirAccion(av, av) } else { null }
            Criterio.MENOR_QUE -> if (valor1 < valor) { definirAccion(av, av) } else { null }
        }
    }

    private fun compararLista(aventureros: List<Aventurero>, emisor: Aventurero): Habilidad? {
        return when (estadistica) {
            TipoDeEstadistica.ARMADURA -> definirCriterioArmadura(aventureros, emisor)
            TipoDeEstadistica.VIDA -> definirCriterioVida(aventureros, emisor)
            TipoDeEstadistica.MANA -> definirCriterioMana(aventureros, emisor)
            TipoDeEstadistica.VELOCIDAD -> definirCriterioVelocidad(aventureros, emisor)
            TipoDeEstadistica.DANIO_FISICO -> definirCriterioDañoF(aventureros, emisor)
            TipoDeEstadistica.DANIO_MAGICO -> definirCriterioDañoM(aventureros, emisor)
            TipoDeEstadistica.PRECISION_FISICA -> definirCriterioPrecision(aventureros, emisor)
        }
    }

    private fun definirCriterioArmadura(aventureros: List<Aventurero>, emisor: Aventurero): Habilidad? {
        return when (criterio) {
            Criterio.IGUAL -> definirAccion(emisor, aventureros.find { av -> av.armadura() == valor })
            Criterio.MAYOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.armadura() > valor })
            Criterio.MENOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.armadura() < valor })
        }
    }

    private fun definirCriterioVida(aventureros: List<Aventurero>, emisor: Aventurero): Habilidad? {
        return when (criterio) {
            Criterio.IGUAL -> definirAccion(emisor, aventureros.find { av -> av.vidaActual == valor })
            Criterio.MAYOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.vidaActual > valor })
            Criterio.MENOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.vidaActual < valor })
        }
    }

    private fun definirCriterioMana(aventureros: List<Aventurero>, emisor: Aventurero): Habilidad? {
        return when (criterio) {
            Criterio.IGUAL -> definirAccion(emisor, aventureros.find { av -> av.manaActual == valor })
            Criterio.MAYOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.manaActual > valor })
            Criterio.MENOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.manaActual < valor })
        }
    }

    private fun definirCriterioVelocidad(aventureros: List<Aventurero>, emisor: Aventurero): Habilidad? {
        return when (criterio) {
            Criterio.IGUAL -> definirAccion(emisor, aventureros.find { av -> av.velocidad() == valor })
            Criterio.MAYOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.velocidad() > valor })
            Criterio.MENOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.velocidad() < valor })
        }
    }

    private fun definirCriterioDañoF(aventureros: List<Aventurero>, emisor: Aventurero): Habilidad? {
        return when (criterio) {
            Criterio.IGUAL -> definirAccion(emisor, aventureros.find { av -> av.dañoF() == valor })
            Criterio.MAYOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.dañoF() > valor })
            Criterio.MENOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.dañoF() < valor })
        }
    }

    private fun definirCriterioDañoM(aventureros: List<Aventurero>, emisor: Aventurero): Habilidad? {
        return when (criterio) {
            Criterio.IGUAL -> definirAccion(emisor, aventureros.find { av -> av.dañoM() == valor })
            Criterio.MAYOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.dañoM() > valor })
            Criterio.MENOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.dañoM() < valor })
        }
    }

    private fun definirCriterioPrecision(aventureros: List<Aventurero>, emisor: Aventurero): Habilidad? {
        return when (criterio) {
            Criterio.IGUAL -> definirAccion(emisor, aventureros.find { av -> av.pres() == valor })
            Criterio.MAYOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.pres() > valor })
            Criterio.MENOR_QUE -> definirAccion(emisor, aventureros.find { av -> av.pres() < valor })
        }
    }

    private fun definirAccion(emisor: Aventurero, receptor: Aventurero?): Habilidad? {
        return when (receptor) {
            null -> null
            else -> when (accion) {
                Accion.ATAQUE_FISICO -> Atacar(emisor.dañoF(),emisor.pres(),receptor)
                Accion.DEFENDER -> Defender(emisor,receptor)
                Accion.MEDITAR -> Meditar(emisor)
                Accion.CURAR -> if (emisor.manaActual >= 5) { Curar(emisor.dañoM(),receptor) } else { null }
                Accion.ATAQUE_MAGICO ->  if (emisor.manaActual >= 5) { AtacarMagia(emisor.dañoM(),emisor.nivel,receptor) } else { null }
            }
        }
    }
}