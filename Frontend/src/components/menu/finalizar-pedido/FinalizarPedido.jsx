import usePedido from '../../../context/PedidoContext';
import ClientDetailsFinalizar from './client-details-finalizar/ClientDetailsFinalizar';
import { useLocation, useNavigate } from 'react-router-dom';
import './FinalizarPedido.css'
import { useState } from 'react';

const FinalizarPedido = () => {
  const { state } = useLocation();
  const restaurant = state?.restaurant;
  const { pedido } = usePedido();
  const navigate = useNavigate();
  const [propina, setPropina] = useState(0);

  const handlePagoRealizado = () => {
    navigate('/');
    window.location.reload();
  }

  const handlePropinaChange = (event) => {
    const newPropina = parseFloat(event.target.value);
    setPropina(isNaN(newPropina) ? 0 : newPropina);
  };

  
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
                  <ClientDetailsFinalizar comensales={ pedido.soloPersonas } propina={ propina }/>
                  <section class="tip-section">
                    <label for="tipAmount">Propina a dejar:</label>
                    <input
                      type="text"
                      id="tipAmount"
                      value={propina}
                      onChange={handlePropinaChange}
                    />
                  </section>
                  <h1>Total del Pedido: <span>{totalSpent() + propina }</span></h1>
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