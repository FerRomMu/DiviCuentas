import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import ModalCrearPedido from './modal-crear-pedido/ModalCrearPedido';
import useProductsData from '../../hooks/useProductsData';
import ProductsRow from './products-row/productsRow';
import ModalVerDetalles from './modal-ver-detalles/ModalVerDetalles';
import usePedido from '../../context/PedidoContext';
import ModalVerPersonas from './modal-ver-personas/ModalVerPersonas';
import ModalCancelarPedido from './modal-cancelar-pedido/ModalCancelarPedido';
import './Menu.css'
import ModalFinalizarPedido from './modal-finalizar-pedido/ModalFinalizarPedido';

const Menu = () => {

    const { state } = useLocation();
    const { restaurant } = state;

    const navigate = useNavigate();

    const products = useProductsData(restaurant?.menu);
    
    const [openModal, setOpenModal] = useState(false);
    const [openDetails, setOpenDetails] = useState(false);
    const [openDetailsPersonas, setOpenDetailsPersonas] = useState(false);
    const [openCloseOrder, setCloseOrder] = useState(false);
    const [openFinalizarOrden, setFinalizarOrden] = useState(false);
    const [isOrder, setOrder] = useState(false);
    const { pedido } = usePedido();
    const [verComensales, setVerComensales] = useState(false);

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

    const abrirDetallesPersonas = () =>{
      setOpenDetailsPersonas(true)
    }

    const cerrarDetallesPersonas = () =>{
      setOpenDetailsPersonas(false)
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

    const abrirCancelarPedido = () => {
      setCloseOrder(true)
    }

    const cerrarCancelarPedido = () => {
      setCloseOrder(false)
    }

    const abrirModalFinalizarPedido = () => {
      setFinalizarOrden(true)
    }

    const cerrarFinalizarPedido = () => {
      setFinalizarOrden(false)
    }

    return (
        <main>
          <header>
            <h1 className='container-volver-btn'>Menú</h1>
            <button className='volver-btn' onClick={() => isOrder? abrirCancelarPedido() : backToHome()}> 
              {isOrder? "Cancelar pedido" : "Volver"} 
            </button>
            {isOrder && (
              <button className='finalizar-btn' onClick={() => abrirModalFinalizarPedido()}> 
                Finalizar Pedido
              </button>
            )}
            {isOrder && (
              <h2>Está pidiendo: {pedido.owner} </h2>
            )}
          </header>
          <div>
            {openCloseOrder && (
              <ModalCancelarPedido close= {cerrarCancelarPedido} setOrder= {setOrder}/>
            )}
          </div>
          <div>
            {openFinalizarOrden && (
              <ModalFinalizarPedido close= {cerrarFinalizarPedido} setOrder= {setOrder} restaurant={restaurant}/>
            )}
          </div>
          <section className='flex'>
            {
              productsInRows(products).map((productsTrio, i) => {
                return <ProductsRow products={productsTrio} isOrder={isOrder}/>
              })
            }
          </section>
          <footer className='flex-h menu-footer'>
            <div className='flex-h menu-div-btns'>
                {isOrder && ( 
                  <>
                    <button onClick={abrirDetalles}>Ver pedido</button>
                    {openDetails && (
                      <ModalVerDetalles close={ cerrarDetalles } restaurant={restaurant}/>
                    )}
                  </>
                )}

                {isOrder && ( 
                  <>
                    <button onClick={abrirDetallesPersonas}>Ver comensales</button>
                        {openDetailsPersonas && (
                            <ModalVerPersonas close={ cerrarDetallesPersonas } restaurant={restaurant}/>
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
            </div>
          </footer>
        </main>
    )

}

export default Menu;