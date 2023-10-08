package ar.edu.unq.divicuentas.controllers

import ar.edu.unq.divicuentas.mapping.dto.OrderDTO
import ar.edu.unq.divicuentas.service.IOrderService
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@ServiceREST
@RequestMapping("/menu/order")
class OrderController {

    @Autowired lateinit var service: IOrderService

    @PostMapping
    fun createOrder(@RequestBody orderDTO: OrderDTO): OrderDTO {
        return OrderDTO.toDTO(service.create(orderDTO.toModel()))
    }

    @GetMapping("/{id}")
    fun order(@PathVariable id: Long): OrderDTO {
        val order = service.getById(id) ?: throw NotFoundException("Orden no encontrada.")
        return OrderDTO.toDTO(order)
    }
}