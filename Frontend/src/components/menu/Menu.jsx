import React, { useState } from 'react';
import './Menu.css';
import { useLocation, useNavigate } from 'react-router-dom';
import ModalCrearPedido from './modal-crear-pedido/ModalCrearPedido';
import useProductsData from '../../hooks/useProductsData';
import ProductsRow from './products-row/productsRow';

const Menu = () => {

    const { state } = useLocation();
    const { restaurant } = state;

    const navigate = useNavigate();

    const products = useProductsData(restaurant?.menu);
    
    const [openModal, setOpenModal] = useState(false);
    const [isOrder, setOrder] = useState(false);
    const [name, setName] = useState("");

    const productsInRows = (products) => {
      if(products === null){ return [] }
      const productsInRows = []
      for (let i = 0; i < products.length; i += 3) {
        productsInRows.push(products.slice(i,i+3))
      }
      return productsInRows
    }

    const backToHome = () => {
        navigate('/');
    }

    const abrirModal = () => {
      setOpenModal(true);
    }
 
    const list = productsInRows(products)



    return (
        <main className='container'>
            <h1 className='title_menu'>Menú</h1>
            <button className='volver-btn' onClick={() => backToHome()}> Volver </button>
            {isOrder && (
              <h2>Está pidiendo: {name} </h2>
            )}
            <div>
            {
              list.map((productsTrio, i) => {
                return <section className='products'>{
                  <ProductsRow products={productsTrio} isOrder={isOrder}/>
                }</section>
              })
            }</div>
            {!isOrder && (
            <div>
                <button onClick={abrirModal}>Crear Pedido</button>
                {openModal && (
                  <ModalCrearPedido setOrder={setOrder} setName={setName} ></ModalCrearPedido>
                )}
            </div>
            )}

        </main>
    )

}

export default Menu;