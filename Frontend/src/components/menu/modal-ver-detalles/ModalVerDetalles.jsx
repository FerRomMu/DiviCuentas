import usePedido from '../../../context/PedidoContext'
import ClientDetails from './clientDetails/ClientDetails'
import './ModalVerDetalles.css'

const ModalVerDetalles = ({close, restaurant}) => {

    const { pedido } = usePedido()

    const totalSpent = () => {
        let total = 0;
        for (const [name, productsMap] of pedido.personas.entries()) {
            for (const [product, cantidad] of productsMap.entries()) {
                total += cantidad * product.price;
            }
        }
        return total;
    }

    const totalPorPersona = () => {
        const totales = {};
        for (const [name, productsMap] of pedido.personas.entries()) {
            let totalPersona = 0;
            for (const [product, cantidad] of productsMap.entries()) {
                totalPersona += cantidad * product.price;
            }
            totales[name] = totalPersona;
        }
        return totales;
    }

    return (
        <div className='modal-background'>
            <section className='ver-detalles bg border container-volver-btn'>
                <header>
                    <h1>{restaurant.name}</h1>
                </header>
                <main>
                    <h2>Comensales</h2>
                    <ClientDetails pedido={pedido} />
                    <h1>Total del Pedido: <span>{totalSpent()}</span></h1>
                    {Object.keys(totalPorPersona).map((name, index) => (
                        <p key={index}>{name}: {totalPorPersona[name]}</p>
                    ))}
                </main>
                <button className='volver-btn' onClick={close}>Volver al pedido</button>
            </section>
        </div>
    )

}

export default ModalVerDetalles;