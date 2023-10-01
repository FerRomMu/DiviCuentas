package ar.edu.unq.divicuentas.service

import ar.edu.unq.divicuentas.modelo.Order

interface IOrderService {
    fun create(order : Order) : Order
}