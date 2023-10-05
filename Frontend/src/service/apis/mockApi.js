const mockData = {
  'restaurant/all': {
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
        "menu":[1,2]
      }
    ]
  },
  'otro/ejemplo': {
    data: {
      message: 'Mock data for otro/ejemplo'
    }
  }
};

const mockService = {
  get: async (url) => {
    const path = url.replace(/^\//, '');
    const data = mockData[path]
    return data? data : Promise.reject(new Error('Error en path del mock. ¿Pusiste mal el path?'));
  }
};

export default mockService;