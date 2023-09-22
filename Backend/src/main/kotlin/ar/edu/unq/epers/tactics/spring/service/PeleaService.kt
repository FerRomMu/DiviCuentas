package ar.edu.unq.epers.tactics.spring.service

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.Pelea
import ar.edu.unq.epers.tactics.spring.modelo.PeleasPaginadas
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Habilidad

interface PeleaService {
    fun iniciarPelea(idDeLaParty: Long, partyEnemiga: String) : Pelea
    fun estaEnPelea(partyId: Long):Boolean
    fun resolverTurno(peleaId: Long, aventureroId:Long, enemigos:List<Aventurero>): Habilidad
    fun recibirHabilidad(peleaId: Long,aventureroId: Long, habilidadId: Habilidad): Aventurero
    fun terminarPelea(idDeLaPelea: Long): Pelea
    fun actualizar(pelea: Pelea): Pelea
    fun recuperar(idDeLaPelea: Long): Pelea
    fun recuperarOrdenadas(partyId:Long, pagina:Int?): PeleasPaginadas
    fun eliminarTodas()
}