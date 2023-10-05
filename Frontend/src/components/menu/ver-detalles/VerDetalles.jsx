import ClientDetails from './clientDetails/ClientDetails'
const VerDetalles = ({order, restaurant}) => {

    const totalSpent = () => {
        console.log(order)
        return order.products.reduce((total, product) => total + product.amount * product.price, 0)
    }

    return (
        <section className='ver-detalles'>
            <header>
                <h1>{restaurant.name}</h1>
            </header>
            <main>
                <h2>Comensales</h2>
                <ClientDetails name={order.owner} products={order.products}/>
                <h1>Total: <span>{ totalSpent() }</span></h1>
            </main>
            <footer>
                <button>Volver al pedido</button>
            </footer>
        </section>
    )

}

export default VerDetalles;