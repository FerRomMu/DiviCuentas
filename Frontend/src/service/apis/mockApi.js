const mockData = {
  'restaurant':{
    'all': {
      data:
        [
          {
            "name":"Dolce Vita Gelato",
            "direction":"Av.San Martín 2090",
            "type":"Helado/Postres",
            "image":"https://media-cdn.tripadvisor.com/media/photo-s/1b/ce/ad/d3/la-location.jpg",
            "menu":[4]
        },
        {
          "name":"Güerrín",
          "direction":"Av.Corrientes 1368",
          "type":"Pizzeria",
          "image":"https://tn.com.ar/resizer/T1HDFanvJCkAI6VkJ6iDZIqzgNQ=/1440x0/smart/filters:format(webp)/cloudfront-us-east-1.images.arcpublishing.com/artear/6BWOOZVNOVBPREIFB5WDT6E7CA.jpg",
          "menu":[3]
        },
        {
          "name":"BurguerBeer",
          "direction":"Av.Sarmiento 5130",
          "type":"Hamburguesas/Comida rápida",
          "image":"https://puntoapunto.com.ar/wp-content/uploads/2022/05/Burgerbeer.jpg",
          "menu":[1,2,1,2,3,1,1]
        }
      ]
    },
  },
  'product': {
    '4': {
      data: {
        "name":"Helado de Frutilla",
        "image":"https://media-cdn.tripadvisor.com/media/photo-s/23/de/56/92/refreshing-red-fruit.jpg",
        "description":"Helado con sabor a Frutilla",
        "price":2000.0
      }
    },
    '3': {
      data: {
        "name":"Pizza Muzza",
        "image":"https://s3.amazonaws.com/arc-wordpress-client-uploads/infobae-wp/wp-content/uploads/2019/06/06170448/GUERRIN-MUZARELLA.jpg",
        "description":"Pizza de Muzzarella",
        "price":5000.0
      }
    },
    '2': {
      data: {
        "name":"Stacker Doble",
        "image":"https://s3-eu-central-1.amazonaws.com/www.burgerking.com.ar.v2/wp-media-folder-burger-king-argentina//home/ubuntu/preview/menu-app/frontend/apps/marketing-website-wordpress-app/web/app/uploads/sites/5/Stacker-Doble.png",
        "description":"2 carnes a la parri, salsa stacker, pan, queso cheddar, panceta.",
        "price":3000.0
      }
    },
    '1': {
      data: {
        "name":"Stacker Triple",
        "image":"https://s3-eu-central-1.amazonaws.com/www.burgerking.com.ar.v2/wp-media-folder-burger-king-argentina//home/ubuntu/preview/menu-app/frontend/apps/marketing-website-wordpress-app/web/app/uploads/sites/5/Stacker-Triple.png",
        "description":"3 carnes a la parrilla, salsa stacker, pan, queso cheddar, panceta.",
        "price":4000.0
      }
    }
  }
};

const mockService = {
  get: async (url) => {
    const parts = url.replace(/^\//, '').split('/');
    const path = parts[0]
    const param = parts[1]
    const data = mockData[path][param]
    return data? data : Promise.reject(new Error('Error en path del mock. ¿Pusiste mal el path?'));
  }
};

export default mockService;