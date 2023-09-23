package ar.edu.unq.divicuentas.persistencia

import ar.edu.unq.divicuentas.modelo.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IRestaurantRepository : JpaRepository<Restaurant, Long>