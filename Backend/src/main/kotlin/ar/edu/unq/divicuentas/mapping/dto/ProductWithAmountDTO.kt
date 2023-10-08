package ar.edu.unq.divicuentas.mapping.dto

import ar.edu.unq.divicuentas.modelo.Product

class ProductWithAmountDTO(
    var name: String,
    var image: String,
    var description: String,
    var price: Double,
    var amount: Int
) {

    fun toModel(): Product {
        return Product(this.name, this.image, this.description, this.price)
    }

    companion object {
        fun toDTO(product: Product, amount: Int): ProductWithAmountDTO {
            return ProductWithAmountDTO(product.name, product.image, product.description, product.price, amount)
        }
    }
}