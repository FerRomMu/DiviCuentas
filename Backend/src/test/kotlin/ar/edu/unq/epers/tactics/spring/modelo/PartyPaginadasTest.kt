package ar.edu.unq.epers.tactics.spring.modelo


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PartyPaginadasTest {
    lateinit var partyPaginadas: PartyPaginadas
    lateinit var partiesT: List<Party>
    lateinit var party1: Party; lateinit var party2: Party; lateinit var party3: Party; lateinit var party4: Party
    lateinit var party5: Party; lateinit var party6: Party; lateinit var party7: Party; lateinit var party8: Party
    lateinit var party9: Party; lateinit var party10: Party; lateinit var party11: Party; lateinit var party12: Party


    @BeforeEach
    fun prepare() {
        party1 = Party("party1", "1"); party2 = Party("party2", "2"); party3 = Party("party3", "3"); party4= Party("party4", "4");
        party5 = Party("party5", "5"); party6 = Party("party6", "6"); party7 = Party("party7", "7"); party8 = Party("party8", "8");
        party9 = Party("party9", "9"); party10 = Party("party10", "10"); party11 = Party("party11", "11"); party12 = Party("party12", "12");

        party1.derrotas=20; party2.derrotas=2; party3.derrotas=3; party4.derrotas=4;
        party5.derrotas=5; party6.derrotas=6; party7.derrotas=7; party8.derrotas=8;
        party9.derrotas=9; party10.derrotas=10; party11.derrotas=11; party12.derrotas=0;

        party1.victorias=12; party2.victorias=11; party3.victorias=10; party4.victorias=9;
        party5.victorias=8; party6.victorias=7; party7.victorias=6; party8.victorias=5;
        party9.victorias=4; party10.victorias=3; party11.victorias=2; party12.victorias=20;

        partiesT = listOf(party3,party2,party7,party5,party4,party6,party9,party8,party1,party10,party11,party12)

        partyPaginadas = PartyPaginadas(partiesT)
    }

    @Test
    fun siLePidoLaPagina0MeDevuelveLosPrimeros10OrdenadosPorDerrotasAscendente() {
        partyPaginadas.ordenarParties(Orden.DERROTAS, Direccion.ASCENDENTE, 0)
        assertEquals(10, partyPaginadas.parties.size)
    }

    @Test
    fun siLePidoSinPaginaMeDevuelveLosPrimeros10OrdenadosPorDerrotasAscendente() {
        partyPaginadas.ordenarParties(Orden.DERROTAS, Direccion.ASCENDENTE, null)
        assertEquals(10, partyPaginadas.parties.size)
    }

    @Test
    fun siLePidoLaPagina1MeDevuelveLos2PartiesRestantesDeLos12EnTotal() {
        partyPaginadas.ordenarParties(Orden.DERROTAS, Direccion.ASCENDENTE, 1)
        assertEquals(2, partyPaginadas.parties.size)
    }

    @Test
    fun siLePidoElTotalMeDevuelveLos12() {
        partyPaginadas.ordenarParties(Orden.DERROTAS, Direccion.ASCENDENTE, 1)
        assertEquals(12, partyPaginadas.total)
    }

    @Test
    fun siPidoLaPagina0DeLasPartiesOrdenadasPorDerrotasAscendenteMeLasDevuelveConEseOrden() {
        partyPaginadas.ordenarParties(Orden.DERROTAS, Direccion.ASCENDENTE, 0)
        assertEquals(10, partyPaginadas.parties.size)
        assertEquals("party12", partyPaginadas.parties[0].nombre)
        assertEquals("party2", partyPaginadas.parties[1].nombre)
        assertEquals("party3", partyPaginadas.parties[2].nombre)
        assertEquals("party4", partyPaginadas.parties[3].nombre)
        assertEquals("party5", partyPaginadas.parties[4].nombre)
        assertEquals("party6", partyPaginadas.parties[5].nombre)
        assertEquals("party7", partyPaginadas.parties[6].nombre)
        assertEquals("party8", partyPaginadas.parties[7].nombre)
        assertEquals("party9", partyPaginadas.parties[8].nombre)
        assertEquals("party10", partyPaginadas.parties[9].nombre)
    }

    @Test
    fun siPidoLaPaginaUnoDeLasPartiesOrdenadasPorDerrotasAscendenteMeLasDevuelveConEseOrden() {
    partyPaginadas.ordenarParties(Orden.DERROTAS, Direccion.ASCENDENTE, 1)
    assertEquals(2, partyPaginadas.parties.size)
    assertEquals("party11", partyPaginadas.parties[0].nombre)
    assertEquals("party1", partyPaginadas.parties[1].nombre)
    }

    @Test
    fun ordenarPartiesPorDerrotasDescendenteMeLasDevuelveConEseOrden(){
        partyPaginadas.ordenarParties(Orden.DERROTAS, Direccion.DESCENDENTE, 0)
        assertEquals(10, partyPaginadas.parties.size)
        assertEquals("party1", partyPaginadas.parties[0].nombre)
        assertEquals("party11", partyPaginadas.parties[1].nombre)
        assertEquals("party10", partyPaginadas.parties[2].nombre)
        assertEquals("party9", partyPaginadas.parties[3].nombre)
        assertEquals("party8", partyPaginadas.parties[4].nombre)
        assertEquals("party7", partyPaginadas.parties[5].nombre)
        assertEquals("party6", partyPaginadas.parties[6].nombre)
        assertEquals("party5", partyPaginadas.parties[7].nombre)
        assertEquals("party4", partyPaginadas.parties[8].nombre)
        assertEquals("party3", partyPaginadas.parties[9].nombre)
    }

    @Test
    fun siPidoLaPaginaUnoDeLasPartiesOrdenadasPorDerrotasDescendenteMeLasDevuelveConEseOrden() {
        partyPaginadas.ordenarParties(Orden.DERROTAS, Direccion.DESCENDENTE, 1)
        assertEquals(2, partyPaginadas.parties.size)
        assertEquals("party2", partyPaginadas.parties[0].nombre)
        assertEquals("party12", partyPaginadas.parties[1].nombre)
    }

    @Test
    fun siPidoLaPagina0DeLasPartiesOrdenadasPorVictoriasDescendenteMeLasDevuelveConEseOrden(){
        partyPaginadas.ordenarParties(Orden.VICTORIAS, Direccion.DESCENDENTE, 0)
        assertEquals(10, partyPaginadas.parties.size)
        assertEquals("party12", partyPaginadas.parties[0].nombre)
        assertEquals("party1", partyPaginadas.parties[1].nombre)
        assertEquals("party2", partyPaginadas.parties[2].nombre)
        assertEquals("party3", partyPaginadas.parties[3].nombre)
        assertEquals("party4", partyPaginadas.parties[4].nombre)
        assertEquals("party5", partyPaginadas.parties[5].nombre)
        assertEquals("party6", partyPaginadas.parties[6].nombre)
        assertEquals("party7", partyPaginadas.parties[7].nombre)
        assertEquals("party8", partyPaginadas.parties[8].nombre)
        assertEquals("party9", partyPaginadas.parties[9].nombre)
    }

    @Test
    fun siPidoLaPaginaUnoDeLasPartiesOrdenadasPorVictoriasDescendenteMeLasDevuelveConEseOrden(){
        partyPaginadas.ordenarParties(Orden.VICTORIAS, Direccion.DESCENDENTE, 1)
        assertEquals(2, partyPaginadas.parties.size)
        assertEquals("party10", partyPaginadas.parties[0].nombre)
        assertEquals("party11", partyPaginadas.parties[1].nombre)
    }

    @Test
    fun siPidoLaPagina0DeLasPartiesOrdenadasPorVictoriasAscendenteMeLasDevuelveConEseOrden(){
        partyPaginadas.ordenarParties(Orden.VICTORIAS, Direccion.ASCENDENTE, 0)
        assertEquals(10, partyPaginadas.parties.size)
        assertEquals("party11", partyPaginadas.parties[0].nombre)
        assertEquals("party10", partyPaginadas.parties[1].nombre)
        assertEquals("party9", partyPaginadas.parties[2].nombre)
        assertEquals("party8", partyPaginadas.parties[3].nombre)
        assertEquals("party7", partyPaginadas.parties[4].nombre)
        assertEquals("party6", partyPaginadas.parties[5].nombre)
        assertEquals("party5", partyPaginadas.parties[6].nombre)
        assertEquals("party4", partyPaginadas.parties[7].nombre)
        assertEquals("party3", partyPaginadas.parties[8].nombre)
        assertEquals("party2", partyPaginadas.parties[9].nombre)
    }

    @Test
    fun siPidoLaPaginaUnoDeLasPartiesOrdenadasPorVictoriasAscendenteMeLasDevuelveConEseOrden(){
        partyPaginadas.ordenarParties(Orden.VICTORIAS, Direccion.ASCENDENTE, 1)
        assertEquals(2, partyPaginadas.parties.size)
        assertEquals("party1", partyPaginadas.parties[0].nombre)
        assertEquals("party12", partyPaginadas.parties[1].nombre)
    }

    @Test
    fun siPidoLaPagina2DeLas12PeleasDeLaPartyDevuelveLaListaVacia() {
        partyPaginadas.ordenarParties(Orden.VICTORIAS, Direccion.DESCENDENTE,2)
        assertTrue(partyPaginadas.parties.isEmpty())
    }
}