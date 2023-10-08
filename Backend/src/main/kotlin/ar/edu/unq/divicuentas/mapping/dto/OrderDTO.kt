package ar.edu.unq.divicuentas.mapping.dto

import ar.edu.unq.divicuentas.modelo.Order

class OrderDTO(
    var id: Long?,
    var owner: String,
    var products: List<ProductWithAmountDTO>
) {

    fun toModel(): Order {
        val menuProducts = products.map { productDTO ->
            productDTO.toModel()
        }
        return Order(this.owner, menuProducts)
    }

    companion object {
        fun toDTO(order: Order): OrderDTO {
            val productsWithAmount = order.products.groupingBy { it }.eachCount()
            val productsDTO = productsWithAmount.map { (product, amount) ->
                ProductWithAmountDTO.toDTO(product, amount)
            }
            return OrderDTO(order.id , order.owner, productsDTO)
        }
    }
}