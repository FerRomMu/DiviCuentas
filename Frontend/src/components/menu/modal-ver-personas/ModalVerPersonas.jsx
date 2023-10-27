import usePedido from '../../../context/PedidoContext'
import React, { useState } from 'react';
import './ModalVerPersonas.css';
import CheckboxListPersonas from './checkbox-list-personas/CheckboxListPersonas';

const ModalVerPersonas = ({close, restaurant}) => {

    const { pedido, cambiarQuienesPiden } = usePedido()
    const [pidiendo] = useState(new Map())
    const personas = pedido.soloPersonas;
    
    const markComensal = (index, state) => {
      pidiendo.set(index, state)
    }

    const cambiarPedidores = () => {
      const nuevosPedidores = Array.from(pidiendo.entries())
        .filter(([, valor]) => valor)
        .sort((a, b) => a[0] - b[0])
        .map(([i,_]) => personas[i])
      cambiarQuienesPiden(nuevosPedidores)
    }

    return (
        <div className='modal-background'>
            <section className='mvp-personas bg border container-volver-btn'>
                <header>
                    <h1>{restaurant.name}</h1>
                </header>
                <main>
                    <h2>Comensales</h2>
                    <div className='mvp-ul'>
                        {personas.map((nombre, index) => (
                          <CheckboxListPersonas owner={ pedido.owner } name={nombre} onMark={markComensal} index={index}/>
                        ))}
                    </div>
                </main>
                <button className='volver-btn' onClick={close}>Volver al pedido</button>
                <h2>Esta pidiendo: { pedido.owner } </h2>
                <footer>
                  <button onClick={cambiarPedidores}>Cambiar quien pide</button>
                </footer>
            </section>
        </div>
    )

}

export default ModalVerPersonas;