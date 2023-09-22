package ar.edu.unq.epers.tactics.spring.service.impl

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.Direccion
import ar.edu.unq.epers.tactics.spring.modelo.Orden
import ar.edu.unq.epers.tactics.spring.modelo.Party
import ar.edu.unq.epers.tactics.spring.persistencia.AventureroDAO
import ar.edu.unq.epers.tactics.spring.persistencia.PartyDAO
import ar.edu.unq.epers.tactics.spring.service.AventureroService
import ar.edu.unq.epers.tactics.spring.service.PartyService
import helpers.DataService
import helpers.DataServiceImp
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestInstance(PER_CLASS)
class PartyServiceTest {

    @Autowired
    lateinit var partyDAO: PartyDAO
    @Autowired
    lateinit var service: PartyService

    @Autowired
    lateinit var aventureroDAO: AventureroDAO
    @Autowired
    lateinit var serviceAventurero: AventureroService

    lateinit var party1: Party
    lateinit var party2: Party
    lateinit var party3: Party
    lateinit var juancho: Aventurero
    lateinit var fercho: Aventurero
    lateinit var chofi: Aventurero
    lateinit var pepita: Aventurero
    lateinit var vinchuca: Aventurero
    lateinit var messi: Aventurero

    @BeforeEach
    fun prepare() {
        party1 = Party("SQLe", "img.jpg")
        party2 = Party("SQLi", "afgas.jpg")
        party3 = Party("SQLa", "afgas.jpg")
        juancho = Aventurero( "juancho", "imagen.jpg")
        fercho = Aventurero( "fercho", "imagen.jpg")
        chofi = Aventurero( "chofi", "imagen.jpg")
        pepita = Aventurero("pepita", "img", 90.0, 5.0, 40.0, 3.0)
        vinchuca = Aventurero("vinchuca", "img", 40.0, 10.0, 30.0, 60.0)
        messi = Aventurero("messi", "img", 100.0, 90.0, 90.0, 100.0)

        service.crear(party1)
        service.crear(party2)
        service.crear(party3)
    }

    @Test
    fun siRecuperoLaPartyEstaTieneSusDatos(){
        var partyRecuperada = service.recuperar(party1.id!!)
        assertEquals("SQLe", partyRecuperada.nombre)
        assertEquals("img.jpg", partyRecuperada.imagen)
    }

    @Test
    fun siActualizoLaPartyYLuegoLaRecuperoLaPartyEstaActualizada() {
        party1.agregarAventurero(chofi)
        party1.agregarAventurero(juancho)
        party1.agregarAventurero(fercho)
        val id = party1.id!!
        service.actualizar(party1)
        val party = service.recuperar(id)
        assertEquals(3, party.aventureros.size)
    }

    @Test
    fun recuperarTodasRecuperaLas3PartiesEnOrden() {
        var parties = service.recuperarTodas()
        assertEquals(3, parties.size)
        assertEquals("SQLa", parties[0].nombre)
        assertEquals("SQLe", parties[1].nombre)
        assertEquals("SQLi", parties[2].nombre)
    }

    @Test
    fun siAgregoAventureroEsteEstaEnLaParty(){
        serviceAventurero.guardarAventurero(chofi)
        service.agregarAventureroAParty(party1.id!!, chofi)
        party1 = service.recuperar(party1.id!!)

        assertTrue(party1.aventureros.any{ it.id == chofi.id })
    }

    @Test
    fun recuperarTodasEsVaciaSiNoHayParties() {
        service.eliminarTodas()
        var parties = service.recuperarTodas()
        assertTrue(parties.isEmpty())
    }

    @Test
    fun siLaPartyAActualizarNoExisteDebeFallar(){
        var partyInexistente = Party("Inexistentes", "fake.png")
        assertThrows<NoSuchElementException> { service.actualizar(partyInexistente) }
    }

    @Test
    fun siLaPartyARecuperarNoExisteDebeFallar(){
        assertThrows<NoSuchElementException> { service.recuperar(1000000000) }
    }

