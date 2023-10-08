package ar.edu.unq.divicuentas.persistencia

import ar.edu.unq.divicuentas.modelo.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IOrderRepository : JpaRepository<Order, Long> {
    fun findByOwner(owner: String): Order
}