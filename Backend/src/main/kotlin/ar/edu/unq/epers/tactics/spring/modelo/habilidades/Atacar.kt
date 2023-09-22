package ar.edu.unq.epers.tactics.spring.modelo.habilidades

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.auxiliar.ModGet
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.auxiliar.RandomGet
import javax.persistence.Entity

@Entity
class Atacar(var danio: Double, var prec: Double, receptor: Aventurero) : Habilidad(receptor){
    @Transient
    var modificador: ModGet = RandomGet()
    override var tipo = "ataque"

    override fun aplicar() {
        if (receptor.defensores.isNotEmpty()) { receptor = receptor.defensores[0] }
        if (modificador.value() + prec >= receptor.armadura() + receptor.velocidad()/2){
            receptor.recibirDaño(danio)
        }
    }
}