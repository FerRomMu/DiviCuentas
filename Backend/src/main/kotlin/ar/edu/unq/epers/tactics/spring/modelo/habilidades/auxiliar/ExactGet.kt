package ar.edu.unq.epers.tactics.spring.modelo.habilidades.auxiliar

class ExactGet(private val valueToGet: Int): ModGet {
    override fun value(): Int { return valueToGet }
}