package ar.edu.unq.epers.tactics.spring.modelo.habilidades.auxiliar

class RandomGet: ModGet {
    override fun value(): Int { return (1..20).random() }
}