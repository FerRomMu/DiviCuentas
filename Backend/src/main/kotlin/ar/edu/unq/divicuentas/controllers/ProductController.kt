package ar.edu.unq.divicuentas.controllers

import ar.edu.unq.divicuentas.mapping.dto.ProductDTO
import ar.edu.unq.divicuentas.service.IProductService
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@ServiceREST
@RequestMapping("/product")
class ProductController {

    @Autowired lateinit var service: IProductService

    @PostMapping
    fun createProduct(@RequestBody productDTO: ProductDTO) : ProductDTO{
        return ProductDTO.toDTO(service.create(productDTO.toModel()))
    }

    @GetMapping("/{id}")
    fun product(@PathVariable id: Long): ProductDTO{
        return try {
            ProductDTO.toDTO(service.getById(id)!!)
        } catch (e: NullPointerException) {
            throw NotFoundException("Producto con id $id no encontrado.")
        }
    }
}