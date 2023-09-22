package ar.edu.unq.epers.tactics.spring.modelo

import ar.edu.unq.epers.tactics.spring.exceptions.BadAtributeException
import ar.edu.unq.epers.tactics.spring.exceptions.InvalidEmptyNameException
import ar.edu.unq.epers.tactics.spring.exceptions.InvalidValueException
import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.Party
import ar.edu.unq.epers.tactics.spring.modelo.tacticas.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AventureroTest {

    lateinit var chofi: Aventurero
    lateinit var fercho: Aventurero
    lateinit var juancho: Aventurero

    @BeforeEach
    fun prepare() {
        chofi = Aventurero("chofi", "img.jpg", 100.0, 100.0, 30.0, 100.0)
        fercho = Aventurero("fercho", "img.png",  80.0, 100.0, 70.0, 100.0)
        juancho = Aventurero("juancho", "unaImagen", 40.0, 9.0, 50.0, 100.0)
    }

    @Test
    fun testDatosYAtributos() {
        assertEquals(1, fercho.nivel)
        assertEquals("fercho",fercho.nombre)
        assertEquals(80.0, fercho.atributos.fuerza)
        assertEquals(100.0, fercho.atributos.destreza)
        assertEquals(70.0, fercho.atributos.constitucion)
        assertEquals(100.0, fercho.atributos.inteligencia)
    }

    @Test
    fun testEstadisticasDeUnAventurero() {
        assertEquals( 165.0, chofi.vida())
        assertEquals(101.0, chofi.mana())
        assertEquals(31.0, chofi.armadura())
        assertEquals(101.0, chofi.velocidad())
        assertEquals(151.0, chofi.dañoF())
        assertEquals(101.0, chofi.dañoM())
        assertEquals(201.0, chofi.pres())
    }

    @Test
    fun unAventureroRecibeDañoPeroNoMasAllaDeCero() {
        juancho.recibirDaño(5.0)
        assertEquals(140.0, juancho.vidaActual)
        juancho.recibirDaño(145.0)
        assertEquals(0.0, juancho.vidaActual)
    }

    @Test
    fun unAventureroSePuedeCurarPeroNoMasAllaDeSuVidaYSoloSiEstaVivo() {
        assertEquals(145.0, juancho.vidaActual)
        juancho.curar(5.0)
        assertEquals(145.0, juancho.vidaActual)

        juancho.vidaActual = 130.0
        juancho.curar(10.0)
        assertEquals(140.0, juancho.vidaActual)

        juancho.vidaActual = 0.0
        juancho.curar(100.0)
        assertEquals(0.0, juancho.vidaActual)
    }

    @Test
    fun unAventureroModificaSuManaPeroNoMasAllaDeCeroOSuMana() {
        assertEquals(101.0, juancho.manaActual)
        juancho.modificarMana(9)
        assertEquals(101.0, juancho.manaActual)

        juancho.manaActual = 90.0
        juancho.modificarMana(10)
        assertEquals(100.0, juancho.manaActual)

        juancho.modificarMana(-111)
        assertEquals(0.0, juancho.manaActual)
    }

    @Test
    fun resolverTacticaObtieneLaHabilidadDeLaPrimerTacticaQueSeCumple() {
        val party1 = Party("Tropa persistente", "nosotros.jpg")
        party1.agregarAventurero(chofi)
        party1.agregarAventurero(juancho)
        val enemigos = listOf(fercho)
        val tacticaIncumplida1 = Tactica(TipoDeReceptor.ENEMIGO, TipoDeEstadistica.VIDA, Criterio.MENOR_QUE, 30.0, Accion.ATAQUE_MAGICO, 0)
        val tacticaIncumplida2 = Tactica(TipoDeReceptor.ALIADO, TipoDeEstadistica.ARMADURA, Criterio.MENOR_QUE, 10.0, Accion.DEFENDER, 1)
        val tacticaCumple1 = Tactica(TipoDeReceptor.UNO_MISMO, TipoDeEstadistica.MANA, Criterio.IGUAL, 101.0, Accion.MEDITAR, 2)
        val tacticaCumple2 = Tactica(TipoDeReceptor.ENEMIGO, TipoDeEstadistica.VIDA, Criterio.MAYOR_QUE, 20.0, Accion.ATAQUE_FISICO, 3)
        juancho.tacticas = mutableListOf(tacticaIncumplida1, tacticaCumple1, tacticaIncumplida2, tacticaCumple2)

        val habilidad = juancho.resolverTactica(enemigos)
        assertEquals(habilidad.receptor, juancho)
    }

    @Test
    fun resetAventureroDevuelveAlAventureroASuEstadoInicial() {
        chofi.vidaActual = 20.0
        chofi.manaActual = 20.0
        chofi.addDefensor(juancho)
        chofi.setterDefendido(fercho)
        chofi.resetAventurero()

        assertEquals( 165.0, chofi.vida())
        assertEquals(101.0, chofi.mana())
        assertTrue(chofi.defensores.isEmpty())
        assertEquals(null, chofi.defendido)
    }

    @Test
    fun dejarDeDefenderDejaDeDefender() {
        chofi.setterDefendido(juancho)
        chofi.addDefensor(juancho)
        chofi.dejarDeDefender()

        assertFalse(juancho.defensores.contains(juancho))
        assertEquals(null, chofi.defendido)
    }

    @Test
    fun unAventureroNoSePuedeCrearSinNombre(){
        assertThrows<InvalidEmptyNameException> {  Aventurero("","imagen.jpg")}
        assertThrows<InvalidEmptyNameException> {  Aventurero("","imagen.jpg", 3.0, 5.0, 4.0, 4.0) }
    }

    @Test
    fun unAventureroNoSePuedeCrearSinImagen(){
        assertThrows<InvalidEmptyNameException> {  Aventurero("a","")}
        assertThrows<InvalidEmptyNameException> {  Aventurero("b","", 3.0, 5.0, 4.0, 4.0) }
    }

    @Test
    fun unAventureroNoSePuedeCrearConAtributosNegativos(){
        assertThrows<BadAtributeException> {  Aventurero("a","imagen.jpg", -3.0, 5.0, 4.0, 4.0) }
        assertThrows<BadAtributeException> {  Aventurero("b","imagen.jpg", 3.0, -5.0, 4.0, 4.0) }
        assertThrows<BadAtributeException> {  Aventurero("c","imagen.jpg", 3.0, 5.0, -4.0, 4.0) }
        assertThrows<BadAtributeException> {  Aventurero("d","imagen.jpg", 3.0, 5.0, 4.0, -4.0) }
    }

    @Test
    fun unAventureroNoSePuedeCrearConAtributosMayoresA100(){
        assertThrows<BadAtributeException> {  Aventurero("a","imagen.jpg", 100.1, 5.0, 4.0, 4.0) }
        assertThrows<BadAtributeException> {  Aventurero("b","imagen.jpg", 3.0, 100.1, 4.0, 4.0) }
        assertThrows<BadAtributeException> {  Aventurero("c","imagen.jpg", 3.0, 5.0, 110.0, 4.0) }
        assertThrows<BadAtributeException> {  Aventurero("d","imagen.jpg", 3.0, 5.0, 4.0, 100.1) }
    }

    @Test
    fun siSeTrataDeBajarNivelFalla(){
        assertThrows<InvalidValueException> { fercho.subirTantosNiveles(-1) }
    }
}