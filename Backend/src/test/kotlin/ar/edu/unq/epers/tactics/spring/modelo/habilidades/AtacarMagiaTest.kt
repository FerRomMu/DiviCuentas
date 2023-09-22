package ar.edu.unq.epers.tactics.spring.modelo.habilidades

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.auxiliar.ExactGet
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.AtacarMagia
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AtacarMagiaTest {
    lateinit var ataMag: AtacarMagia
    lateinit var emisor: Aventurero
    lateinit var receptor: Aventurero

    @BeforeEach
    fun prepare() {
        emisor = Aventurero( "fercho, el maguito", "imagen.jpg", 1.0,1.0,2.0,7.0)
        receptor = Aventurero("Chofi, la guerrera", "imagen.jpg", 1.0,4.0,5.0,1.0)
        ataMag = AtacarMagia(emisor.dañoM(),emisor.nivel,receptor)
    }

    @Test
    fun trasAtacarYAcertarElReceptorPierdeVida(){
        ataMag.modificador = ExactGet(2)
        Assertions.assertEquals(receptor.vidaActual, 16.0)
        ataMag.aplicar()
        Assertions.assertEquals(receptor.vidaActual, 8.0)
    }

    @Test
    fun trasAtacarYFallarElReceptorNoPierdeVida(){
        ataMag.modificador = ExactGet(0)
        Assertions.assertEquals(receptor.vidaActual, 16.0)
        ataMag.aplicar()
        Assertions.assertEquals(receptor.vidaActual, 16.0)
    }
}