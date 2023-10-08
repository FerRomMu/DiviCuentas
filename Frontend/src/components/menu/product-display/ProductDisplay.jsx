import React, { useState } from 'react';
import usePedido from '../../../context/PedidoContext';

const ProductDisplay = ({prod ,isOrder}) => {

    const [cantidadSumada, setCantidadSumada] = useState(0);
    const { pedido, agregarProducto, quitarProducto, confirmarPedido } = usePedido();

    const agregarPedido = (product) => {
        setCantidadSumada(cantidadSumada + 1);
        agregarProducto(product);
      };
    
    const quitarPedido = (id) => {
        if (cantidadSumada > 0) {
                  setCantidadSumada(cantidadSumada - 1);
        }
        quitarProducto(id);
        console.log(pedido);
    };

    return (
        <div className='product'>
            <h1 className='grid-title'>{prod ? prod.name : ''}</h1>
            <div className='grid-img'>
              <img className='img' src={prod ? prod.image : ''} alt=''></img>
            </div>
            <div className={isOrder ? 'box-crear-pedido' : 'hidden'}>
              <button className='btn' type='submit' onClick={() => agregarPedido(prod)}> <span>+</span></button>
              <p className='cantidad'>{cantidadSumada}</p>
              <button className='btn' type='submit' onClick={() => quitarPedido(prod.id)}> <span>-</span></button>
            </div>
            <div className='grid-info'>
              <p className='description'>{prod ? prod.description : ''}</p>
              <p>${prod ? prod.price : ''}</p>
            </div>
        </div>
    )


}

export default ProductDisplay;