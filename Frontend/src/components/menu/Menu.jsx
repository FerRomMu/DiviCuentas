import React, { useEffect, useState } from 'react';
import useService from '../services/useService';
import './Menu.css';
import { useLocation, useNavigate } from 'react-router-dom';
import CreateOrder from "../createOrder/CreateOrder";

const Menu = () => {

    const { product } = useService();
    const { state } = useLocation();
    const { restaurant } = state;
    const [products, setProducts] = useState([])
    const navigate = useNavigate();
    
   
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

    const navigateToCreateOrder = () => {
        navigate('/createOrder')
    }

    const list = productsInRows(products)

    const productsRow = (products) => {
      return (
        <>
        <button className='volver-btn' onClick={() => backToHome()}>{" Volver"}</button>
        { products.map((prod, i) =>
          <div className='product'>
            <h1 className='grid-title'>{prod ? prod.name : ''}</h1>
            <div className='grid-img'>
              <img className='img' src={prod ? prod.image : ''} alt=''></img>
            </div>
            <div className='grid-info'>
              <p className='description'>{prod ? prod.description : ''}</p>
              <p>${prod ? prod.price : ''}</p>
            </div>
          </div>
        )}
        </>
      )
      
    }

    return (
        <div className='container'>
            <h1 className='title_menu'>Menú</h1>
            <div>
            {
              list.map((productsTrio, i) => {
                return <div className='products'>{
                  productsRow(productsTrio)
                }</div>
              })
            }</div>
            <div>
                {/* ...contenido del menú */}
                <button onClick={navigateToCreateOrder}>Crear Pedido</button>

                {/*/!* Renderiza el componente CreateOrder y pasa la función navigateToMenu como prop *!/*/}
                {/*<CreateOrder navigateToMenu={navigateToMenu} />*/}
            </div>

        </div>
    )

}

export default Menu;