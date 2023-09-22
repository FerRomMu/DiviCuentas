package ar.edu.unq.epers.tactics.spring.modelo.habilidades

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.auxiliar.ModGet
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.auxiliar.RandomGet
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class AtacarMagia(val poderMagico: Double, val nivel: Int, receptor: Aventurero) : Habilidad(receptor){
    @Transient
    var modificador: ModGet = RandomGet()
    override var tipo = "ataqueMagico"

    override fun aplicar() {
        if (receptor.defensores.isNotEmpty()) { receptor = receptor.defensores[0] }
        if (modificador.value() + nivel >= receptor.velocidad()/2)
            receptor.recibirDaño(poderMagico)
    }
}