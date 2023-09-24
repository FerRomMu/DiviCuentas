package ar.edu.unq.divicuentas.controllers

import ar.edu.unq.divicuentas.mapping.dto.ProductDTO
import ar.edu.unq.divicuentas.service.IProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@ServiceREST
@RequestMapping("/product")
class ProductController {

    @Autowired lateinit var service: IProductService

    @PostMapping
    fun createProduct(@RequestBody productDTO: ProductDTO) : ProductDTO{
        return ProductDTO.fromDTO(service.create(productDTO.toModel()))
    }
}