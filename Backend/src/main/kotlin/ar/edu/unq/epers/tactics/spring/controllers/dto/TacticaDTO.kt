package ar.edu.unq.epers.tactics.spring.controllers.dto

import ar.edu.unq.epers.tactics.spring.modelo.tacticas.*

data class TacticaDTO(var id: Long?, var prioridad: Int, var receptor: TipoDeReceptor, var tipoDeEstadistica: TipoDeEstadistica, var criterio: Criterio, var valor: Double, var accion: Accion
) {

    companion object {

        fun desdeModelo(tactica: Tactica): TacticaDTO {
            val tacDTO =
                TacticaDTO(
                    id = tactica.id,
                    prioridad =  tactica.prioridad!!,
                    receptor = tactica.receptor,
                    tipoDeEstadistica = tactica.estadistica,
                    criterio = tactica.criterio,
                    valor = tactica.valor,
                    accion = tactica.accion
                )
            return tacDTO
        }
    }

    fun aModelo(): Tactica {
        val tactica = Tactica(
            this.receptor,
            this.tipoDeEstadistica,
            this.criterio,
            this.valor,
            this.accion,
            this.prioridad
        )
        tactica.id = this.id
        return tactica
    }

    fun actualizarModelo(tactica: Tactica) {
        tactica.receptor = receptor
        tactica.estadistica = tipoDeEstadistica
        tactica.criterio = criterio
        tactica.valor = valor
        tactica.accion = accion
        tactica.prioridad = prioridad
        tactica.id = id
    }
}