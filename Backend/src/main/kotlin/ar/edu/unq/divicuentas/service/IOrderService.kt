package ar.edu.unq.divicuentas.service

import ar.edu.unq.divicuentas.modelo.Order

interface IOrderService {
    fun create(order : Order) : Order
    fun getById(orderId: Long): Order?
    fun update(order: Order): Order
    fun getByOwner(owner: String): Order?
}