package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.exceptions.InvalidValueException
import ar.edu.unq.epers.tactics.spring.modelo.tacticas.*
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TacticaDTOTest {


    lateinit var tactica: Tactica
    lateinit var tacticaDTO: TacticaDTO

    @BeforeEach
    fun prepare() {
        tactica = Tactica(TipoDeReceptor.ALIADO, TipoDeEstadistica.ARMADURA, Criterio.IGUAL, 30.0, Accion.CURAR, 2)
        tacticaDTO = TacticaDTO(1,5,TipoDeReceptor.ENEMIGO,TipoDeEstadistica.VIDA, Criterio.MAYOR_QUE, 50.0, Accion.ATAQUE_FISICO)
    }

    @Test
    fun laTacticaSePasaCorrectamenteDesdeModelo() {
        val laTacticaDTO = TacticaDTO.desdeModelo(tactica)
        Assertions.assertEquals(null, laTacticaDTO.id)
        Assertions.assertEquals(TipoDeReceptor.ALIADO, laTacticaDTO.receptor)
        Assertions.assertEquals(TipoDeEstadistica.ARMADURA, laTacticaDTO.tipoDeEstadistica)
        Assertions.assertEquals(Criterio.IGUAL, laTacticaDTO.criterio)
        Assertions.assertEquals(30.0, laTacticaDTO.valor)
        Assertions.assertEquals(Accion.CURAR, laTacticaDTO.accion)
        Assertions.assertEquals(2, laTacticaDTO.prioridad)
    }

    @Test
    fun laTacticaDTOSePasaCorrectamenteAModelo(){
        val laTactica = tacticaDTO.aModelo()
        Assertions.assertEquals(1, laTactica.id)
        Assertions.assertEquals(5, laTactica.prioridad)
        Assertions.assertEquals(TipoDeReceptor.ENEMIGO, laTactica.receptor)
        Assertions.assertEquals(TipoDeEstadistica.VIDA, laTactica.estadistica)
        Assertions.assertEquals(Criterio.MAYOR_QUE, laTactica.criterio)
        Assertions.assertEquals(50.0, laTactica.valor)
        Assertions.assertEquals(Accion.ATAQUE_FISICO, laTactica.accion)
    }

    @Test
    fun laTacticaSeActualizaCorrectamente(){
        tacticaDTO.actualizarModelo(tactica)
        Assertions.assertEquals(1, tactica.id)
        Assertions.assertEquals(5, tactica.prioridad)
        Assertions.assertEquals(TipoDeReceptor.ENEMIGO, tactica.receptor)
        Assertions.assertEquals(TipoDeEstadistica.VIDA, tactica.estadistica)
        Assertions.assertEquals(Criterio.MAYOR_QUE, tactica.criterio)
        Assertions.assertEquals(50.0, tactica.valor)
        Assertions.assertEquals(Accion.ATAQUE_FISICO, tactica.accion)
    }

    @Test
    fun unDTOConUnaPrioridadInvalidaFallaAlPasarAModelo(){
        tacticaDTO.prioridad = -2
        assertThrows<InvalidValueException> { tacticaDTO.aModelo() }
    }
}