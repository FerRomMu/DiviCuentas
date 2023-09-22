package ar.edu.unq.epers.tactics.spring.service

import ar.edu.unq.epers.tactics.spring.modelo.*

interface PartyService {
    fun crear(party: Party) : Party
    fun actualizar(party: Party): Party
    fun recuperar(idDeLaParty: Long): Party
    fun recuperarTodas(): List<Party>
    fun agregarAventureroAParty(idDeLaParty: Long, aventurero: Aventurero) : Aventurero
    fun recuperarOrdenadas(orden: Orden, direccion: Direccion, pagina:Int?): PartyPaginadas
    fun allAventurerosEnParty(idDeLaParty: Long) : List<Aventurero>
    fun eliminarTodas()

}


