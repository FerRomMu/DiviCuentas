package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.modelo.PartyPaginadas

data class PartyPaginadasDTO(val parties:List<PartyDTO>, val total:Int){
    companion object{
        fun desdeModelo(partyPaginadas: PartyPaginadas): PartyPaginadasDTO {
            throw  NotImplementedError()
        }
    }
}