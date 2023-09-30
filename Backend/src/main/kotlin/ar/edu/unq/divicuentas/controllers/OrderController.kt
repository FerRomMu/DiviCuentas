package ar.edu.unq.divicuentas.controllers

import ar.edu.unq.divicuentas.mapping.dto.OrderDTO
import ar.edu.unq.divicuentas.service.IOrderService
import org.springframework.beans.factory.annotation.Autowired
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
}