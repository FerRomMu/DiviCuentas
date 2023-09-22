package ar.edu.unq.epers.tactics.spring.persistencia

import ar.edu.unq.epers.tactics.spring.modelo.Pelea
import org.springframework.data.repository.CrudRepository

interface PeleaDAO : CrudRepository<Pelea, Long>