    @Test
    fun siLaPartyTiene5MiembrosAlAgregarOtroDebeFallar(){
        party1.agregarAventurero(vinchuca)
        party1.agregarAventurero(pepita)
        party1.agregarAventurero(chofi)
        party1.agregarAventurero(fercho)
        party1.agregarAventurero(juancho)
        service.actualizar(party1)

        assertThrows<Exception> { service.agregarAventureroAParty(party1.id!!, messi) }
    }

    @Test
    fun siLaPartyAgregaMiembroRepetidoDebeFallar(){
        service.agregarAventureroAParty(party1.id!!, vinchuca)
        party1 = service.recuperar(party1.id!!)
        assertThrows<Exception> { service.agregarAventureroAParty(party1.id!!, vinchuca) }
    }

    @Test
    fun siLaPartyNoExisteAlAgregarUnAventureroDebeFallar(){
        assertThrows<NoSuchElementException> { service.agregarAventureroAParty(100000000, chofi) }
    }

    @Test
    fun partiesOrdenadasPorVictoriaDevuelveLasPartiesPaginadasEnOrden(){
        party1.victorias = 1
        party2.victorias = 2
        party3.victorias = 3
        service.actualizar(party1)
        service.actualizar(party2)
        service.actualizar(party3)

        var pagina = service.recuperarOrdenadas(Orden.VICTORIAS, Direccion.ASCENDENTE, null)
        assertEquals(party1.id, pagina.parties[0].id)
        assertEquals(party2.id, pagina.parties[1].id)
        assertEquals(party3.id, pagina.parties[2].id)

        pagina = service.recuperarOrdenadas(Orden.VICTORIAS, Direccion.DESCENDENTE, null)
        assertEquals(party3.id, pagina.parties[0].id)
        assertEquals(party2.id, pagina.parties[1].id)
        assertEquals(party1.id, pagina.parties[2].id)

    }

    @Test
    fun partiesOrdenadasPorDerrotaDevuelveLasPartiesPaginadasEnOrden(){
        party1.derrotas = 1
        party2.derrotas = 2
        party3.derrotas = 3
        service.actualizar(party1)
        service.actualizar(party2)
        service.actualizar(party3)

        var pagina = service.recuperarOrdenadas(Orden.DERROTAS, Direccion.ASCENDENTE, null)
        assertEquals(party1.id, pagina.parties[0].id)
        assertEquals(party2.id, pagina.parties[1].id)
        assertEquals(party3.id, pagina.parties[2].id)

        pagina = service.recuperarOrdenadas(Orden.DERROTAS, Direccion.DESCENDENTE, null)
        assertEquals(party3.id, pagina.parties[0].id)
        assertEquals(party2.id, pagina.parties[1].id)
        assertEquals(party1.id, pagina.parties[2].id)

    }

    @Test
    fun partiesOrdenadasPorPoderDevuelveLasPartiesPaginadasEnOrden(){
        party2.agregarAventurero(juancho)
        party3.agregarAventurero(messi)
        service.actualizar(party2)
        service.actualizar(party3)

        println(party1.poder())
        println(party2.poder())
        println(party3.poder())
        var pagina = service.recuperarOrdenadas(Orden.PODER, Direccion.ASCENDENTE, null)
        assertEquals(party1.id, pagina.parties[0].id)
        assertEquals(party2.id, pagina.parties[1].id)
        assertEquals(party3.id, pagina.parties[2].id)

        pagina = service.recuperarOrdenadas(Orden.PODER, Direccion.DESCENDENTE, null)
        assertEquals(party3.id, pagina.parties[0].id)
        assertEquals(party2.id, pagina.parties[1].id)
        assertEquals(party1.id, pagina.parties[2].id)

    }

    @AfterEach
    fun cleanup(){
        service.eliminarTodas()
        serviceAventurero.eliminarTodos()
    }
}
