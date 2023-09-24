package ar.edu.unq.divicuentas.mapping

import ar.edu.unq.divicuentas.mapping.dto.RestaurantDTO
import ar.edu.unq.divicuentas.modelo.Restaurant
import ar.edu.unq.divicuentas.service.IProductService
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RestaurantMapper(@Autowired val productService: IProductService) {

    fun toModel(dto: RestaurantDTO): Restaurant {
        val menuProducts = dto.menu.map { productId ->
            try {
                productService.getById(productId)!!
            } catch (e: NullPointerException) {
                throw NotFoundException("El producto con $productId no fue encontrado.")
            }
        }
        return Restaurant(dto.name, dto.direction, dto.type, dto.image, menuProducts)
    }

    fun toDTO(restaurant: Restaurant): RestaurantDTO {
        return RestaurantDTO(
            restaurant.name,
            restaurant.direction,
            restaurant.type,
            restaurant.image,
            restaurant.menu.map { prod ->
                prod.id!!
            }
        )
    }
}