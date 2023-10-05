import useRestaurantsData from '../../hooks/useRestaurantsData';
import './Home.css';
import DisplayRestaurant from './display-restaurant/DisplayRestaurant';

const Home = () => {

    const restaurants = useRestaurantsData();

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