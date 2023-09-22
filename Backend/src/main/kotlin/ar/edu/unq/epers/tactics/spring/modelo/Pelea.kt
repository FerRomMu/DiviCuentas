package ar.edu.unq.epers.tactics.spring.modelo

import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Habilidad
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Pelea(party: Party, partyEnemiga: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @ManyToOne
    val party = party
    val partyEnemiga = partyEnemiga
    var date: LocalDateTime = LocalDateTime.now()
    @OneToMany(cascade = [CascadeType.ALL])
    var habilidades : MutableList<Habilidad> = mutableListOf<Habilidad>()

    fun guardarHabilidad(habilidad: Habilidad) {
        this.habilidades.add(habilidad)
    }

    init {
        party.iniciarPelea()
    }
}