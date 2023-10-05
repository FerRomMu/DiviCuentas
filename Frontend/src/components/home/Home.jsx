import useRestaurantsData from '../../hooks/useRestaurantsData';
import './Home.css';
import DisplayRestaurant from './display-restaurant/DisplayRestaurant';
import { useNavigate } from "react-router-dom";

const Home = () => {

    const restaurants = useRestaurantsData();
    
    const navigate = useNavigate()
    const navigateMenu = (restaurant) => {
      navigate('/menu',  { state: { restaurant } }); 
    }

    return(
      <main className='container-boxes-restaurants'>
        <header>
          <h1 className='title'>Restaurants</h1>
        </header>
        <section className='restaurants'>
          {restaurants?.map((restaurant, i) => <DisplayRestaurant restaurant={restaurant} id={i} callback={navigateMenu}/>)}
        </section>
      </main>
    )

}

export default Home;