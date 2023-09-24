package ar.edu.unq.divicuentas.modelo

import javax.persistence.*

@Entity
class Restaurant(
    var name: String,
    var direction: String,
    var type: String,
    var image: String,
    @OneToMany(fetch = FetchType.EAGER
        ,cascade = [CascadeType.ALL]) var menu: MutableList<Product>
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}