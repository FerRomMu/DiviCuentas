import './DisplayRestaurant.css'

const DisplayRestaurant = ({ restaurant, id, callback }) => {

  return(
    <div key={id} className='display-restaurant bg'>
        <aside className='display-restaurant-image-grid'>
            <img className='display-restaurant-img' src={restaurant.image} alt={ restaurant.name }/>
        </aside>
        <header className='display-restaurant-title-grid'>
          <h2 className='display-restaurant-title'>{restaurant.name}</h2>
          <p className='display-restaurant-subtitle'>{restaurant.type}</p>
        </header>
        <section className='display-restaurant-info-grid'>
          <div className='display-restaurant-location'>
            <img className='display-restaurant-img-location' src='https://icon-library.com/images/new-location-icon/new-location-icon-4.jpg' alt=""></img>
            <p>{restaurant.direction}</p>
          </div>
        </section>
        <button className='display-restaurant-button-grid' type='submit' onClick={() => callback(restaurant)}>Ver menú</button>
    </div>
  )
}

export default DisplayRestaurant;