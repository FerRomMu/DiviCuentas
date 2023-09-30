package ar.edu.unq.divicuentas.service.impl
import ar.edu.unq.divicuentas.mapping.dto.ProductDTO
import ar.edu.unq.divicuentas.modelo.Product
import ar.edu.unq.divicuentas.modelo.Restaurant
import ar.edu.unq.divicuentas.persistencia.IProductRepository
import ar.edu.unq.divicuentas.persistencia.IRestaurantRepository
import ar.edu.unq.divicuentas.service.IDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DataService(
    @Autowired private var productRepository: IProductRepository,
    @Autowired private var restaurantRepository: IRestaurantRepository

) : IDataService {

    private var producto1 = Product(
        name = "Stacker Triple",
        image = "https://s3-eu-central-1.amazonaws.com/www.burgerking.com.ar.v2/wp-media-folder-burger-king-argentina//home/ubuntu/preview/menu-app/frontend/apps/marketing-website-wordpress-app/web/app/uploads/sites/5/Stacker-Triple.png",
        description = "3 carnes a la parrilla, salsa stacker, pan, queso cheddar, panceta.",
        price = 4000.0
    )
    private var producto2 = Product(
    name = "Stacker Doble",
    image = "https://s3-eu-central-1.amazonaws.com/www.burgerking.com.ar.v2/wp-media-folder-burger-king-argentina//home/ubuntu/preview/menu-app/frontend/apps/marketing-website-wordpress-app/web/app/uploads/sites/5/Stacker-Doble.png",
    description = "2 carnes a la parri, salsa stacker, pan, queso cheddar, panceta.",
    price = 3000.0
    )

    private var producto3 = Product(
        name = "Pizza Muzza",
        image = "https://s3.amazonaws.com/arc-wordpress-client-uploads/infobae-wp/wp-content/uploads/2019/06/06170448/GUERRIN-MUZARELLA.jpg",
        description = "Pizza de Muzzarella",
        price = 5000.0
    )

    private var producto4 = Product(
        name = "Helado de Frutilla",
        image = "https://media-cdn.tripadvisor.com/media/photo-s/23/de/56/92/refreshing-red-fruit.jpg",
        description = "Helado con sabor a Frutilla",
        price = 2000.0
    )

    private var restaurante1 = Restaurant(
        name = "BurguerBeer",
        direction = "Av.Sarmiento 5130",
        type = "Hamburguesas/Comida rápida",
        image = "https://puntoapunto.com.ar/wp-content/uploads/2022/05/Burgerbeer.jpg",
        menu = mutableListOf(producto1, producto2)
    )

    private var restaurante2 = Restaurant(
        name = "Güerrín",
        direction = "Av.Corrientes 1368",
        type="Pizzeria",
        image="https://tn.com.ar/resizer/T1HDFanvJCkAI6VkJ6iDZIqzgNQ=/1440x0/smart/filters:format(webp)/cloudfront-us-east-1.images.arcpublishing.com/artear/6BWOOZVNOVBPREIFB5WDT6E7CA.jpg",
        menu= mutableListOf(producto3)
    )
    private var restaurante3 = Restaurant(
        name="Dolce Vita Gelato",
        direction = "Av.San Martín 2090",
        type="Helado/Postres",
        image= "https://media-cdn.tripadvisor.com/media/photo-s/1b/ce/ad/d3/la-location.jpg",
        menu = mutableListOf(producto4)
    )
    override fun crearSetDeDatosIniciales() {
        val productosIniciales = listOf<Product>(producto1, producto2, producto3, producto4)

        val restaurantesIniciales = listOf<Restaurant>(restaurante1, restaurante2, restaurante3)

        productRepository.saveAll(productosIniciales)
        restaurantRepository.saveAll(restaurantesIniciales)
    }

    override fun eliminarTodo() {
        productRepository.deleteAll()
        restaurantRepository.deleteAll()
    }
}