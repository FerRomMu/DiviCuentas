import React, { useEffect, useState } from 'react';
import './Home.css';
import useService from '../services/useService';
import DisplayRestaurant from './display-restaurant/DisplayRestaurant';

const Home = () => {

    const { getRestaurants } = useService();
    const [restaurants, setRestaurants] = useState([]);

    useEffect(() => {
        getRestaurants()
            .then((listRest) => {
                setRestaurants(listRest);
            })

    }, [])

    return(
        <main className='container-boxes-restaurants'>
          <header>
            <h1 className='title'>Restaurants</h1>
          </header>
          <section className='restaurants'>
            {restaurants?.map((restaurant, i) => <DisplayRestaurant restaurant={restaurant} id={i}/>)}
          </section>
        </main>
    )

}

export default Home;