package ar.edu.unq.epers.tactics.spring.modelo.habilidades

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import javax.persistence.Entity

@Entity
class HabilidadNula(receptor: Aventurero): Habilidad(receptor) {
    override var tipo = "nula"
    override fun aplicar() {}
}