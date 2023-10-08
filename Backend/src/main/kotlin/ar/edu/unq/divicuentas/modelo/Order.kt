package ar.edu.unq.divicuentas.modelo

import javax.persistence.*

@Entity
@Table(name = "`order`")
class Order(
    var owner: String,
    products: List<Product>) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL])
    var products: MutableList<Product> = mutableListOf()

    fun update(owner: String, products: MutableList<Product>): Order {
        this.owner = owner
        this.products = products
        return this
    }

    init {
        this.owner = owner
        this.products = products.toMutableList()
    }

}