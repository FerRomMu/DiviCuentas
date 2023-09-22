package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.modelo.Pelea
import java.time.LocalDateTime


data class PeleaDTO(var partyId:Long?, var date: LocalDateTime, var peleaId:Long?, var partyEnemiga:String){

    companion object {

        fun desdeModelo(pelea: Pelea): PeleaDTO {
            return PeleaDTO(
                partyId = pelea.party.id,
                date = pelea.date,
                peleaId = pelea.id,
                partyEnemiga = pelea.partyEnemiga
            )
        }
    }

}