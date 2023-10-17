import React, { useState } from 'react';
import usePedido from '../../../context/PedidoContext';
import './ProductDisplay.css'

const ProductDisplay = ({prod ,isOrder}) => {

    const [cantidadSumada, setCantidadSumada] = useState(0);
    const { pedido, agregarProducto, quitarProducto, confirmarPedido } = usePedido();

    const agregarPedido = (product, name) => {
        setCantidadSumada(cantidadSumada + 1);
        agregarProducto(product, name);
      };

    const quitarPedido = (product, name) => {
        if (cantidadSumada > 0) {
            setCantidadSumada(cantidadSumada - 1);
            quitarProducto(product, name);
        }
    };

    const cantidadEnCuenta = pedido.personas.has(pedido.owner) ? pedido.personas.get(pedido.owner).get(prod) || 0 : 0;

    return (
        <div className='product-display bg'>
            <h1 className='product-display-grid-title'>{prod ? prod.name : ''}</h1>
            <div className='product-display-grid-img'>
              <img src={prod ? prod.image : ''} alt=''></img>
            </div>
            <div className={isOrder ? 'product-display-amount-container' : 'hidden'}>
              <button onClick={() => agregarPedido(prod, pedido.owner)}> <span>+</span></button>
                <p>{cantidadEnCuenta}</p>
              <button onClick={() => quitarPedido(prod, pedido.owner)}> <span>-</span></button>
            </div>
            <div className='product-display-grid-info'>
              <p className='product-display-description'>{prod ? prod.description : ''}</p>
              <p className='center'>${prod ? prod.price : ''}</p>
            </div>
        </div>
    )

}

export default ProductDisplay;