package ar.edu.unq.epers.tactics.spring.modelo.habilidades

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.auxiliar.ExactGet
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Atacar
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Defender
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Habilidad
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Meditar
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefenderTest {
    lateinit var defender: Habilidad
    lateinit var atacar: Atacar
    lateinit var emisor: Aventurero
    lateinit var receptor: Aventurero
    lateinit var defensor: Aventurero

    @BeforeEach
    fun prepare() {
        emisor = Aventurero( "juancho, el asesino", "imagen.jpg",5.0,4.0,1.0,1.0)
        receptor =  Aventurero( "fercho, el maguito", "imagen.jpg",1.0, 1.0, 2.0, 7.0)
        defensor = Aventurero("Chofi, la guerrera", "imagen.jpg", 1.0,4.0,5.0,1.0)
        defender = Defender(defensor,receptor)
        atacar = Atacar(emisor.dañoF(), emisor.pres(),receptor)
    }

    @Test
    fun trasAtacarUnReceptorDefendidoElDefensorPierdeVida(){
        defender.aplicar()
        Assertions.assertEquals(defensor.vidaActual, 16.0)
        atacar.aplicar()
        Assertions.assertEquals(receptor.vidaActual, 10.0)
        Assertions.assertEquals(defensor.vidaActual, 8.0)
    }

    @Test
    fun trasAtacarUnReceptorDefendidoYFallarNadiePierdeVida(){
        defender.aplicar()
        Assertions.assertEquals(defensor.vidaActual, 16.0)
        atacar.modificador = ExactGet(-3)
        atacar.aplicar()
        Assertions.assertEquals(receptor.vidaActual, 10.0)
        Assertions.assertEquals(defensor.vidaActual, 16.0)
    }
}