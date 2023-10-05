import axios from "axios";

const realApi = axios.create({
  baseURL: 'http://localhost:8080/',
});

export default realApi;