import React, { useEffect, useState } from 'react';
import useService from '../services/useService';
import './Menu.css';

const Menu = () => {

    const { product} = useService();
    const [prod, setProd] = useState(null)
    const [products, setProducts] = useState([])

    useEffect(() => {
        product(1)
            .then((prod) => setProd(prod))
    }, [])

    console.log(prod)

    return (
        <div className='container'>
            <h1 className='title'>Menú</h1>
            <div className='products'>
                <div className='box-product'>
                    <h1 className='product-name'>{prod ? prod.name : ''}</h1>
                    <img className='product-img' src={prod ? prod.image : ''} alt=''></img>
                    <p>{prod ? prod.description : ''}</p>
                    <p className='price'>${prod ? prod.price : ''}</p>
                </div>
            </div>
        </div>
    )

}

export default Menu;