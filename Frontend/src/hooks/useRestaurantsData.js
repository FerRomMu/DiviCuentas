import { useEffect, useState } from 'react';
import { fetchRestaurantsData } from '../service/apiService';

const useRestaurantsData = () => {
  const [restaurantsData, setRestaurantData] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await fetchRestaurantsData();
        setRestaurantData(data);
      } catch (error) {
        console.error('Error fetching restaurants data', error);
      }
    };

    fetchData();
  }, []);

  return restaurantsData;
};

export default useRestaurantsData;
