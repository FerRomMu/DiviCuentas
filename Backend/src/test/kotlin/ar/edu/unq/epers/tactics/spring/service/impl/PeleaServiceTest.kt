package ar.edu.unq.epers.tactics.spring.service.impl

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.Party
import ar.edu.unq.epers.tactics.spring.modelo.Pelea
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Atacar
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.auxiliar.ExactGet
import ar.edu.unq.epers.tactics.spring.persistencia.AventureroDAO
import ar.edu.unq.epers.tactics.spring.persistencia.PartyDAO
import ar.edu.unq.epers.tactics.spring.persistencia.PeleaDAO
import ar.edu.unq.epers.tactics.spring.modelo.tacticas.*
import ar.edu.unq.epers.tactics.spring.service.AventureroService
import ar.edu.unq.epers.tactics.spring.service.PartyService
import ar.edu.unq.epers.tactics.spring.service.PeleaService
import helpers.DataService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestInstance(PER_CLASS)
class PeleaServiceTest {
    @Autowired
    lateinit var peleaDAO: PeleaDAO
    @Autowired
    lateinit var partyDAO: PartyDAO
    @Autowired
    lateinit var aventureroDAO: AventureroDAO
    @Autowired
    lateinit var peleaService: PeleaService
    @Autowired
    lateinit var partyService: PartyService
    @Autowired
    lateinit var aventureroService: AventureroService
    lateinit var party1: Party
    lateinit var juancho: Aventurero
    lateinit var fercho: Aventurero
    lateinit var chofi: Aventurero
    lateinit var gandalf: Aventurero
    lateinit var dumbledore: Aventurero
    lateinit var enemigos: List<Aventurero>
    lateinit var serviceData: DataService
    lateinit var pelea: Pelea

    @BeforeEach
    fun prepare() {

        party1 = Party("SQLt", "jajaja.jpg")
        fercho = Aventurero( "fercho, el maguito", "imagen.jpg", 1.0,1.0,2.0,7.0)
        chofi = Aventurero("Chofi, la guerrera", "imagen.jpg", 1.0,4.0,5.0,1.0)
        juancho = Aventurero( "juancho, el asesino", "imagen.jpg",5.0,4.0,1.0,1.0)
        party1.agregarAventurero(fercho); party1.agregarAventurero(chofi); party1.agregarAventurero(juancho)

        gandalf = Aventurero("Gandalf", "imagen.jpg", 1.0,1.0,2.0,7.0)
        dumbledore = Aventurero("Dumbledore", "imagen.jpg", 1.0,1.0,2.0,7.0)
        enemigos = listOf(gandalf, dumbledore)

        partyService.crear(party1)
        aventureroService.guardarAventurero(fercho)
        aventureroService.guardarAventurero(juancho)
        aventureroService.guardarAventurero(chofi)
        pelea = peleaService.iniciarPelea(party1.id!!, "SQLi")
    }

    @Test
    fun seIniciaLaPeleaConLaParty1Involucrada() {
        assertEquals(party1.id, pelea.party.id)
    }

    @Test
    fun siLaPartyYaEstaEnPeleaNoPuedeIniciarOtra(){
        assertThrows<Exception> { peleaService.iniciarPelea(party1.id!!, "SQLe") }
    }

    @Test
    fun siLaPartyNoExisteNoPuedeIniciarPelea(){
        assertThrows<NoSuchElementException> { peleaService.iniciarPelea(100000000, "SQLe") }
    }

    @Test
    fun seIniciaLaPeleaConLaParty1YSeChequeaElEstadoDePelea(){
        assertTrue(peleaService.estaEnPelea(party1.id!!))
    }

    @Test
    fun siLaPartyNoExisteNoSePuedeSaberSiEstaEnPelea(){
        assertThrows<NoSuchElementException> { peleaService.estaEnPelea(10000000) }
    }

    @Test
    fun seIniciaLaPeleaYSeRecuperaSabiendoQueLaParty1EstaInvolucrada() {
        val peleaRecuperada = peleaService.recuperar(pelea.id!!)
        assertEquals(party1.id!!, peleaRecuperada.party.id!!)
    }

    @Test
    fun seRecuperanPeleasOrdenadas(){
        pelea.date = LocalDateTime.of(1990,5, 20, 1,1,1)
        pelea = peleaService.actualizar(pelea)
        pelea = peleaService.terminarPelea(pelea.id!!)

        var otraPelea = Pelea(party1, "otraParty")
        otraPelea.date = LocalDateTime.of(1998,1,1,1,1)
        otraPelea = peleaService.iniciarPelea(otraPelea.party.id!!, otraPelea.partyEnemiga)
        otraPelea = peleaService.terminarPelea(otraPelea.id!!)

        var ultimaPelea = Pelea(party1, "otraParty")
        ultimaPelea.date = LocalDateTime.of(2000,1,1,1,1)
        ultimaPelea = peleaService.iniciarPelea(ultimaPelea.party.id!!, ultimaPelea.partyEnemiga)
        ultimaPelea = peleaService.terminarPelea(ultimaPelea.id!!)

        var pagina = peleaService.recuperarOrdenadas(party1.id!!, null)

        assertEquals(ultimaPelea.id!!, pagina.peleas[0].id)
        assertEquals(otraPelea.id!!, pagina.peleas[1].id)
        assertEquals(pelea.id!!, pagina.peleas[2].id)
    }

