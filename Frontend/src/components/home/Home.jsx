import { Box } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { Routes, Route, Navigate } from "react-router-dom";
import './Home.css';
import useService from '../services/useService';

const Home = () => {

    const { getRestaurants , product, restaurant} = useService();
    const [restaurants, setRestaurants] = useState([])

    useEffect(() => {
        getRestaurants()
            .then((listRest) => {
                setRestaurants(listRest);
            })

        product(1)
        restaurant(1)

    }, [])

    console.log(restaurants)
    // const restaurants = [
        // {
        //   id: 1,
        //   name: "BurguerBeer",
        //   direction: "Av.Sarmiento 5130",
        //   tipo_cocina: "Hamburguesas/Comida rápida",
        //   image: "https://puntoapunto.com.ar/wp-content/uploads/2022/05/Burgerbeer.jpg"
        // },
    //     {
    //       id: 2,
    //       name: "Güerrín",
    //       direction: "Av.Corrientes 1368",
    //       tipo_cocina: "Pizzas",
    //       image: "https://tn.com.ar/resizer/T1HDFanvJCkAI6VkJ6iDZIqzgNQ=/1440x0/smart/filters:format(webp)/cloudfront-us-east-1.images.arcpublishing.com/artear/6BWOOZVNOVBPREIFB5WDT6E7CA.jpg"
    //     },
    //     {
    //       id: 3,
    //       name: "Dolce Vita Gelato",
    //       direction: "Av.San Martín 2090",
    //       tipo_cocina: "Helado/Postres",
    //       image: "https://media-cdn.tripadvisor.com/media/photo-s/1b/ce/ad/d3/la-location.jpg"
    //     }
    //   ];

    return(
        <div className='container-boxes-restaurants'>
            <h1 className='title'>Restaurants</h1>

            {/* <div className='restaurants'>
                {restaurants.map((restaurant) => {
                    return (
                        <Box key={restaurant.id} className='restaurant'>
                            <div>
                                <img className='img-restaurant' src={restaurant.image} alt=''/>
                            </div>
                            <div>
                                <h2>{restaurant.name}</h2>
                                <p>{restaurant.direction}</p>
                                <p>{restaurant.tipo_cocina}</p>
                            </div>
                        </Box>
                    )
                })}
            </div> */}
        </div>
    )

}

export default Home;