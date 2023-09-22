package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.modelo.PeleasPaginadas


data class PeleasPaginadasDTO(val peleas:List<PeleaDTO>, val total:Int){
    companion object{
        fun desdeModelo(peleasPaginadas: PeleasPaginadas): PeleasPaginadasDTO {
            throw  NotImplementedError()
        }
    }
}