    @Test
    fun resolverTurno(){
        val tacticaIncumplida1 = Tactica(TipoDeReceptor.ALIADO, TipoDeEstadistica.ARMADURA, Criterio.MENOR_QUE, 0.0, Accion.DEFENDER,0)
        val tacticaCumple1 = Tactica(TipoDeReceptor.UNO_MISMO, TipoDeEstadistica.MANA, Criterio.MAYOR_QUE, 0.0, Accion.MEDITAR,1)
        val tacticaCumple2 = Tactica(TipoDeReceptor.ENEMIGO, TipoDeEstadistica.VIDA, Criterio.MAYOR_QUE, 2.0, Accion.ATAQUE_FISICO,2)
        chofi.tacticas = mutableListOf(tacticaIncumplida1, tacticaCumple1, tacticaCumple2)
        aventureroService.actualizar(chofi)
        val habilidad = peleaService.resolverTurno(pelea.id!!, chofi.id!!, enemigos)
        assertEquals(habilidad.receptor.nombre, "Chofi, la guerrera")
    }

    @Test
    fun siResuelveTurnoDeUnaPeleaInexistenteFalla(){
        assertThrows<NoSuchElementException> { peleaService.resolverTurno(100000000, chofi.id!!, enemigos) }
    }

    @Test
    fun siResuelveTurnoConUnAventureroInexistenteFalla(){
        assertThrows<NoSuchElementException> { peleaService.resolverTurno(pelea.id!!, 100000000, enemigos) }
    }

    @Test
    fun seRecibeHabilidadDeAtaqueParaChofi(){
        assertEquals(16.0, chofi.vidaActual)
        val ataque = Atacar(juancho.dañoF(),juancho.pres(),chofi)
        val chofiActualizada = peleaService.recibirHabilidad(pelea.id!!,chofi.id!!,ataque)
        assertEquals(8.0, chofiActualizada.vidaActual)
    }

    @Test
    fun siSeRecibeHabilidadDeAtaqueParaUnAventureroQueNoEstaDebeFallar(){
        val aventureroInexistente = Aventurero("Alguien", "imagen.jpg")
        aventureroInexistente.id = 10000000
        val ataque = Atacar(juancho.dañoF(), juancho.pres(),aventureroInexistente)
        assertThrows<NoSuchElementException> { peleaService.recibirHabilidad(pelea.id!!,aventureroInexistente.id!!,ataque) }
    }

    @Test
    fun siSeRecibeHabilidadParaUnAventureroQueNoEsElObjetivoDebeFallar(){
        val ataque = Atacar(juancho.dañoF(),juancho.pres(),chofi)
        assertThrows<IllegalArgumentException> { peleaService.recibirHabilidad(pelea.id!!,juancho.id!!,ataque) }
    }

    @Test
    fun siTerminaLaPeleaLaPartyDejaDeEstarPeleando(){
        peleaService.terminarPelea(pelea.id!!)
        assertFalse(peleaService.estaEnPelea(pelea.party.id!!))
    }

    @Test
    fun unAventureroRecibeHabilidadDeAtaqueLaPeleaSeGuardaEsaHabilidad() {
        assertEquals(16.0, chofi.vidaActual)
        val ataque = Atacar(juancho.dañoF(),juancho.pres(),chofi)
        val chofiActualizada = peleaService.recibirHabilidad(pelea.id!!,chofi.id!!,ataque)
        val peleaRecuperada = peleaService.recuperar(pelea.id!!)
        val habilidades = peleaRecuperada.habilidades
        assertEquals(8.0, chofiActualizada.vidaActual)
        assertTrue(habilidades.size == 1)
    }

    @Test
    fun siTerminaLaPeleaLosMiembrosDeLaPartySeRecuperan(){
        peleaService.terminarPelea(pelea.id!!)

        assertEquals(juancho.vida(), juancho.vidaActual)
        assertEquals(juancho.mana(), juancho.manaActual)
        assertTrue(juancho.defensores.isEmpty())
        assertNull(juancho.defendido)

        assertEquals(fercho.vida(), fercho.vidaActual)
        assertEquals(fercho.mana(), fercho.manaActual)
        assertTrue(fercho.defensores.isEmpty())
        assertNull(fercho.defendido)

        assertEquals(chofi.vida(), chofi.vidaActual)
        assertEquals(chofi.mana(), chofi.manaActual)
        assertTrue(chofi.defensores.isEmpty())
        assertNull(chofi.defendido)
    }

    @Test
    fun siTerminaUnaPeleaInexistenteFalla(){
        assertThrows<NoSuchElementException> { peleaService.terminarPelea(100000000) }
    }

    @AfterEach
    fun cleanup(){
        peleaService.eliminarTodas()
        partyService.eliminarTodas()
        aventureroService.eliminarTodos()
    }
}