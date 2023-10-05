import mockApi from './apis/mockApi';
import realApi from './apis/api';

const api = process.env.REACT_APP_USE_MOCK_API === 'true' ? mockApi : realApi
const path = 'restaurant'

const fetchRestaurantsData = async () => {
  const response = await api.get(`${path}/all`);
  return response.data;
};

export { fetchRestaurantsData };

