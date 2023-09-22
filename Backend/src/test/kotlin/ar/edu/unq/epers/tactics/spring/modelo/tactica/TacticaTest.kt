package ar.edu.unq.epers.tactics.spring.modelo.tactica

import ar.edu.unq.epers.tactics.spring.exceptions.InvalidValueException
import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.Party
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Habilidad
import ar.edu.unq.epers.tactics.spring.modelo.tacticas.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TacticaTest {

    lateinit var party1: Party
    lateinit var juancho: Aventurero
    lateinit var fercho: Aventurero
    lateinit var chofi: Aventurero
    lateinit var nazgul: Aventurero
    lateinit var saruman: Aventurero
    lateinit var sauron: Aventurero
    lateinit var enemigos: List<Aventurero>
    lateinit var t: Tactica
    var h: Habilidad? = null

    @BeforeEach
    fun prepare() {
        chofi = Aventurero("chofi", "imagen.jpg", 100.0, 100.0, 30.0, 100.0)
        fercho = Aventurero("fercho", "imagen.jpg", 80.0, 100.0, 70.0, 100.0)
        juancho = Aventurero("juancho", "imagen.jpg", 40.0, 9.0, 50.0, 100.0)
        party1 = Party("Tropa persistente", "nosotros.jpg")
        party1.agregarAventurero(chofi)
        party1.agregarAventurero(juancho)
        party1.agregarAventurero(fercho)
        nazgul = Aventurero("nazgul", "imagen.jpg", 50.0, 40.0, 100.0, 70.0)
        saruman = Aventurero("saruman", "imagen.jpg", 60.0, 30.0, 30.0, 100.0)
        sauron = Aventurero("sauron", "imagen.jpg", 100.0, 100.0, 100.0, 100.0)
        enemigos = listOf(saruman,sauron,nazgul)
    }

    @Test
    fun meditarSeCreaSoloCuandoCumpleCriterio(){
        t = Tactica(TipoDeReceptor.UNO_MISMO, TipoDeEstadistica.MANA, Criterio.IGUAL, 50.0, Accion.MEDITAR, 0)
        h = t.ejecutar(fercho, party1.aventureros, enemigos)
        assertNull(h)
        t = Tactica(TipoDeReceptor.UNO_MISMO, TipoDeEstadistica.MANA, Criterio.MENOR_QUE, 50.0, Accion.MEDITAR, 0)
        h = t.ejecutar(fercho, party1.aventureros, enemigos)
        assertNull(h)
        t = Tactica(TipoDeReceptor.UNO_MISMO, TipoDeEstadistica.MANA, Criterio.MAYOR_QUE, 50.0, Accion.MEDITAR, 0)
        h = t.ejecutar(fercho, party1.aventureros, enemigos)
        assertNotNull(h)
        assertEquals(fercho, h!!.receptor)
    }

    @Test
    fun curarSeCreaSoloCuandoCumpleCriterio(){
        t = Tactica(TipoDeReceptor.ALIADO, TipoDeEstadistica.VIDA, Criterio.IGUAL, 200.0, Accion.CURAR, 0)
        h = t.ejecutar(juancho, party1.aventureros, enemigos)
        assertNull(h)
        t = Tactica(TipoDeReceptor.ALIADO, TipoDeEstadistica.VIDA, Criterio.MAYOR_QUE, 200.0, Accion.CURAR,0)
        h = t.ejecutar(juancho, party1.aventureros, enemigos)
        assertNotNull(h)
        assertEquals(fercho, h!!.receptor)
    }

    @Test
    fun defenderSeCreaSoloCuandoCumpleCriterio(){
        t = Tactica(TipoDeReceptor.ALIADO, TipoDeEstadistica.VIDA, Criterio.IGUAL, 200.0, Accion.DEFENDER,0)
        h = t.ejecutar(juancho, party1.aventureros, enemigos)
        assertNull(h)
        t = Tactica(TipoDeReceptor.ALIADO, TipoDeEstadistica.VIDA, Criterio.MAYOR_QUE, 200.0, Accion.DEFENDER, 0)
        h = t.ejecutar(juancho, party1.aventureros, enemigos)
        assertNotNull(h)
        assertEquals(fercho, h!!.receptor)
    }

    @Test
    fun atacarSeCreaSoloCuandoCumpleCriterio(){
        t = Tactica(TipoDeReceptor.ENEMIGO, TipoDeEstadistica.VIDA, Criterio.IGUAL, 300.0, Accion.ATAQUE_FISICO, 0)
        h = t.ejecutar(chofi, party1.aventureros, enemigos)
        assertNull(h)
        t = Tactica(TipoDeReceptor.ENEMIGO, TipoDeEstadistica.VIDA, Criterio.MAYOR_QUE, 300.0, Accion.ATAQUE_FISICO, 0)
        h = t.ejecutar(chofi, party1.aventureros, enemigos)
        assertNotNull(h)
        assertEquals(sauron, h!!.receptor)
    }

    @Test
    fun atacarMagiaSeCreaSoloCuandoCumpleCriterio(){
        t = Tactica(TipoDeReceptor.ENEMIGO, TipoDeEstadistica.VIDA, Criterio.IGUAL, 300.0, Accion.ATAQUE_MAGICO, 0)
        h = t.ejecutar(chofi, party1.aventureros, enemigos)
        assertNull(h)
        t = Tactica(TipoDeReceptor.ENEMIGO, TipoDeEstadistica.VIDA, Criterio.MAYOR_QUE, 300.0, Accion.ATAQUE_MAGICO, 0)
        h = t.ejecutar(chofi, party1.aventureros, enemigos)
        assertNotNull(h)
        assertEquals(sauron, h!!.receptor)
    }

    @Test
    fun losCriteriosFuncionanCorrectamente(){
        t = Tactica(TipoDeReceptor.ENEMIGO, TipoDeEstadistica.VIDA, Criterio.IGUAL, 300.0, Accion.ATAQUE_MAGICO, 0)
        h = t.ejecutar(chofi, party1.aventureros, enemigos)
        assertNull(h)
        sauron.vidaActual = 300.0
        h = t.ejecutar(chofi, party1.aventureros, enemigos)
        assertNotNull(h)

        t = Tactica(TipoDeReceptor.ENEMIGO, TipoDeEstadistica.VIDA, Criterio.MAYOR_QUE, 300.0, Accion.ATAQUE_MAGICO, 0)
        h = t.ejecutar(chofi, party1.aventureros, enemigos)
        assertNull(h)
        sauron.vidaActual = 301.0
        h = t.ejecutar(chofi, party1.aventureros, enemigos)
        assertNotNull(h)

        t = Tactica(TipoDeReceptor.ENEMIGO, TipoDeEstadistica.VIDA, Criterio.MENOR_QUE, 10.0, Accion.ATAQUE_MAGICO, 0)
        h = t.ejecutar(chofi, party1.aventureros, enemigos)
        assertNull(h)
        sauron.vidaActual = 9.0
        h = t.ejecutar(chofi, party1.aventureros, enemigos)
        assertNotNull(h)
    }

    @Test
    fun siSeCreaUnaTacticaConPrioridadNegativaFalla() {
        assertThrows<InvalidValueException> {
            Tactica(
                TipoDeReceptor.ALIADO, TipoDeEstadistica.ARMADURA, Criterio.MAYOR_QUE,
                20.0, Accion.CURAR,-1)
        }
    }
}