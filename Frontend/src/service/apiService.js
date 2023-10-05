import mockApi from './apis/mockApi';
import axiosConfig from './apis/axiosConfig';

const api = process.env.REACT_APP_USE_MOCK_API === 'true' ? mockApi : axiosConfig
const path = 'restaurant'

const fetchRestaurantsData = async () => {
  const response = await api.get(`${path}/all`);
  return response.data;
};

export { fetchRestaurantsData };
