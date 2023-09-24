package ar.edu.unq.divicuentas.controllers.dto

import ar.edu.unq.divicuentas.modelo.Product
import ar.edu.unq.divicuentas.modelo.Restaurant

class ProductDTO(
    var name: String,
    var image: String,
    var description: String,
    var price: Double
) {

    fun aModelo(): Product {
        return Product(this.name, this.image, this.description, this.price)
    }

    companion object {
        fun fromDTO(product: Product): ProductDTO {
            return ProductDTO(product.name, product.image, product.description, product.price)
        }
    }

}