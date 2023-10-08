package ar.edu.unq.divicuentas.mapping.dto

import ar.edu.unq.divicuentas.modelo.Order
import ar.edu.unq.divicuentas.modelo.Product
import ar.edu.unq.divicuentas.modelo.Restaurant
import javassist.NotFoundException

class OrderDTO(
    var id : Long?,
    var owner: String,
    var products: MutableList<ProductDTO>
) {

    fun toModel(): Order {
        val menuProducts = products.map { productDTO ->
            productDTO.toModel()
        }
        return Order(this.owner, menuProducts)
    }

    companion object {
        fun toDTO(order: Order): OrderDTO {
            val productsDTO = order.products.map { product ->
                ProductDTO.toDTO(product)
            }
            return OrderDTO(order.id , order.owner, productsDTO as MutableList<ProductDTO>)
        }
    }
}