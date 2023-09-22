package ar.edu.unq.epers.tactics.spring.persistencia

import ar.edu.unq.epers.tactics.spring.modelo.Party
import org.springframework.data.repository.CrudRepository

interface PartyDAO: CrudRepository<Party, Long>