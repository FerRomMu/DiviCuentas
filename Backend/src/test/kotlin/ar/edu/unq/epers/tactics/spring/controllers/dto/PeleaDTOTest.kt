package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.exceptions.UnmodifiedPartyException
import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.Party
import ar.edu.unq.epers.tactics.spring.modelo.Pelea
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PeleaDTOTest {

    lateinit var party: Party
    lateinit var fercho: Aventurero
    lateinit var juancho: Aventurero
    lateinit var chofi: Aventurero
    lateinit var pelea: Pelea

    @BeforeEach
    fun prepare() {
        party = Party("sqlit", "imagen1.jpg")
        chofi = Aventurero("chofi", "img", 100.0, 100.0, 30.0, 100.0)
        fercho = Aventurero("fercho", "img", 80.0, 100.0, 70.0, 100.0)
        juancho = Aventurero("juancho", "img", 40.0, 9.0, 50.0, 100.0)
        party.agregarAventurero(chofi)
        party.agregarAventurero(fercho)
        party.agregarAventurero(juancho)
        party.id = 3

        pelea = Pelea(party, "Malvados")
    }

    @Test
    fun laPeleaSePasaCorrectamenteDesdeModelo() {
        val peleaDTO = PeleaDTO.desdeModelo(pelea)
        Assertions.assertEquals(null, peleaDTO.peleaId)
        Assertions.assertEquals("Malvados", peleaDTO.partyEnemiga)
        Assertions.assertEquals(3, peleaDTO.partyId)
    }

}