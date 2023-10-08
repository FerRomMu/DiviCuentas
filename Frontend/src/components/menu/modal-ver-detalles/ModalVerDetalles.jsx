import usePedido from '../../../context/PedidoContext'
import ClientDetails from './clientDetails/ClientDetails'
import './ModalVerDetalles.css'

const ModalVerDetalles = ({close, restaurant}) => {

    const { pedido } = usePedido()

    const totalSpent = () => {
        return pedido.products.reduce((total, product) => total + product.amount * product.price, 0)
    }

    return (
        <div className='modal-background'>
        <section className='ver-detalles bg container-volver-btn'>
            <header>
                <h1>{restaurant.name}</h1>
            </header>
            <main>
                <h2>Comensales</h2>
                <ClientDetails name={pedido.owner} products={pedido.products}/>
                <h1>Total: <span>{ totalSpent() }</span></h1>
            </main>
            <button className='volver-btn' onClick={close}>Volver al pedido</button>
        </section>
        </div>
    )

}

export default ModalVerDetalles;