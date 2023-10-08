package ar.edu.unq.divicuentas.service.impl

import ar.edu.unq.divicuentas.modelo.Order
import ar.edu.unq.divicuentas.persistencia.IOrderRepository
import ar.edu.unq.divicuentas.service.IOrderService
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService: IOrderService {
    @Autowired lateinit var repository: IOrderRepository
    override fun create(order: Order): Order {
        return repository.save(order)
    }

    override fun getById(orderId: Long): Order? {
        return repository.findById(orderId).orElse(null)
    }

    override fun update(order: Order): Order {
        val existingOrder = repository.findByOwner(order.owner)
        val updatedOrder = existingOrder.update(order.owner, order.products)

        return repository.save(updatedOrder)
    }

    override fun getByOwner(owner: String): Order {
        return repository.findByOwner(owner)
    }


}