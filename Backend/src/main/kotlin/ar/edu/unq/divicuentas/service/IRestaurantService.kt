package ar.edu.unq.divicuentas.service

import ar.edu.unq.divicuentas.modelo.Product
import ar.edu.unq.divicuentas.modelo.Restaurant

interface IRestaurantService {
    fun create(restaurant: Restaurant): Restaurant
    fun update(restaurant: Restaurant): Restaurant
    fun deleteById(restaurantId: Long)
    fun getById(restaurantId: Long): Restaurant?
    fun restaurants(): List<Restaurant>
    fun productsOf(restaurantId: Long): List<Product>
}