package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.modelo.Aventurero
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HabilidadDTOTest {

    lateinit var ataque: Atacar
    lateinit var ataqueMagico: AtacarMagia
    lateinit var defensa: Defender
    lateinit var curar: Curar
    lateinit var meditar: Meditar
    lateinit var unAventurero: Aventurero
    lateinit var otroAventurero: Aventurero
    lateinit var unAventureroDTO: AventureroDTO

    @BeforeEach
    fun prepare() {
        unAventurero = Aventurero("chofi", "img", 100.0, 100.0, 30.0, 100.0)
        unAventurero.id = 2
        otroAventurero = Aventurero("fercho", "img", 80.0, 100.0, 70.0, 100.0)
        otroAventurero.id = 3

        ataque = Atacar(20.0,2.0, unAventurero)
        ataqueMagico = AtacarMagia(20.0,2,unAventurero)
        defensa = Defender(unAventurero, otroAventurero)
        curar = Curar(10.0, unAventurero)
        meditar = Meditar(unAventurero)

        unAventureroDTO = AventureroDTO.desdeModelo(unAventurero)
    }

    @Test
    fun meditarSePasaCorrectamenteAMeditarDTO(){
        var meditarDTO: MeditarDTO = HabilidadDTO.desdeModelo(meditar) as MeditarDTO
        Assertions.assertEquals( "meditacion", meditarDTO.tipo)
        Assertions.assertEquals( 2, meditarDTO.objetivo.id)
    }

    @Test
    fun curarSePasaCorrectamenteACurarDTO(){
        var curarDTO: CurarDTO = HabilidadDTO.desdeModelo(curar) as CurarDTO
        Assertions.assertEquals( 10.0, curarDTO.poderMagico)
        Assertions.assertEquals( "curacion", curarDTO.tipo)
        Assertions.assertEquals( 2, curarDTO.objetivo.id)
    }

    @Test
    fun unAtaqueSePasaCorrectamenteAAtaqueDTO(){
        var ataqueDTO: AtaqueDTO = HabilidadDTO.desdeModelo(ataque) as AtaqueDTO
        Assertions.assertEquals( 20.0, ataqueDTO.daño)
        Assertions.assertEquals( 2.0, ataqueDTO.precisionFisica)
        Assertions.assertEquals( "ataque", ataqueDTO.tipo)
        Assertions.assertEquals( 2, ataqueDTO.objetivo.id)
    }

    @Test
    fun unAtaqueMagicoSePasaCorrectamenteAAtaqueMagicoDTO(){
        var ataqueDTO: AtaqueMagicoDTO = HabilidadDTO.desdeModelo(ataqueMagico) as AtaqueMagicoDTO
        Assertions.assertEquals( 20.0, ataqueDTO.poderMagico)
        Assertions.assertEquals( 2, ataqueDTO.sourceLevel)
        Assertions.assertEquals( "ataqueMagico", ataqueDTO.tipo)
        Assertions.assertEquals( 2, ataqueDTO.objetivo.id)
    }

    @Test
    fun unaDefensaSePasaCorrectamenteADefensaDTO(){
        var defensaDTO: DefensaDTO = HabilidadDTO.desdeModelo(defensa) as DefensaDTO
        Assertions.assertEquals( "defensa", defensaDTO.tipo)
        Assertions.assertEquals( 3, defensaDTO.objetivo.id)
        Assertions.assertEquals( 2, defensaDTO.source.id)
    }

    @Test
    fun unAtaqueDTOPasaCorrectamenteAModelo(){
        var unAtaqueDTO = AtaqueDTO("ataque", 20.0, 2.0, unAventureroDTO)
        var enModelo = unAtaqueDTO.aModelo()
        Assertions.assertEquals( 2, enModelo.receptor.id)
        Assertions.assertEquals( 20.0, enModelo.danio)
        Assertions.assertEquals( 2.0, enModelo.prec)
    }

    @Test
    fun unAtaqueMagicoDTOPasaCorrectamenteAModelo(){
        var unAtaqueDTO = AtaqueMagicoDTO("ataqueMagico", 20.0, 2, unAventureroDTO)
        var enModelo = unAtaqueDTO.aModelo()
        Assertions.assertEquals( 2, enModelo.receptor.id)
        Assertions.assertEquals( 20.0, enModelo.poderMagico)
        Assertions.assertEquals( 2, enModelo.nivel)
    }

    @Test
    fun defensaDTOPasaCorrectamenteAModelo(){
        var defensaDTO = DefensaDTO("defensa", unAventureroDTO, AventureroDTO.desdeModelo(otroAventurero))
        var enModelo = defensaDTO.aModelo()
        Assertions.assertEquals( 3, enModelo.receptor.id)
        Assertions.assertEquals( 2, enModelo.emisor.id)
    }

    @Test
    fun curarDTOPasaCorrectamenteAModelo(){
        var curarDTO = CurarDTO("curacion", 20.0, unAventureroDTO)
        var enModelo = curarDTO.aModelo()
        Assertions.assertEquals( 2, enModelo.receptor.id)
        Assertions.assertEquals( 20.0, enModelo.curacion)
    }

    @Test
    fun meditarDTOPasaCorrectamenteAModelo(){
        var meditarDTO = MeditarDTO("meditacion", unAventureroDTO)
        var enModelo = meditarDTO.aModelo()
        Assertions.assertEquals( 2, enModelo.receptor.id)
    }
}