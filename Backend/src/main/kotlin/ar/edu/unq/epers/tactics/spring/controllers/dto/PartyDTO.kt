package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.modelo.Party


data class PartyDTO(var id:Long?, var nombre:String, var imagenURL:String, var aventureros:List<AventureroDTO>){

    companion object {

        fun desdeModelo(party: Party): PartyDTO {
            val partyDTO =
                PartyDTO(
                    id = party.id,
                    nombre = party.nombre,
                    imagenURL = party.imagen,
                    aventureros = party.aventureros
                        .map { av -> AventureroDTO.desdeModelo(av) }
            )
            return partyDTO
        }
    }

    fun aModelo(): Party {
        val party = Party(this.nombre, this.imagenURL)
        party.id = this.id
        this.aventureros.map { avDTO -> avDTO.aModelo() }.toMutableList().forEach { aventurero -> party.agregarAventurero(aventurero) }
        return party
    }

    fun actualizarModelo(party: Party) {
        party.id = id
        party.imagen = imagenURL
        party.nombre = nombre
        party.quitarAventureros()
        aventureros.map { avDto -> avDto.aModelo() }.toMutableList().forEach { aventurero -> party.agregarAventurero(aventurero) }
    }
}