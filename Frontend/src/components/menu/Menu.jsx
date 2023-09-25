import React, { useEffect, useState } from 'react';
import useService from '../services/useService';
import './Menu.css';

const Menu = () => {

    /*const { product} = useService();
    const [prod, setProd] = useState(null)
    const [products, setProducts] = useState([])*/

    /*useEffect(() => {
        product(1)
            .then((prod) => setProd(prod))
    }, [])*/

    const products = [
      {
          "name":"Stacker Triple",
          "image":"https://s3-eu-central-1.amazonaws.com/www.burgerking.com.ar.v2/wp-media-folder-burger-king-argentina//home/ubuntu/preview/menu-app/frontend/apps/marketing-website-wordpress-app/web/app/uploads/sites/5/Stacker-Triple.png",
          "description":"3 carnes a la parrilla, salsa stacker, pan, queso cheddar, panceta.",
          "price":4000.0
      },
      {
          "name":"Stacker Doble",
          "image":"https://s3-eu-central-1.amazonaws.com/www.burgerking.com.ar.v2/wp-media-folder-burger-king-argentina//home/ubuntu/preview/menu-app/frontend/apps/marketing-website-wordpress-app/web/app/uploads/sites/5/Stacker-Doble.png",
          "description":"2 carnes a la parri, salsa stacker, pan, queso cheddar, panceta.",
          "price":3000.0
      },
      {
        "name":"Pizza Muzza",
        "image":"https://s3.amazonaws.com/arc-wordpress-client-uploads/infobae-wp/wp-content/uploads/2019/06/06170448/GUERRIN-MUZARELLA.jpg",
        "description":"Pizza de Muzzarella",
        "price":5000.0
      },
      {
        "name":"Helado de Frutilla",
        "image":"https://media-cdn.tripadvisor.com/media/photo-s/23/de/56/92/refreshing-red-fruit.jpg",
        "description":"Helado con sabor a Frutilla",
        "price":2000.0
      },
      {
          "name":"Stacker eeeele",
          "image":"https://s3-eu-central-1.amazonaws.com/www.burgerking.com.ar.v2/wp-media-folder-burger-king-argentina//home/ubuntu/preview/menu-app/frontend/apps/marketing-website-wordpress-app/web/app/uploads/sites/5/Stacker-Triple.png",
          "description":"3 carnes a la parrilla, salsa stacker, pan, queso cheddar, panceta.",
          "price":4000.0
      },
      {
          "name":"Stacker Dobrerele",
          "image":"https://s3-eu-central-1.amazonaws.com/www.burgerking.com.ar.v2/wp-media-folder-burger-king-argentina//home/ubuntu/preview/menu-app/frontend/apps/marketing-website-wordpress-app/web/app/uploads/sites/5/Stacker-Doble.png",
          "description":"2 carnes a la parri, salsa stacker, pan, queso cheddar, panceta.",
          "price":3000.0
      },
      {
        "name":"Pizza Muzzasasa",
        "image":"https://s3.amazonaws.com/arc-wordpress-client-uploads/infobae-wp/wp-content/uploads/2019/06/06170448/GUERRIN-MUZARELLA.jpg",
        "description":"Pizza de Muzzarella",
        "price":5000.0
      },
      {
        "name":"Helado de Frutidadaalla",
        "image":"https://media-cdn.tripadvisor.com/media/photo-s/23/de/56/92/refreshing-red-fruit.jpg",
        "description":"Helado con sabor a Frutilla",
        "price":2000.0
      }
    ]


    const productsInRows = (products) => {
      const productsInRows = []
      for (let i = 0; i < products.length; i += 3) {
        productsInRows.push(products.slice(i,i+3))
      }
      return productsInRows
    }

    const list = productsInRows(products)

    console.log(list)
    const productsRow = (products) => {
      return (
        <>
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
        ) }
        </>
      )
      
    }

    return (
        <div className='container'>
            <h1 className='title'>Menú</h1>
            <div>
            {
              list.map((productsTrio, i) => {
                return <div className='products'>{
                  productsRow(productsTrio)
                }</div>
              })
            }</div>
        </div>
    )

}

export default Menu;