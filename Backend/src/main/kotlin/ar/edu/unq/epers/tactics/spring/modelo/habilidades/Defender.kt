package ar.edu.unq.epers.tactics.spring.modelo.habilidades

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class Defender(@OneToOne val emisor: Aventurero, receptor: Aventurero): Habilidad(receptor) {
    override var tipo = "defensa"

    override fun aplicar(){
        emisor.defendido = receptor
        receptor.addDefensor(emisor)
    }
}