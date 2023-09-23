package ar.edu.unq.divicuentas.persistencia

import ar.edu.unq.divicuentas.modelo.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IProductRepository : JpaRepository<Product, Long>