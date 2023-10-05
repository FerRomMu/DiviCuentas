import { Navigate } from "react-router-dom";

const DisplayRestaurant = ({ restaurant, id }) => {

  const navigateMenu = (restaurant) => {
    Navigate('/menu',  { state: { restaurant } }); 
  }

  return(
    <div key={id} className='restaurant'>
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
}

export default DisplayRestaurant;