import ClientDetails from './clientDetails/ClientDetails'
import './ModalVerDetalles.css'

const ModalVerDetalles = ({order, restaurant}) => {

    const totalSpent = () => {
        return order.products.reduce((total, product) => total + product.amount * product.price, 0)
    }

    return (
        <div className='modal-background'>
        <section className='ver-detalles bg container-volver-btn'>
            <header>
                <h1>{restaurant.name}</h1>
            </header>
            <main>
                <h2>Comensales</h2>
                <ClientDetails name={order.owner} products={order.products}/>
                <h1>Total: <span>{ totalSpent() }</span></h1>
            </main>
            <button className='volver-btn'>Volver al pedido</button>
        </section>
        </div>
    )

}

export default ModalVerDetalles;