package ar.edu.unq.divicuentas.mapping.dto

import ar.edu.unq.divicuentas.modelo.Order
import ar.edu.unq.divicuentas.modelo.Product

class OrderDTO(
    var id : Long?,
    var owner: String,
    var  products: MutableList<Product>
) {

    fun toModel(): Order {
        return Order(this.owner, this.products)
    }

    companion object {
        fun toDTO(order: Order): OrderDTO {
            return OrderDTO(order.id , order.owner, order.products)
        }
    }
}