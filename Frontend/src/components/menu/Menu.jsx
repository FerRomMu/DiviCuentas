import React, { useEffect, useState } from 'react';
import useService from '../../service/useService';
import './Menu.css';
import { useLocation, useNavigate } from 'react-router-dom';
import ModalCrearPedido from './modal-crear-pedido/ModalCrearPedido';
import ProductDisplay from './product-display/ProductDisplay';

const Menu = () => {

    const { product } = useService();
    const { state } = useLocation();
    const { restaurant } = state;
    const [products, setProducts] = useState([])
    const navigate = useNavigate();
    const [openModal, setOpenModal] = useState(false);
    const [isOrder, setOrder] = useState(false);
    const [name, setName] = useState("");
    
   
    useEffect(() => {
        const fetchProducts = async () => {
          if (restaurant?.menu) {
            const productPromises = restaurant.menu.map(async (idP) => {
              return await product(idP);
            });
      
            const productsToSet = await Promise.all(productPromises);
      
            setProducts(productsToSet);
          }
        };
      
        fetchProducts();
      }, [restaurant]);


    const productsInRows = (products) => {
      const productsInRows = []
      for (let i = 0; i < products.length; i += 3) {
        productsInRows.push(products.slice(i,i+3))
      }
      return productsInRows
    }

    const backToHome = () => {
        navigate('/home');
    }

    const abrirModal = () => {
      setOpenModal(true);
    }
 
    const list = productsInRows(products)

    const productsRow = (products) => {
      return (
        <>
          <button className='volver-btn' onClick={() => backToHome()}>{" Volver"}</button>
          { products.map((prod, i) =>
              <ProductDisplay prod={prod} isOrder={isOrder}></ProductDisplay>
          )}
        </>
      )
      
    }

    return (
        <main className='container'>
            <h1 className='title_menu'>Menú</h1>
            {isOrder && (
              <h2>Está pidiendo: {name} </h2>
            )}
            <div>
            {
              list.map((productsTrio, i) => {
                return <div className='products'>{
                  productsRow(productsTrio)
                }</div>
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