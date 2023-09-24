import React, { useEffect, useState } from 'react';
import { Routes, Route, Navigate } from "react-router-dom";
import './Home.css';

const Home = () => {

    const restaurants = [
        {
          id: 1,
          name: "BurguerBeer",
          direction: "Av.Sarmiento 5130",
          tipo_cocina: "Hamburguesas/Comida rápida",
          image: "https://puntoapunto.com.ar/wp-content/uploads/2022/05/Burgerbeer.jpg"
        },
        {
          id: 2,
          name: "Güerrín",
          direction: "Av.Corrientes 1368",
          tipo_cocina: "Pizzas",
          image: "https://tn.com.ar/resizer/T1HDFanvJCkAI6VkJ6iDZIqzgNQ=/1440x0/smart/filters:format(webp)/cloudfront-us-east-1.images.arcpublishing.com/artear/6BWOOZVNOVBPREIFB5WDT6E7CA.jpg"
        },
        {
          id: 3,
          name: "Dolce Vita Gelato",
          direction: "Av.San Martín 2090",
          tipo_cocina: "Helado/Postres",
          image: "https://media-cdn.tripadvisor.com/media/photo-s/1b/ce/ad/d3/la-location.jpg"
        }
      ];

    return(
        <section className='container-boxes-restaurants'>
            <h1 className='title'>Restaurants</h1>

            <div className='restaurants'>
                {restaurants.map((restaurant, i) => {
                    return (
                        <div key={restaurant.i} className='restaurant'>
                            <aside className='image-grid'>
                                <img src={restaurant.image} alt=''/>
                            </aside>
                            <header className='title-grid'>
                              <h2 className='title'>{restaurant.name}</h2>
                              <p className='subtitle'>{restaurant.tipo_cocina}</p>
                            </header>
                            <section className='info-grid'>
                              <div>
                                <img className='img-location' src='https://icon-library.com/images/new-location-icon/new-location-icon-4.jpg' alt=""></img>
                                <p>{restaurant.direction}</p>
                              </div>
                            </section>
                            <button className='button-grid'>Ver menú</button>
                        </div>
                    )
                })}
            </div>
        </section>
    )

}

export default Home;