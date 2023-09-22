package ar.edu.unq.epers.tactics.spring.modelo

class PartyPaginadas (var parties: List<Party>) {
    val total = parties.size

    fun ordenarParties(orden: Orden, direccion: Direccion, pagina: Int?) {
        when (orden) {
            Orden.PODER -> ordenarPorPoder(direccion, pagina ?: 0)
            Orden.VICTORIAS -> ordenarPorVictorias(direccion, pagina ?: 0)
            Orden.DERROTAS -> ordenarPorDerrotas(direccion, pagina ?: 0)
        }
    }

    fun ordenarPorPoder(direccion: Direccion, pagina: Int) {
        when (direccion) {
            Direccion.DESCENDENTE -> ordenarPoderDESC(pagina)
            Direccion.ASCENDENTE -> ordenarPoderASC(pagina)
        }
    }

    private fun ordenarPoderASC(pagina: Int) {
        parties = parties.sortedBy { party -> party.poder() }
        definirPageDeParty(pagina)
    }

    private fun ordenarPoderDESC(pagina: Int) {
        parties = parties.sortedByDescending { party -> party.poder() }
        definirPageDeParty(pagina)
    }

    private fun ordenarPorVictorias(direccion: Direccion, pagina: Int) {
        when (direccion) {
            Direccion.DESCENDENTE -> ordenarVictoriasDESC(pagina)
            Direccion.ASCENDENTE -> ordenarVictoriasASC(pagina)
        }
    }

    private fun ordenarVictoriasDESC(pagina: Int) {
        parties = parties.sortedByDescending { p -> p.victorias}
        definirPageDeParty(pagina)
    }


    private fun ordenarVictoriasASC(pagina: Int) {
        parties = parties.sortedBy { p -> p.victorias }
        definirPageDeParty(pagina)
    }
    
    private fun ordenarPorDerrotas(direccion: Direccion, pagina: Int) {
        when (direccion) {
            Direccion.DESCENDENTE -> ordenarDerrotaDESC(pagina)
            Direccion.ASCENDENTE -> ordenarDerrotaASC(pagina)
        }
    }

    private fun ordenarDerrotaASC(pagina: Int) {
        parties = parties.sortedBy { p -> p.derrotas }
        definirPageDeParty(pagina)
    }

    private fun ordenarDerrotaDESC(pagina: Int) {
        parties = parties.sortedByDescending { p -> p.derrotas }
        definirPageDeParty(pagina)
    }

    fun definirPageDeParty(pagina: Int){
        parties = parties.slice((pagina*10).coerceAtMost(total)..((pagina*10)+9).coerceAtMost(total-1))
    }
}

enum class Orden {
    PODER,
    VICTORIAS,
    DERROTAS
}

enum class Direccion {
    ASCENDENTE,
    DESCENDENTE;
}