package ar.edu.unq.epers.tactics.spring.controllers

import ar.edu.unq.epers.tactics.spring.service.AventureroService
import ar.edu.unq.epers.tactics.spring.service.PartyService
import ar.edu.unq.epers.tactics.spring.controllers.dto.AventureroDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@ServiceREST
@RequestMapping("/party/{partyId}/adventurer")
class AdventurerControllerRest(@Autowired val aventureroService: AventureroService, @Autowired  val partyService: PartyService) {

    @GetMapping
    fun getAdventurers(@PathVariable partyId: Long):List<AventureroDTO> =
        partyService.allAventurerosEnParty(partyId).map { aventurero -> AventureroDTO.desdeModelo(aventurero) }

    @GetMapping("/{id}")
    fun getAdventurer(@PathVariable id: Long) = AventureroDTO.desdeModelo(aventureroService.recuperar(id))

    @PutMapping("/{id}")
    fun updateAdventurer(@PathVariable id: Long, @RequestBody adventurerData: AventureroDTO): AventureroDTO {
        val avent = aventureroService.recuperar(id)
        adventurerData.actualizarModelo(avent)
        return AventureroDTO.desdeModelo(aventureroService.actualizar(avent))
    }

    @PostMapping
    fun createAdventurer(@PathVariable partyId: Long, @RequestBody adventurerData: AventureroDTO): AventureroDTO {
        return AventureroDTO.desdeModelo(partyService.agregarAventureroAParty(partyId, adventurerData.aModelo()))
    }

    @DeleteMapping("/{id}")
    fun deleteAdventurer(@PathVariable id: Long):StatusResponse {
        var avent = aventureroService.recuperar(id)
        aventureroService.eliminar(avent)
        return StatusResponse(201)
    }

}

data class StatusResponse(var status:Int)