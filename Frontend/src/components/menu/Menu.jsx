import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import ModalCrearPedido from './modal-crear-pedido/ModalCrearPedido';
import useProductsData from '../../hooks/useProductsData';
import ProductsRow from './products-row/productsRow';
import ModalVerDetalles from './modal-ver-detalles/ModalVerDetalles';
import usePedido from '../../context/PedidoContext';

const Menu = () => {

    const { state } = useLocation();
    const { restaurant } = state;

    const navigate = useNavigate();

    const products = useProductsData(restaurant?.menu);
    
    const [openModal, setOpenModal] = useState(false);
    const [openDetails, setOpenDetails] = useState(false);
    const [isOrder, setOrder] = useState(false);
    const { pedido } = usePedido();

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
    const cerrarModal = () => {
      setOpenModal(false)
    }

    const abrirDetalles = () => {
      setOpenDetails(true);
    }
    const cerrarDetalles = () => {
      setOpenDetails(false)
    }


    return (
        <main>
          <header>
            <h1 className='container-volver-btn'>Menú</h1>
            <button className='volver-btn' onClick={() => backToHome()}> Volver </button>
            {isOrder && (
              <h2>Está pidiendo: {pedido.owner} </h2>
            )}
          </header>
          <section className='flex'>
            {
              productsInRows(products).map((productsTrio, i) => {
                return <ProductsRow products={productsTrio} isOrder={isOrder}/>
              })
            }
          </section>
          <footer className='flex'>
            {isOrder && ( 
              <>
                <button onClick={abrirDetalles}>Ver pedido</button>
                {openDetails && (
                  <ModalVerDetalles close={ cerrarDetalles } restaurant={restaurant}/>
                )}
              </>
            )}
            <>
              <button onClick={abrirModal}>
                {isOrder ? "AgregarPersona" : "Crear Pedido" }
              </button>
              {openModal && (
                <ModalCrearPedido close={ cerrarDetalles } setOrder={ setOrder } setOpenModal= { setOpenModal } />
              )}
            </>
          </footer>
        </main>
    )

}

export default Menu;