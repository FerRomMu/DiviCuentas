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

    return (
        <div className='modal-background'>
            <section className='ver-detalles bg border container-volver-btn'>
                <header>
                    <h1>{restaurant.name}</h1>
                </header>
                <main>
                    <h2>Comensales</h2>
                    <ClientDetailsFinalizar comensales={ pedido.soloPersonas }/>
                    <h1 style={{fontSize: '30px'}}>Total del Pedido: <span>{totalSpent()}</span></h1>
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