package ar.edu.unq.epers.tactics.spring.modelo

import ar.edu.unq.epers.tactics.spring.exceptions.UnmodifiedPartyException
import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.Party
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PartyTest {

    lateinit var party: Party
    lateinit var fercho: Aventurero
    lateinit var juancho: Aventurero
    lateinit var chofi: Aventurero
    lateinit var pepita: Aventurero
    lateinit var vinchuca: Aventurero
    lateinit var messi: Aventurero

    @BeforeEach
    fun prepare() {
        party = Party("sqlit", "imagen1.jpg")
        chofi = Aventurero("chofi", "img", 100.0, 100.0, 30.0, 100.0)
        fercho = Aventurero("fercho", "img", 80.0, 100.0, 70.0, 100.0)
        juancho = Aventurero("juancho", "img", 40.0, 9.0, 50.0, 100.0)
        pepita = Aventurero("pepita", "img", 90.0, 5.0, 40.0, 3.0)
        vinchuca = Aventurero("vinchuca", "img", 40.0, 10.0, 30.0, 60.0)
        messi = Aventurero("messi", "img", 100.0, 90.0, 90.0, 100.0)

    }

    @Test
    fun unaPartyRecienCreadaConoceSusDatosYNoTieneAventureros() {
        Assertions.assertTrue(party.aventureros.isEmpty())
        Assertions.assertEquals("imagen1.jpg",party.imagen)
        Assertions.assertEquals("sqlit", party.nombre)
        Assertions.assertEquals(null, party.id)
    }

    @Test
    fun unaPartyAgregaHasta5Aventureros() {
        party.agregarAventurero(chofi)
        party.agregarAventurero(fercho)
        party.agregarAventurero(juancho)
        party.agregarAventurero(vinchuca)
        party.agregarAventurero(messi)

        assertThrows<UnmodifiedPartyException> { party.agregarAventurero(pepita) }
        Assertions.assertEquals(5, party.aventureros.size)
    }

    @Test
    fun unaPartyNoPuedeAgregar2VecesAlMismoAventurero() {
        party.agregarAventurero(chofi)
        assertThrows<UnmodifiedPartyException> { party.agregarAventurero(chofi) }
    }

    @Test
    fun unaPartyAlComenzarUnaPeleaSabeQueEstaPeleandoHastaSerReseteada() {
        party.iniciarPelea()
        assertTrue(party.estaEnPelea)

        party.terminarPelea(true)
        assertFalse(party.estaEnPelea)
    }

    @Test
    fun unaPartyDevuelveElPoderCorrespondienteDependiendoDeSusAventureros() {
        Assertions.assertEquals(0.0, party.poder())

        party.agregarAventurero(chofi)
        Assertions.assertEquals(453.0, party.poder())

        party.agregarAventurero(juancho)
        Assertions.assertEquals(649.5, party.poder())
    }

    @Test
    fun unaPartyContabilizaSusVictoriasYSoloCuandoGana() {
        Assertions.assertEquals(0, party.victorias)

        party.terminarPelea(true)
        Assertions.assertEquals(1, party.victorias)

        party.terminarPelea(true)
        Assertions.assertEquals(2, party.victorias)

        party.terminarPelea(false)
        Assertions.assertEquals(2, party.victorias)
    }

    @Test
    fun unaPartyContabilizaSusDerrotasYSoloCuandoPierde() {
        Assertions.assertEquals(0, party.derrotas)

        party.terminarPelea(false)
        Assertions.assertEquals(1, party.derrotas)

        party.terminarPelea(false)
        Assertions.assertEquals(2, party.derrotas)

        party.terminarPelea(true)
        Assertions.assertEquals(2, party.derrotas)
    }
}