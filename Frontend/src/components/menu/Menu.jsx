import React, { useEffect, useState } from 'react';
import useService from '../services/useService';
import './Menu.css';
import { useLocation } from 'react-router-dom';

const Menu = () => {

    const { product } = useService();
    const { state } = useLocation();
    const { restaurant } = state;
    const [products, setProducts] = useState([])
    
    restaurant?.menu.map((idP) => {
        product(idP)
            .then((prod) => {
                products.push(prod); 
                setProducts(products);
            })
    })

    console.log(products)

    return (
        <div className='container'>
            <h1 className='title'>Menú</h1>
            <div className='products'>
                {products.map((prod) => {
                    <div className='box-product'>
                        <h1 className='product-name'>{prod ? prod.name : ''}</h1>
                        <img className='product-img' src={prod ? prod.image : ''} alt=''></img>
                        <p>{prod ? prod.description : ''}</p>
                        <p className='price'>${prod ? prod.price : ''}</p>
                    </div>
                })}
            </div>
        </div>
    )

}

export default Menu;