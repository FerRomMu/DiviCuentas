package ar.edu.unq.epers.tactics.spring.modelo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.LocalDate
import java.time.LocalDateTime

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PeleasPaginadasTest {
    lateinit var peleasPaginadas: PeleasPaginadas
    lateinit var peleasPaginadas2: PeleasPaginadas
    lateinit var party1: Party
    lateinit var pelea1: Pelea; lateinit var pelea2: Pelea; lateinit var pelea3: Pelea; lateinit var pelea4: Pelea;
    lateinit var pelea5: Pelea; lateinit var pelea6: Pelea; lateinit var pelea7: Pelea; lateinit var pelea8: Pelea;
    lateinit var pelea9: Pelea; lateinit var pelea10: Pelea; lateinit var pelea11: Pelea; lateinit var pelea12: Pelea;

    @BeforeEach
    fun prepare() {
        party1 = Party("party1","1")
        pelea1 = Pelea(party1, "1"); pelea2 = Pelea(party1, "2"); pelea3 = Pelea(party1, "3");
        pelea4 = Pelea(party1, "4"); pelea5 = Pelea(party1, "5"); pelea6 = Pelea(party1, "6");
        pelea7 = Pelea(party1, "7"); pelea8 = Pelea(party1, "8"); pelea9 = Pelea(party1, "9");
        pelea10 = Pelea(party1, "10"); pelea11 = Pelea(party1, "11"); pelea12 = Pelea(party1, "12");

        pelea1.date = LocalDateTime.of(2021, 10, 8, 14, 20)
        pelea2.date = LocalDateTime.of(2021, 10, 8, 22, 20)
        pelea3.date = LocalDateTime.of(2021, 10, 9, 14, 20)
        pelea4.date = LocalDateTime.of(2021, 10, 15, 14, 20)
        pelea5.date = LocalDateTime.of(2021, 10, 16, 14, 20)
        pelea6.date = LocalDateTime.of(2021, 10, 18, 14, 20)
        pelea7.date = LocalDateTime.of(2021, 10, 20, 14, 20)
        pelea8.date = LocalDateTime.of(2021, 10, 21, 14, 20)
        pelea9.date = LocalDateTime.of(2021, 10, 23, 14, 20)
        pelea10.date = LocalDateTime.of(2021, 10, 24, 14, 20)
        pelea11.date = LocalDateTime.of(2021, 10, 30, 14, 20)
        pelea12.date = LocalDateTime.of(2021, 10, 1, 14, 20)

        var peleas1 = listOf(pelea10, pelea2, pelea3, pelea4, pelea12, pelea6, pelea8, pelea7, pelea9, pelea1, pelea11, pelea5)
        var peleas2 = listOf(pelea1, pelea2, pelea3, pelea4, pelea12)

        peleasPaginadas = PeleasPaginadas(peleas1)
        peleasPaginadas2 = PeleasPaginadas(peleas2)
    }

    @Test
    fun siLePidoElTotalMeDevuelveLos12() {
        assertEquals(12, peleasPaginadas.total)
        peleasPaginadas.ordenarPeleas(1)
        assertEquals(12, peleasPaginadas.total)
    }

    @Test
    fun siPidoLaPagina0DeLas12PeleasDeLaPartyMeDevuelveLas10MasRecientes() {
        peleasPaginadas.ordenarPeleas(0)
        assertEquals(10, peleasPaginadas.peleas.size)
        assertEquals("11", peleasPaginadas.peleas[0].partyEnemiga)
        assertEquals("10", peleasPaginadas.peleas[1].partyEnemiga)
        assertEquals("9", peleasPaginadas.peleas[2].partyEnemiga)
        assertEquals("8", peleasPaginadas.peleas[3].partyEnemiga)
        assertEquals("7", peleasPaginadas.peleas[4].partyEnemiga)
        assertEquals("6", peleasPaginadas.peleas[5].partyEnemiga)
        assertEquals("5", peleasPaginadas.peleas[6].partyEnemiga)
        assertEquals("4", peleasPaginadas.peleas[7].partyEnemiga)
        assertEquals("3", peleasPaginadas.peleas[8].partyEnemiga)
        assertEquals("2", peleasPaginadas.peleas[9].partyEnemiga)
    }

    @Test
    fun siPidoLaPagina1DeLas12PeleasDeLaPartyMeDevuelveLas2MenosRecientesRestantes() {
        peleasPaginadas.ordenarPeleas(1)
        assertEquals(2, peleasPaginadas.peleas.size)
        assertEquals("1", peleasPaginadas.peleas[0].partyEnemiga)
        assertEquals("12", peleasPaginadas.peleas[1].partyEnemiga)
    }

    @Test
    fun siPidoLaPaginaCeroDeLas5PeleasEnPeleas2MeDevuelveLas5Ordenadas() {
        peleasPaginadas2.ordenarPeleas(0)
        assertEquals(5, peleasPaginadas2.peleas.size)
        assertEquals("4", peleasPaginadas2.peleas[0].partyEnemiga)
        assertEquals("3", peleasPaginadas2.peleas[1].partyEnemiga)
        assertEquals("2", peleasPaginadas2.peleas[2].partyEnemiga)
        assertEquals("1", peleasPaginadas2.peleas[3].partyEnemiga)
        assertEquals("12", peleasPaginadas2.peleas[4].partyEnemiga)
    }

    @Test
    fun siPidoLaPagina2DeLas12PeleasDeLaPartyDevuelveLaListaVacia() {
        peleasPaginadas.ordenarPeleas(2)
        assertTrue(peleasPaginadas.peleas.isEmpty())
    }
}