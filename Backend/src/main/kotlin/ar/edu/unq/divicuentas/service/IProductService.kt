package ar.edu.unq.divicuentas.service

import ar.edu.unq.divicuentas.modelo.Product

interface IProductService {
    fun create(product: Product): Product
    fun update(product: Product): Product
    fun deleteById(productId: Long)
    fun getById(productId: Long): Product?
}