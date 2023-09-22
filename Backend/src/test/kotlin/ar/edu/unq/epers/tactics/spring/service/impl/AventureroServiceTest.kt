package ar.edu.unq.epers.tactics.spring.service.impl

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.persistencia.AventureroDAO
import ar.edu.unq.epers.tactics.spring.service.AventureroService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestInstance(PER_CLASS)
class AventureroServiceTest {

    @Autowired
    lateinit var aventureroDao: AventureroDAO
    @Autowired
    lateinit var service: AventureroService

    lateinit var juancho : Aventurero
    lateinit var juancho2 : Aventurero
    lateinit var juancho3 : Aventurero

    @BeforeEach
    fun prepare() {
        juancho = Aventurero("juanc", "imagen.jpg")
        service.guardarAventurero(juancho)
    }

    @Test
    fun siRecuperoUnAventureroYaGuardadoTieneSusDatos(){
        juancho2 = service.recuperar(juancho.id!!)
        Assertions.assertEquals("juanc", juancho2.nombre)
        Assertions.assertEquals("imagen.jpg", juancho2.imagen)
    }

    @Test
    fun siActualizoAventureroYLoRecuperoTieneSusDatosActualizados(){
        juancho2 = service.recuperar(juancho.id!!)
        juancho2.nombre = "chofi"
        juancho3 = service.actualizar(juancho2)
        Assertions.assertEquals("chofi", juancho3.nombre)
    }

    @Test
    fun alEliminarAventureroSiLoTratoDeRecuperarNoObtengoNada() {
        service.eliminar(juancho)
        assertThrows<NoSuchElementException> { service.recuperar(juancho.id!!) }
    }

    @Test
    fun siIntentoActualizarAventureroQueNoExisteDebeFallar(){
        juancho2 = Aventurero("otroJuan", "img.jpg")
        juancho2.id = 111
        assertThrows<NoSuchElementException> { service.actualizar(juancho2) }
    }

    @Test
    fun siIntentoBorrarAventureroQueNoExisteDebeFallar(){
        var aventureroInexistente = Aventurero("mengano", "img.jpg")
        assertThrows<NoSuchElementException> { service.eliminar(aventureroInexistente) }
    }

    @Test
    fun siIntentoRecuperarAventureroQueNoExisteDebeFallar(){
        assertThrows<NoSuchElementException> { service.recuperar(1000000000000) }
    }

    @AfterEach
    fun cleanup(){
        service.eliminarTodos()
    }
}