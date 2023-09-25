import React, { useEffect, useState } from 'react';
import { Routes, Route, Navigate, useNavigate , useHistory} from "react-router-dom";
import './Home.css';
import useService from '../services/useService';

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
                            <button className='button-grid' type='submit' onClick={() => navigateMenu(restaurant)}>Ver menú</button>
                        </div>
                    )
                })}
            </div>
        </section>

    )

}

export default Home;