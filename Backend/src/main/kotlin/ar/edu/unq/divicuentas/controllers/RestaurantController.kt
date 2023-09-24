package ar.edu.unq.divicuentas.controllers

import ar.edu.unq.divicuentas.controllers.dto.RestaurantDTO
import ar.edu.unq.divicuentas.modelo.Restaurant
import ar.edu.unq.divicuentas.service.IRestaurantService
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@ServiceREST
@RequestMapping("/restaurant")
class RestaurantController {

    @Autowired
    lateinit var service: IRestaurantService
    @GetMapping
    fun restaurant(id: Long) : RestaurantDTO {
        val restaurant = service.getById(id) ?: throw NotFoundException("Restaurante no encontrado.")
        return RestaurantDTO.fromDTO(restaurant)
    }
    @PostMapping
    fun createRestaurant(@RequestBody restaurantDTO: RestaurantDTO): RestaurantDTO {
        return RestaurantDTO.fromDTO(service.create(restaurantDTO.aModelo()))
    }
}