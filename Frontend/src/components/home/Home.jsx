import useRestaurantsData from '../../hooks/useRestaurantsData';
import DisplayRestaurant from './display-restaurant/DisplayRestaurant';
import { useNavigate } from "react-router-dom";

const Home = () => {

    const restaurants = useRestaurantsData();
    
    const navigate = useNavigate()
    const navigateMenu = (restaurant) => {
      navigate('/menu',  { state: { restaurant } }); 
    }

    return(
      <main>
        <header>
          <h1>Restaurants</h1>
        </header>
        <section className='flex'>
          {restaurants?.map((restaurant, i) => <DisplayRestaurant key={i} restaurant={restaurant} id={i} callback={navigateMenu}/>)}
        </section>
      </main>
    )

}

export default Home;