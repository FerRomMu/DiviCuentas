import axios from 'axios';

export const useService = () => {

    const url_restaurant = 'http://localhost:8080/restaurant';
    const url_product = 'http://localhost:8080/product';

    // ---- Restaurants
    const getRestaurants = () => {
        return axios.get(`${url_restaurant}/all`)
            .then((res) => { return res.data; })
            .catch((e) => console.error(e))
    }

    const createRestaurant = (name, direction, type, image, menu) => {
        const body = {
            name: name,
            direction: direction,
            type: type,
            image: image,
            menu: menu
        }

        return axios.post((`${url_restaurant}`), body)
            .then((res) => console.log(res))
            .catch((e) => console.error(e))
    }

    const restaurant = (id) => {
        return axios.get((`${url_restaurant}/${id}`))
            .then((res) => {
                console.log(res.data)
                return res.data;
            })
            .catch((e) => console.error(e))
    }

    // ---- Products
    const createProduct = (name, image, description, price) => {
        const body = {
            name : name,
            image: image,
            description: description,
            price: price
        }

        return axios.post((`${url_product}`), body)
            .then((res) => console.log(res))
            .catch((e) => console.error(e))
    }

    const product = (id) => {
        return axios.get((`${url_product}/${id}`))
            .then((res) => {
                return res.data;
            })
            .catch((e) => console.error(e))
    }

    return {getRestaurants, createRestaurant, restaurant, createProduct, product}
}

export default useService