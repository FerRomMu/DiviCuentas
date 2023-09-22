package ar.edu.unq.epers.tactics.spring.service.impl

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.Pelea
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Habilidad
import ar.edu.unq.epers.tactics.spring.persistencia.AventureroDAO
import ar.edu.unq.epers.tactics.spring.persistencia.PartyDAO
import ar.edu.unq.epers.tactics.spring.persistencia.PeleaDAO
import ar.edu.unq.epers.tactics.spring.service.PeleaService
import ar.edu.unq.epers.tactics.spring.modelo.PeleasPaginadas
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PeleaServiceImpl(): PeleaService {
    @Autowired private lateinit var peleaDAO: PeleaDAO
    @Autowired private lateinit var partyDAO: PartyDAO
    @Autowired private lateinit var aventureroDAO: AventureroDAO


    override fun iniciarPelea(idDeLaParty: Long, partyEnemiga: String): Pelea {
        val party = partyDAO.findById(idDeLaParty).orElse(null) ?: throw NoSuchElementException("Debe existir party con el id dado para iniciar la pelea.")
        if(party.estaEnPelea) { throw Exception("Una party en pelea no puede estar en otra.")}
        val pelea = Pelea(party, partyEnemiga)
        partyDAO.save(party)
        return peleaDAO.save(pelea)
    }

    override fun estaEnPelea(partyId: Long): Boolean {
        val party = partyDAO.findById(partyId).orElse(null) ?: throw NoSuchElementException("No existe party con el id dado.")
        return party.estaEnPelea
    }

    override fun actualizar(pelea: Pelea): Pelea {
        validateExists(pelea)
        partyDAO.save(pelea.party)
        peleaDAO.save(pelea)
        return pelea
    }

    private fun validateExists(pelea: Pelea) {
        if (pelea.id == null || !peleaDAO.findById(pelea.id!!).isPresent){ throw NoSuchElementException("No existe pelea con el id dado.")}
    }

    override fun recuperar(idDeLaPelea: Long): Pelea {
        return peleaDAO.findById(idDeLaPelea).orElse(null) ?: throw NoSuchElementException("No existe party con el id dado.")
    }

    override fun recuperarOrdenadas(partyId: Long, pagina: Int?): PeleasPaginadas {
        var peleas = peleaDAO.findAll().toList()
        peleas = peleas.filter { pelea -> pelea.party.id == partyId }
        var peleasP = PeleasPaginadas(peleas)
        peleasP.ordenarPeleas(pagina ?: 0)
        return peleasP
    }

    override fun resolverTurno(peleaId: Long, aventureroId: Long, enemigos: List<Aventurero>): Habilidad {
        val pelea = peleaDAO.findById(peleaId).orElse(null) ?: throw NoSuchElementException("No existe party con el id dado para resolver turno.")
        val aventurero = aventureroDAO.findById(aventureroId).orElse(null) ?: throw NoSuchElementException("No existe aventurero con el id dado.")
        val habilidad = aventurero.resolverTactica(enemigos)
        pelea.guardarHabilidad(habilidad)
        peleaDAO.save(pelea)
        return habilidad
    }

    override fun recibirHabilidad(peleaID:Long, aventureroId: Long, habilidad: Habilidad): Aventurero {
        val aventurero = aventureroDAO.findById(aventureroId).orElse(null) ?: throw NoSuchElementException("No existe aventurero para recibir habilidad.")
        if(aventurero.id != habilidad.receptor.id) { throw IllegalArgumentException ("El id debería ser del aventurero a recibir habilidad.")}
        aventurero.recibirHabilidad(habilidad)
        val pelea = this.recuperar(peleaID)
        pelea.guardarHabilidad(habilidad)
        peleaDAO.save(pelea)
        return aventureroDAO.save(aventurero)
    }

    override fun terminarPelea(idDeLaPelea: Long): Pelea {
        val pelea = recuperar(idDeLaPelea)
        val party = pelea.party
        party.terminarPelea(party.aventureros.any{ it.vidaActual > 0 })
        partyDAO.save(party)
        return pelea
    }

    override fun eliminarTodas(){
        peleaDAO.deleteAll()
    }
}