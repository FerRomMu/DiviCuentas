package helpers

import ar.edu.unq.epers.tactics.spring.service.PartyService

class DataServiceImp(var partyService: PartyService) : DataService {

    override fun eliminarTodo() {
        /*runTrx {
            val session = HibernateTransactionRunner.currentSession
            val nombreDeTablas = session.createNativeQuery("show tables").resultList
            session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate()
            nombreDeTablas.forEach { result ->
                var tabla = ""
                when(result){
                    is String -> tabla = result
                    is Array<*> -> tabla= result[0].toString()
                }
                session.createNativeQuery("truncate table $tabla").executeUpdate()
            }
            session.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate()
        }*/

    }

    override fun crearSetDeDatosIniciales() {
        /*
        dao.crear(Party("Hola"))
        dao.crear(Party("Hff"))
        dao.crear(Party("Hfafad"))
        dao.crear(Party("Hafafa"))
        dao.crear(Party("Hdddd"))*/
    }
}