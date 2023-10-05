import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import './Home.css';
import useService from '../services/useService';
import DisplayRestaurant from './display-restaurant/DisplayRestaurant';

const Home = () => {

    const { getRestaurants } = useService();
    const [restaurants, setRestaurants] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getRestaurants()
            .then((listRest) => {
                setRestaurants(listRest);
            })

    }, [])

    const navigateMenu = (restaurant) => {
        navigate('/menu',  { state: { restaurant } }); 
    }

    return(
        <section className='container-boxes-restaurants'>
            <h1 className='title'>Restaurants</h1>

            <div className='restaurants'>
                {restaurants?.map((restaurant, i) => <DisplayRestaurant restaurant={restaurant} id={i}/>)}
            </div>
        </section>
    )

}

export default Home;