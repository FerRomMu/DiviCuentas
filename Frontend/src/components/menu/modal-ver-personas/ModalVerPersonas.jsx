import usePedido from '../../../context/PedidoContext'
import CheckboxPedirVarios from './checkbox-pedir-varios/CheckboxPedirVarios'
import React, { useState } from 'react';
import './ModalVerPersonas.css';
import CheckboxListPersonas from './checkbox-list-personas/CheckboxListPersonas';

const ModalVerPersonas = ({close, restaurant}) => {

    const { pedido, cambiarQuienesPiden } = usePedido()
    const [pidenVarios, setVarios] = useState(false)
    const [pidiendo, setPidiendo] = useState(new Map())
    const personas = pedido.soloPersonas;
    
    const markComensal = (index, state) => {
      pidiendo.set(index, state)
    }

    const cambiarPedidores = () => {
      const nuevosPedidores = Array.from(pidiendo.entries())
        .filter(([clave, valor]) => valor)
        .sort((a, b) => a[0] - b[0])
        .map(([i,_]) => personas[i])
      console.log(nuevosPedidores)
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
                          <CheckboxListPersonas name={nombre} onMark={markComensal} index={index}/>
                        ))}
                    </div>
                </main>
                <button className='volver-btn' onClick={close}>Volver al pedido</button>
                <h2>Esta pidiendo: { pedido.owner } </h2>
                <footer>
                  <CheckboxPedirVarios pidenVarios={pidenVarios} setVarios={setVarios}/>
                  <button onClick={cambiarPedidores}>Cambiar quien pide</button>
                </footer>
            </section>
        </div>
    )

}

export default ModalVerPersonas;