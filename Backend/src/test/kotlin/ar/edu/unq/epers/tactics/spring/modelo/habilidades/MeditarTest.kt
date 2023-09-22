package ar.edu.unq.epers.tactics.spring.modelo.habilidades

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Meditar
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MeditarTest {
    lateinit var meditar: Meditar
    lateinit var emisor: Aventurero

    @BeforeEach
    fun prepare() {
        emisor = Aventurero( "fercho, el maguito", "imagen.jpg", 1.0, 1.0, 2.0, 5.0)
        emisor.subirTantosNiveles(3)
        meditar = Meditar(emisor)
    }

    @Test
    fun trasUsarMagiaYLuegoMeditarElAventureroRecuperaMana(){
        assertEquals(emisor.manaActual, 9.0)
        emisor.modificarMana(-5)
        meditar.aplicar()
        assertEquals(emisor.manaActual, 8.0)
    }

    @Test
    fun trasQuererMeditarNoSeSuperaElNivelMaximoDeMana(){
        assertEquals(emisor.manaActual, 9.0)
        emisor.modificarMana(-2)
        meditar.aplicar()
        assertEquals(emisor.manaActual, 9.0)
    }

}