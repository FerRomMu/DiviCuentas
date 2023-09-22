package ar.edu.unq.epers.tactics.spring.persistencia

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import org.springframework.data.repository.CrudRepository

interface AventureroDAO : CrudRepository<Aventurero, Long>