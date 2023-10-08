import React, { useState } from 'react';

const ProductDisplay = ({prod ,isOrder}) => {

    const [cantidadSumada, setCantidadSumada] = useState(0);

    const agregarPedido = () => {
        setCantidadSumada(cantidadSumada + 1);
      };
    
    const quitarPedido = () => {
        if (cantidadSumada > 0) {
                  setCantidadSumada(cantidadSumada - 1);
        }
    };

    return (
        <div className='product bg'>
            <h1 className='grid-title'>{prod ? prod.name : ''}</h1>
            <div className='grid-img'>
              <img className='img' src={prod ? prod.image : ''} alt=''></img>
            </div>
            <div className={isOrder ? 'box-crear-pedido' : 'hidden'}>
              <button className='btn' type='submit' onClick={agregarPedido}> <span>+</span></button>
              <p className='cantidad'>{cantidadSumada}</p>
              <button className='btn' type='submit' onClick={quitarPedido}> <span>-</span></button>
            </div>
            <div className='grid-info'>
              <p className='description'>{prod ? prod.description : ''}</p>
              <p>${prod ? prod.price : ''}</p>
            </div>
        </div>
    )


}

export default ProductDisplay;