package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.modelo.habilidades.*
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes(
        JsonSubTypes.Type(value = AtaqueDTO::class, name = "Attack"),
        JsonSubTypes.Type(value = DefensaDTO::class, name = "Defend"),
        JsonSubTypes.Type(value = CurarDTO::class, name = "Heal"),
        JsonSubTypes.Type(value = AtaqueMagicoDTO::class, name = "MagicAttack"),
        JsonSubTypes.Type(value = MeditarDTO::class, name = "Meditate")
)
abstract class HabilidadDTO() {
    companion object {

        fun desdeModelo(habilidad: Habilidad): HabilidadDTO {
            return when (habilidad.tipo) {
               "ataque"  -> AtaqueDTO(habilidad.tipo, (habilidad as Atacar).danio, habilidad.prec, AventureroDTO.desdeModelo(habilidad.receptor))
               "defensa" -> DefensaDTO(habilidad.tipo, AventureroDTO.desdeModelo((habilidad as Defender).emisor), AventureroDTO.desdeModelo(habilidad.receptor))
               "curacion" -> CurarDTO(habilidad.tipo, (habilidad as Curar).curacion, AventureroDTO.desdeModelo(habilidad.receptor))
               "ataqueMagico" -> AtaqueMagicoDTO(habilidad.tipo, (habilidad as AtacarMagia).poderMagico, habilidad.nivel, AventureroDTO.desdeModelo(habilidad.receptor))
               "meditacion" -> MeditarDTO(habilidad.tipo, AventureroDTO.desdeModelo(habilidad.receptor))
                else -> { HabilidadNulaDTO("nula", AventureroDTO.desdeModelo(habilidad.receptor)) }
            }
        }
    }

    abstract fun aModelo(): Habilidad

}

class HabilidadNulaDTO(var tipo: String, val objetivo: AventureroDTO) : HabilidadDTO(){
    override fun aModelo() = HabilidadNula(objetivo.aModelo())
}

data class AtaqueDTO(val tipo: String, val daño: Double, val precisionFisica: Double, val objetivo: AventureroDTO) : HabilidadDTO() {
    override fun aModelo() = Atacar(daño, precisionFisica, objetivo.aModelo())
}

class DefensaDTO(val tipo: String, val source: AventureroDTO, val objetivo: AventureroDTO) : HabilidadDTO() {
    override fun aModelo() = Defender(source.aModelo(), objetivo.aModelo())
}

data class CurarDTO(val tipo: String, val poderMagico: Double, val objetivo: AventureroDTO) : HabilidadDTO() {
    override fun aModelo() = Curar(poderMagico, objetivo.aModelo())
}

data class AtaqueMagicoDTO(val tipo: String, val poderMagico: Double, val sourceLevel: Int, val objetivo: AventureroDTO) : HabilidadDTO() {
    override fun aModelo() = AtacarMagia(poderMagico, sourceLevel, objetivo.aModelo())
}

class MeditarDTO(val tipo: String, val objetivo: AventureroDTO) : HabilidadDTO() {
    override fun aModelo() = Meditar(objetivo.aModelo())
}