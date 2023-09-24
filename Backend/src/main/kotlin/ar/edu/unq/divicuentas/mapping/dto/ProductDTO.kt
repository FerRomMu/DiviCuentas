package ar.edu.unq.divicuentas.mapping.dto

import ar.edu.unq.divicuentas.modelo.Product

class ProductDTO(
    var name: String,
    var image: String,
    var description: String,
    var price: Double
) {

    fun toModel(): Product {
        return Product(this.name, this.image, this.description, this.price)
    }

    companion object {
        fun toDTO(product: Product): ProductDTO {
            return ProductDTO(product.name, product.image, product.description, product.price)
        }
    }

}