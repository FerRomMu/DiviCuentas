package ar.edu.unq.epers.tactics.spring.modelo.habilidades

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import javax.persistence.Entity

@Entity
class Curar(val curacion: Double, receptor: Aventurero) : Habilidad(receptor) {
    override var tipo = "curacion"

    override fun aplicar(){
        receptor.curar(curacion)
    }
}