package ar.edu.unq.divicuentas.controllers.dto

import ar.edu.unq.divicuentas.modelo.Product
import ar.edu.unq.divicuentas.modelo.Restaurant
import ar.edu.unq.divicuentas.service.IProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RestaurantDTO(
    var name: String,
    var direction: String,
    var type: String,
    var image: String,
    var menu: List<Long>
) {

    @Autowired lateinit var service: IProductService

    fun aModelo(): Restaurant {
        return Restaurant(this.name, this.direction, this.type, this.image, this.menu.map { idP -> service.getById(idP) ?: throw Exception("sarasa") }.toMutableList())
    }

    companion object {
        fun fromDTO(restaurant: Restaurant): RestaurantDTO {
            return RestaurantDTO(
                restaurant.name,
                restaurant.direction,
                restaurant.type,
                restaurant.image,
                restaurant.menu.map { prod -> prod.id!! }
            )
        }
    }


}