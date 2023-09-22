package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.exceptions.BadAtributeException
import ar.edu.unq.epers.tactics.spring.exceptions.InvalidEmptyNameException
import ar.edu.unq.epers.tactics.spring.exceptions.InvalidValueException
import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.tacticas.*
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AventureroDTOTest {

    lateinit var fercho: Aventurero
    lateinit var atributoChofiDTO: AtributosDTO
    lateinit var chofiDTO: AventureroDTO

    @BeforeEach
    fun prepare() {
        fercho = Aventurero("fercho", "img.png",  80.0, 100.0, 70.0, 100.0)
        atributoChofiDTO = AtributosDTO(1,80.0,100.0,70.0,100.0)
        chofiDTO = AventureroDTO(1,3,"Chofi", "chofi.jpg", 10.0, emptyList<TacticaDTO>(), atributoChofiDTO)
    }

    @Test
    fun elAventureroSePasaCorrectamenteDesdeModelo() {
        val ferchoDTO = AventureroDTO.desdeModelo(fercho)
        Assertions.assertEquals(null, ferchoDTO.id)
        Assertions.assertEquals(1, ferchoDTO.nivel)
        Assertions.assertEquals("fercho", ferchoDTO.nombre)
        Assertions.assertEquals("img.png", ferchoDTO.imagenURL)
        Assertions.assertEquals(0.0, ferchoDTO.dañoRecibido)
        Assertions.assertTrue(ferchoDTO.tacticas.isEmpty())
    }

    @Test
    fun losAtributosSePasanCorrectamenteDesdeModelo() {
        val atributoDTO = AtributosDTO.desdeModelo(fercho.atributos)
        Assertions.assertEquals(80.0, atributoDTO.fuerza)
        Assertions.assertEquals(100.0, atributoDTO.destreza)
        Assertions.assertEquals(70.0, atributoDTO.constitucion)
        Assertions.assertEquals(100.0, atributoDTO.inteligencia)
    }

    @Test
    fun laAventureraSePasaCorrectamenteAModelo(){
        val chofi = chofiDTO.aModelo()
        Assertions.assertEquals(3, chofi.nivel)
        Assertions.assertEquals(1, chofi.id)
        Assertions.assertEquals("Chofi", chofi.nombre)
        Assertions.assertEquals("chofi.jpg", chofi.imagen)
        Assertions.assertEquals(80.0, chofi.atributos.fuerza)
        Assertions.assertEquals(100.0, chofi.atributos.destreza)
        Assertions.assertEquals(70.0, chofi.atributos.constitucion)
        Assertions.assertEquals(100.0, chofi.atributos.inteligencia)
        Assertions.assertEquals(225.0, chofi.vidaActual)
    }

    @Test
    fun elAventureroSeActualizaCorrectamente(){
        val nuevosAtributosDTO = AtributosDTO(2, 1.0, 1.0, 1.0, 1.0)
        val tacticaDTO = TacticaDTO(1,1, TipoDeReceptor.ALIADO, TipoDeEstadistica.ARMADURA, Criterio.MAYOR_QUE,20.0, Accion.CURAR)
        val nuevoFerchoDTO = AventureroDTO(2, 1, "ferchoDebil", "flaco.png",0.0, listOf(tacticaDTO) ,nuevosAtributosDTO)
        nuevoFerchoDTO.actualizarModelo(fercho)

        Assertions.assertEquals(2, fercho.id)
        Assertions.assertEquals(1, fercho.nivel)
        Assertions.assertEquals("ferchoDebil", fercho.nombre)
        Assertions.assertEquals("flaco.png", fercho.imagen)
        Assertions.assertEquals(8.0, fercho.vidaActual)
        Assertions.assertEquals(1.0, fercho.atributos.fuerza)
        Assertions.assertEquals(1.0, fercho.atributos.destreza)
        Assertions.assertEquals(1.0, fercho.atributos.constitucion)
        Assertions.assertEquals(1.0, fercho.atributos.inteligencia)
        Assertions.assertFalse(fercho.tacticas.isEmpty())
    }

    @Test
    fun siElAventureroDTOTieneAtributosInvalidosFallaAlPasarAModelo(){
        chofiDTO.atributos.constitucion = -1.0
        assertThrows<BadAtributeException> { chofiDTO.aModelo() }
    }

    @Test
    fun siElAventureroDTOTienenNivelInvalidoFallaAlPasarAModelo(){
        chofiDTO.nivel = -1
        assertThrows<InvalidValueException> { chofiDTO.aModelo() }
    }

    @Test
    fun siNoTieneNombreOImagenElAventureroDTOFallaAlPasarAModelo(){
        chofiDTO.imagenURL = ""
        assertThrows<InvalidEmptyNameException> { chofiDTO.aModelo() }

        chofiDTO.imagenURL = "unaImagen.jpg"
        chofiDTO.nombre = ""
        assertThrows<InvalidEmptyNameException> { chofiDTO.aModelo() }
    }
}