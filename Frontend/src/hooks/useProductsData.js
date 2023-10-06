import { useEffect, useState } from 'react';
import { fetchProductsData } from '../service/apiService';

const useProductsData = (ids) => {
  const [productsData, setproductsData] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await fetchProductsData(ids);
        setproductsData(data);
      } catch (error) {
        console.error('Error fetching products data', error);
      }
    };

    fetchData();
  }, []);

  return productsData;
};

export default useProductsData;
