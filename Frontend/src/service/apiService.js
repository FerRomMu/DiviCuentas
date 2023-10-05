import mockApi from './apis/mockApi';
import axiosConfig from './apis/axiosConfig';

const api = process.env.REACT_APP_USE_MOCK_API === 'true' ? mockApi : axiosConfig

const fetchRestaurantsData = async () => {
  const response = api.get(`restaurant/all`);
  return response.then(({data}) => data);
};

const fetchProductsData = async (ids) => {
  const promises = ids.map((id) => api.get(`product/${id}`));

  return Promise.all(promises).then((responses) => {
    return responses.map(({data}) => data);
  });
}

export { fetchRestaurantsData, fetchProductsData };