import usePedido from '../../../context/PedidoContext';
import ClientDetailsFinalizar from './client-details-finalizar/ClientDetailsFinalizar';
import { useLocation, useNavigate } from 'react-router-dom';
import './FinalizarPedido.css'

const FinalizarPedido = () => {
  const { state } = useLocation();
  const restaurant = state?.restaurant;
  const { pedido } = usePedido();
  const navigate = useNavigate();

    const handlePagoRealizado = () => {
      navigate('/');
      window.location.reload();
   }

    const totalSpent = () => {
        let total = 0;
        for (const [, productsMap] of pedido.personas.entries()) {
            for (const [product, cantidad] of productsMap.entries()) {
                total += cantidad * product.price;
            }
        }
        return total;
    }

    /*const totalPorPersona = () => {
        const totales = {};
        for (const [name, productsMap] of pedido.personas.entries()) {
            let totalPersona = 0;
            for (const [product, cantidad] of productsMap.entries()) {
                totalPersona += cantidad * product.price;
            }
            totales[name] = totalPersona;
        }
        return totales;
    }*/

    return (
        <div className='modal-background'>
            <section className='ver-detalles bg border container-volver-btn'>
                <header>
                    <h1>{restaurant.name}</h1>
                </header>
                <main>
                    <h2>Comensales</h2>
                    <ClientDetailsFinalizar/>
                    <h1>Total del Pedido: <span>{totalSpent()}</span></h1>
                </main>
                <div className='fp-buttons'>
                  <button onClick={() => handlePagoRealizado()}>
                    Pago Realizado
                  </button>
                </div>
            </section>
        </div>
    )

}

export default FinalizarPedido;