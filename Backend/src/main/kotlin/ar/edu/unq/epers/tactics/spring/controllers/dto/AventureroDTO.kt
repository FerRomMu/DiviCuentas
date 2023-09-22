package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.modelo.Atributo
import ar.edu.unq.epers.tactics.spring.modelo.Aventurero

data class AventureroDTO(var id: Long?, var nivel: Int, var nombre: String, var imagenURL: String, var dañoRecibido: Double, var tacticas: List<TacticaDTO>, var atributos: AtributosDTO) {

    companion object {

        fun desdeModelo(aventurero: Aventurero): AventureroDTO {
            val avDTO =
                AventureroDTO(
                    id = aventurero.id,
                    nivel = aventurero.nivel,
                    nombre = aventurero.nombre,
                    imagenURL = aventurero.imagen,
                    dañoRecibido = aventurero.vida() - aventurero.vidaActual,
                    tacticas = aventurero.tacticas
                            .map { t -> TacticaDTO.desdeModelo(t) },
                    atributos = AtributosDTO.desdeModelo(aventurero.atributos)
                )
            return avDTO
        }
    }

    fun aModelo(): Aventurero {
        var aventurero = Aventurero(nombre,imagenURL)
        aventurero.id = this.id
        aventurero.subirTantosNiveles(nivel - 1)
        aventurero.tacticas = this.tacticas.map { tactica -> tactica.aModelo() }.toMutableList()
        aventurero.atributos = this.atributos.aModelo()
        aventurero.resetAventurero()
        aventurero.recibirDaño(this.dañoRecibido)

        return aventurero
    }

    fun actualizarModelo(aventurero: Aventurero)  {
        aventurero.id = this.id
        aventurero.nombre = this.nombre
        aventurero.imagen = this.imagenURL
        aventurero.tacticas = this.tacticas.map { tactica -> tactica.aModelo() }.toMutableList()
        aventurero.subirTantosNiveles(nivel - 1)
        aventurero.atributos = this.atributos.aModelo()
        aventurero.resetAventurero()
        aventurero.recibirDaño(this.dañoRecibido)
    }
}

data class AtributosDTO(var id: Long?, var fuerza: Double, var destreza: Double, var constitucion: Double, var inteligencia: Double) {

    companion object {
        fun desdeModelo(atributos: Atributo): AtributosDTO {
            val atDTO =
                AtributosDTO(
                    id = atributos.id,
                    fuerza = atributos.fuerza,
                    destreza = atributos.destreza,
                    constitucion = atributos.constitucion,
                    inteligencia = atributos.inteligencia
                )
            return atDTO
        }
    }

    fun aModelo(): Atributo {
        var atributo = Atributo()
        atributo.setAtributes(fuerza,destreza,constitucion,inteligencia)
        atributo.id = id
        return atributo
    }
}