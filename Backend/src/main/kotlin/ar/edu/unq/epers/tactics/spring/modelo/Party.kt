package ar.edu.unq.epers.tactics.spring.modelo

import ar.edu.unq.epers.tactics.spring.exceptions.InvalidEmptyNameException
import ar.edu.unq.epers.tactics.spring.exceptions.UnmodifiedPartyException
import javax.persistence.*


@Entity
class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var nombre: String = ""
    var imagen: String = ""
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var aventureros:MutableList<Aventurero> = mutableListOf()
    var estaEnPelea = false
    var victorias = 0
    var derrotas = 0

    fun agregarAventurero(aventurero: Aventurero) {
        validateCanAddMember(aventurero)
        aventureros.add(aventurero)
        aventurero.setterParty(this)
    }

    fun quitarAventureros() {
        aventureros.clear()
    }

    private fun validateCanAddMember(aventurero: Aventurero){
        if (aventureros.size >= 5) { throw UnmodifiedPartyException("La party ya tiene 5 miembros.") }
        if (aventurero.party != null && aventurero.party!!.id == this.id) { throw UnmodifiedPartyException("La party ya tiene a este miembro.") }
    }

    fun terminarPelea(gano: Boolean) {
        estaEnPelea = false
        for (av in aventureros){
            av.resetAventurero()
        }
        if(gano) { victorias++ } else { derrotas++ }
    }

    fun iniciarPelea() {
        estaEnPelea = true
    }

    fun poder(): Double {
        return aventureros.map { it.dañoF() }.sum() +
                aventureros.map { it.dañoM() }.sum() +
                aventureros.map { it.pres() }.sum()
    }

    constructor(nombre: String, imagen: String){
        validateData(nombre, imagen)
        this.nombre = nombre
        this.imagen = imagen
    }

    private fun validateData(nombre: String, imagen: String) {
        if(nombre == "") { throw InvalidEmptyNameException("Una party debe tener nombre.") }
        if(imagen == "") { throw InvalidEmptyNameException("Una party debe tener imagen.") }
    }
}