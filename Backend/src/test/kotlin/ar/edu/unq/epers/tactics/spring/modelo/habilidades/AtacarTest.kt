package ar.edu.unq.epers.tactics.spring.modelo.habilidades

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.auxiliar.ExactGet
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Atacar
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AtacarTest {
    lateinit var atacar: Atacar
    lateinit var emisor: Aventurero
    lateinit var receptor: Aventurero

    @BeforeEach
    fun prepare() {
        emisor = Aventurero( "juancho, el asesino", "imagen.jpg",5.0,4.0,1.0,1.0)
        receptor = Aventurero("Chofi, la guerrera", "imagen.jpg", 1.0,4.0, 5.0, 1.0)
        atacar = Atacar(emisor.dañoF(),emisor.pres(),receptor)
    }

    @Test
    fun trasAtacarYAcertarElReceptorPierdeVida(){
        assertEquals(receptor.vidaActual, 16.0)
        atacar.aplicar()
        assertEquals(receptor.vidaActual, 8.0)
    }

    @Test
    fun trasAtacarYNoAcertarElReceptorNoPierdeVida(){
        assertEquals(receptor.vidaActual, 16.0)
        atacar.modificador = ExactGet(-3)
        atacar.aplicar()
        assertEquals(receptor.vidaActual, 16.0)
    }
}