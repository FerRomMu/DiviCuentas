package ar.edu.unq.epers.tactics.spring.service.impl

import ar.edu.unq.epers.tactics.spring.exceptions.UnmodifiedPartyException
import ar.edu.unq.epers.tactics.spring.persistencia.PartyDAO
import ar.edu.unq.epers.tactics.spring.service.PartyService
import ar.edu.unq.epers.tactics.spring.modelo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PartyServiceImpl() : PartyService {
    @Autowired private lateinit var partyDAO: PartyDAO

    override fun crear(party: Party): Party {
        return partyDAO.save(party)
    }

    override fun actualizar(party: Party): Party {
        validateExists(party.id)
        return partyDAO.save(party)
    }

    override fun recuperar(idDeLaParty: Long): Party {
        return partyDAO.findById(idDeLaParty).get()
    }

    override fun recuperarTodas(): List<Party> {
        return partyDAO.findAll().toList().sortedBy { it.nombre }
    }

    override fun agregarAventureroAParty(idDeLaParty: Long, aventurero: Aventurero): Aventurero {
        validateExists(idDeLaParty)
        val party = partyDAO.findById(idDeLaParty).get()
        try {
            party.agregarAventurero(aventurero)
        } catch(e: UnmodifiedPartyException) {
            throw Exception(e.message)
        }
        partyDAO.save(party)
        return aventurero
    }

    override fun recuperarOrdenadas(orden: Orden, direccion: Direccion, pagina: Int?): PartyPaginadas {
        val parties = recuperarTodas()
        val partyP = PartyPaginadas(parties)
        partyP.ordenarParties(orden,direccion,pagina)
        return partyP
    }

    override fun allAventurerosEnParty(idDeLaParty: Long): List<Aventurero> {
        val party = this.recuperar(idDeLaParty)
        return party.aventureros
    }

    private fun validateExists(idDeLaParty: Long?) {
        if( idDeLaParty == null || !partyDAO.findById(idDeLaParty).isPresent ) {
            throw NoSuchElementException("La party dada debe estar en la base datos.")
        }
    }

    override fun eliminarTodas() {
        partyDAO.deleteAll()
    }
}