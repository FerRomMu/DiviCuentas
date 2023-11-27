import './DisplayRestaurant.css'

const DisplayRestaurant = ({ restaurant, id, callback }) => {

  return(
    <div key={id} className='display-restaurant bg'>
        <aside className='display-restaurant-image-grid'>
            <img className='display-restaurant-img' src={restaurant.image} alt={ restaurant.name }/>
        </aside>
        <header className='display-restaurant-title-grid'>
          <div className='display-restaurant-title'>{restaurant.name}</div>
          <p className='display-restaurant-subtitle'>{restaurant.type}</p>
        </header>
        <section className='display-restaurant-info-grid'>
          <div className='display-restaurant-location'>
            <img className='display-restaurant-img-location' src='https://icones.pro/wp-content/uploads/2021/02/icone-de-broche-de-localisation-noire.png' alt=""></img>
            <p>{restaurant.direction}</p>
          </div>
        </section>
        <button className='display-restaurant-button-grid' type='submit' onClick={() => callback(restaurant)}>Ver menú</button>
    </div>
  )
}

export default DisplayRestaurant;