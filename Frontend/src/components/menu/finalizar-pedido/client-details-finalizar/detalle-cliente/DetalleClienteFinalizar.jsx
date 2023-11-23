import DetalleProductoFinalizar from "./detalle-producto/DetalleProductoFinalizar";
import usePedido from '../../../../../context/PedidoContext'

const FinalClientDetails = ({name, propina}) => {

  const { pedido } = usePedido()
  console.log(pedido)

  function keysWithName() {
    const keys = Array.from(pedido.personas.keys())
    const filtered = keys.filter(key => key.includes(name));
    //Dejo la logica que si alguien la quiere pasar a javascript funca pa los nombres incluidos como f en fer
    //const filtered = keys.filter(key => key.split(/, | y /).any { n => n.includes(name) });
    return filtered
  }

  function splitedBy(names) {
    const splitedNames = names.split(/, | y /);
  
    return splitedNames.length;
  }

  function mapToProductsUpdated(map, key) {
    const updatedArray = Array.from(map.entries()).map(([obj, value]) => {
      return {
          ...obj,
          amount: value,
          sharedAmount: splitedBy(key)
      };
    });
    return updatedArray
  }

  function allProducts() {
    const products = []
    const keys = keysWithName()
    keys.forEach((key) => {
      const productsMap = pedido.personas.get(key)
      products.push(mapToProductsUpdated(productsMap, key))
    })
    console.log("products")
    console.log(products)
    return products.flat()
  }

  const totalAPagar = () => {
    const total = allProducts().reduce((accumulator, product) => {
      return accumulator + ((product.price * product.amount) / product.sharedAmount);
    }, 0)

    return total
  }

  return (
    <div key={name}>
        <h1>Cuenta de {name}</h1>
          <DetalleProductoFinalizar products={allProducts()} propina={ propina }/>
        <h2>Total a pagar por {name}: ${totalAPagar()}</h2>
    </div>

  )

}

export default FinalClientDetails;