package ar.edu.unq.epers.tactics.spring.modelo

class PeleasPaginadas(var peleas:List<Pelea>) {
    val total = peleas.size

    fun ordenarPeleas(pagina: Int) {
            peleas = peleas.sortedByDescending { pelea -> pelea.date }
            peleas = peleas.slice(pagina*10.coerceAtMost(total)..((pagina*10)+9).coerceAtMost(total-1))
    }
}