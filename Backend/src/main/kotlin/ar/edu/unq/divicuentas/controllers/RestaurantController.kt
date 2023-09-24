package ar.edu.unq.divicuentas.controllers

import ar.edu.unq.divicuentas.mapping.RestaurantMapper
import ar.edu.unq.divicuentas.mapping.dto.RestaurantDTO
import ar.edu.unq.divicuentas.modelo.Restaurant
import ar.edu.unq.divicuentas.service.IRestaurantService
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@ServiceREST
@RequestMapping("/restaurant")
class RestaurantController {

    @Autowired
    lateinit var service: IRestaurantService
    @Autowired
    lateinit var restaurantMapper: RestaurantMapper

    @GetMapping("/{id}")
    fun restaurant(@PathVariable id: Long) : RestaurantDTO {
        val restaurant = service.getById(id) ?: throw NotFoundException("Restaurante no encontrado.")
        return restaurantMapper.toDTO(restaurant)
    }

    @PostMapping
    fun createRestaurant(@RequestBody restaurantDTO: RestaurantDTO): RestaurantDTO {
        return restaurantMapper.toDTO(service.create(restaurantMapper.toModel(restaurantDTO)))
    }

    @GetMapping("/all")
    fun restaurants(): List<RestaurantDTO>{
        return service.restaurants().map { restaurant -> restaurantMapper.toDTO(restaurant) }
    }
}