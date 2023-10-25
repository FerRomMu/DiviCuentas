import usePedido from '../../../context/PedidoContext'
import './ModalVerPersonas.css';

const ModalVerPersonas = ({close, restaurant}) => {

    const { pedido } = usePedido()
    const personas = Array.from(pedido.personas.entries());

    return (
        <div className='modal-background'>
            <section className='mvp-personas bg border container-volver-btn'>
                <header>
                    <h1>{restaurant.name}</h1>
                </header>
                <main>
                    <h2>Comensales</h2>
                        <ul className='mvp-ul'>
                            <p>
                                {personas.map(([nombre, datosPersona]) => (
                                    <li key={nombre}>{nombre}</li>
                                ))}
                            </p>
                        </ul>
                </main>
                <button className='volver-btn' onClick={close}>Volver al pedido</button>
            </section>
        </div>
    )

}

export default ModalVerPersonas;