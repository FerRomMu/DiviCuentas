package ar.edu.unq.epers.tactics.spring.modelo
import ar.edu.unq.epers.tactics.spring.exceptions.InvalidEmptyNameException
import ar.edu.unq.epers.tactics.spring.exceptions.InvalidValueException
import ar.edu.unq.epers.tactics.spring.exceptions.NullPartyException
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Habilidad
import ar.edu.unq.epers.tactics.spring.modelo.habilidades.Meditar
import ar.edu.unq.epers.tactics.spring.modelo.tacticas.Tactica
import javax.persistence.*

@Entity
class Aventurero () {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, length = 500)
    lateinit var nombre: String
    lateinit var imagen: String
    @ManyToOne
    var party: Party? = null
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var tacticas: MutableList<Tactica> = mutableListOf()
    @OneToOne(cascade = [CascadeType.ALL])
    var atributos = Atributo()
    var nivel: Int = 1

    //Estadisticas
    fun vida(): Double = nivel*5 + atributos.constitucion*2 + atributos.fuerza
    fun armadura(): Double = nivel + atributos.constitucion
    fun mana(): Double = nivel + atributos.inteligencia
    fun velocidad(): Double = nivel + atributos.destreza
    fun dañoF(): Double = nivel + atributos.fuerza + (atributos.destreza / 2.0)
    fun dañoM(): Double = nivel + atributos.inteligencia
    fun pres(): Double = nivel + atributos.fuerza + atributos.destreza

    //Auxiliares
    var vidaActual = 0.0
    var manaActual = 0.0

    @OneToOne
    var defendido: Aventurero? = null

    @OneToMany
    var defensores: MutableList<Aventurero> = mutableListOf()

    fun addDefensor(aventurero: Aventurero){
        defensores.add(aventurero)
    }

    fun removeDefensor(aventurero: Aventurero){
        defensores.remove(aventurero)
    }

    fun setterDefendido(aventurero: Aventurero) {
        defendido = aventurero
    }

    fun dejarDeDefender(){
        defendido?.removeDefensor(this)
        defendido = null
    }

    fun subirTantosNiveles(cantidadASubir: Int) {
        if(cantidadASubir < 0) { throw InvalidValueException("El valor a subir debe ser positivo o 0.") }
        nivel += cantidadASubir
        this.resetAventurero()
    }

    fun recibirDaño(daño: Double) {
        this.vidaActual = (vidaActual - daño).coerceAtLeast(0.0)
    }

    fun curar(valor : Double) {
        if (vidaActual > 0.0) {
            vidaActual = (vidaActual + valor).coerceAtMost(this.vida())
        }
    }

    fun modificarMana(valor : Int) {
        manaActual = (manaActual + valor).coerceAtMost(this.mana()).coerceAtLeast(0.0)
    }

    fun setterParty(party: Party){
        this.party = party
    }

    final fun resetAventurero(){
        vidaActual = vida()
        manaActual = mana()
        defensores.clear()
        defendido = null
    }

    fun resolverTactica(enemigos: List<Aventurero>): Habilidad {
        dejarDeDefender()
        var hab: Habilidad? = null
        if (party == null) { throw NullPartyException("Para poder resolver una tactica el aventurero debe tener party.") }
        for(tactica in tacticas.sortedBy { it.prioridad }){
            hab = tactica.ejecutar(this, party!!.aventureros, enemigos)
            if(hab != null){
                disminuirMana(hab)
                break
            }
        }
        return hab ?: Meditar(this)
    }

    fun disminuirMana(habilidad: Habilidad){
        when(habilidad.tipo){
            "curacion" -> this.modificarMana(-5)
            "ataqueMagico" -> this.modificarMana(-5)
        }
    }

    fun recibirHabilidad(habilidad: Habilidad) {
        habilidad.receptor = this
        habilidad.aplicar()
    }

    constructor(nombre: String, img: String, fuerza: Double, destreza: Double, constitucion: Double, inteligencia: Double): this(nombre, img) {
        atributos.setAtributes(fuerza, destreza, constitucion, inteligencia)
        resetAventurero()
    }

    constructor(nombre: String, img: String): this(){
        this.validateData(nombre, img)
        this.nombre = nombre
        imagen = img
        resetAventurero()
    }

    private fun validateData(nombre: String, imagen: String) {
        if(nombre == "") { throw InvalidEmptyNameException("Un aventurero debe tener nombre.") }
        if(imagen == "") { throw InvalidEmptyNameException("Un aventurero debe tener una imagen.") }
    }
}