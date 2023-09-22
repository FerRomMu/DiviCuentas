package ar.edu.unq.epers.tactics.spring.modelo.habilidades

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Curar
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Habilidad
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CurarTest {
    lateinit var curar: Habilidad
    lateinit var emisor: Aventurero
    lateinit var receptor: Aventurero


    @BeforeEach
    fun prepare() {
        receptor = Aventurero( "juancho, el asesino", "imagen.jpg",5.0,4.0,1.0,1.0)
        emisor = Aventurero( "fercho, el maguito", "imagen.jpg",1.0, 1.0, 2.0, 7.0)
        curar = Curar(emisor.dañoM(),receptor)
    }

    @Test
    fun alEmisorCurarSuReceptoSeCura(){
        receptor.recibirDaño(8.0)
        Assertions.assertEquals(receptor.vidaActual, 4.0)
        curar.aplicar()
        Assertions.assertEquals(receptor.vidaActual, 12.0)
    }

    @Test
    fun alEmisorCurarSuReceptoSeCuraSinSuperarSuMaximoDeVida(){
        receptor.recibirDaño(1.0)
        Assertions.assertEquals(receptor.vidaActual, 11.0)
        curar.aplicar()
        Assertions.assertEquals(receptor.vidaActual, 12.0)
    }
}