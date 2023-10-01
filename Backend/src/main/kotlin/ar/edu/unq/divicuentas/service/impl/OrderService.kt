package ar.edu.unq.divicuentas.service.impl

import ar.edu.unq.divicuentas.modelo.Order
import ar.edu.unq.divicuentas.persistencia.IOrderRepository
import ar.edu.unq.divicuentas.service.IOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService: IOrderService {
    @Autowired lateinit var repository: IOrderRepository
    override fun create(order: Order): Order {
        return repository.save(order)
    }
}