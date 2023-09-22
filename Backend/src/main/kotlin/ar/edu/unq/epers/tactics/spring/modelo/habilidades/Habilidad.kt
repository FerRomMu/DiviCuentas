package ar.edu.unq.epers.tactics.spring.modelo.habilidades
import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    open lateinit var tipo: String
    @ManyToOne
    lateinit var receptor: Aventurero

    constructor(receptor: Aventurero){
        this.receptor = receptor
    }

    abstract fun aplicar()
}