package ar.edu.unq.divicuentas.modelo

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Restaurant(
    var name: String,
    var direction: String,
    var type: String,
    var image: String,
    @OneToMany var menu: MutableList<Product>
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}