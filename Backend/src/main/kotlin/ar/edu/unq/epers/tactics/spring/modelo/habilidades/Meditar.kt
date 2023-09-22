package ar.edu.unq.epers.tactics.spring.modelo.habilidades

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import javax.persistence.Entity

@Entity
class Meditar(receptor: Aventurero): Habilidad(receptor) {
    override var tipo = "meditacion"

    override fun aplicar() {
        receptor.modificarMana(receptor.nivel)
    }
}