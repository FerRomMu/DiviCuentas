package ar.edu.unq.divicuentas.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@ServiceREST
@RequestMapping("/prueba")
class Prueba2RestController {

    @GetMapping
    fun holaMundo() : String{
        return "hola mundo"
    }

}