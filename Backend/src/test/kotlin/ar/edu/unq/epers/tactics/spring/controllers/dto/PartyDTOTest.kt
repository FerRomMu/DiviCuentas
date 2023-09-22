package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.exceptions.UnmodifiedPartyException
import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.Party
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PartyDTOTest {

    lateinit var party: Party
    lateinit var fercho: Aventurero
    lateinit var juancho: Aventurero
    lateinit var chofi: Aventurero

    lateinit var partyDTO: PartyDTO

    @BeforeEach
    fun prepare() {
        party = Party("sqlit", "imagen1.jpg")
        chofi = Aventurero("chofi", "img", 100.0, 100.0, 30.0, 100.0)
        fercho = Aventurero("fercho", "img", 80.0, 100.0, 70.0, 100.0)
        juancho = Aventurero("juancho", "img", 40.0, 9.0, 50.0, 100.0)
        party.agregarAventurero(chofi)
        party.agregarAventurero(fercho)
        party.agregarAventurero(juancho)

        partyDTO = PartyDTO(1,"party", "party.jpg", listOf<AventureroDTO>(AventureroDTO.desdeModelo(fercho)))
    }

    @Test
    fun laPartySePasaCorrectamenteDesdeModelo() {
        val laPartyDTO = PartyDTO.desdeModelo(party)
        Assertions.assertEquals(null, laPartyDTO.id)
        Assertions.assertEquals("sqlit", laPartyDTO.nombre)
        Assertions.assertEquals("imagen1.jpg", laPartyDTO.imagenURL)
        Assertions.assertEquals(3, laPartyDTO.aventureros.size)
    }

    @Test
    fun laPartySePasaCorrectamenteAModelo(){
        val laParty = partyDTO.aModelo()
        Assertions.assertEquals(1, laParty.id)
        Assertions.assertEquals("party", laParty.nombre)
        Assertions.assertEquals("party.jpg", laParty.imagen)
        Assertions.assertEquals(1, laParty.aventureros.size)
    }

    @Test
    fun laPartySeActualizaCorrectamente(){
        val nuevaPartyDTO = PartyDTO(1, "otroNombre", "imagenActualizada.jpg", listOf<AventureroDTO>())
        nuevaPartyDTO.actualizarModelo(party)

        Assertions.assertEquals(1, party.id)
        Assertions.assertEquals("otroNombre", party.nombre)
        Assertions.assertEquals("imagenActualizada.jpg", party.imagen)
        Assertions.assertTrue(party.aventureros.isEmpty())
    }

    @Test
    fun siLaPartyDTOTieneMasDe5AventurerosFalla(){
        var jose = Aventurero("jose", "img")
        var manuel = Aventurero("manuel", "img")
        var miguel = Aventurero("miguel", "img")
        var muchosAventureroDTO = listOf<Aventurero>(chofi, fercho, juancho, manuel, miguel, jose).map { av -> AventureroDTO.desdeModelo(av) }
        partyDTO = PartyDTO(1,"partyQueFalla", "img.jpg", muchosAventureroDTO)
        assertThrows<UnmodifiedPartyException> { partyDTO.aModelo() }
    }
}