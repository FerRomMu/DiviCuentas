package ar.edu.unq.divicuentas.service.impl

import ar.edu.unq.divicuentas.modelo.Product
import ar.edu.unq.divicuentas.modelo.Restaurant
import ar.edu.unq.divicuentas.persistencia.IRestaurantRepository
import ar.edu.unq.divicuentas.service.IRestaurantService
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RestaurantService: IRestaurantService {
    @Autowired
    lateinit var repository: IRestaurantRepository

    override fun create(restaurant: Restaurant): Restaurant {
        return repository.save(restaurant)
    }

    override fun update(restaurant: Restaurant): Restaurant {
        return repository.save(restaurant)
    }

    override fun deleteById(restaurantId: Long) {
        repository.deleteById(restaurantId)
    }

    override fun getById(restaurantId: Long): Restaurant? {
        return repository.findById(restaurantId).orElse(null)
    }

    override fun restaurants(): List<Restaurant> {
        return repository.findAll()
    }

    override fun productsOf(restaurantId: Long): List<Product> {
        return repository.findById(restaurantId)
            .orElseThrow { throw NotFoundException("Restaurante no encontrado.") }
            .menu
    }

}