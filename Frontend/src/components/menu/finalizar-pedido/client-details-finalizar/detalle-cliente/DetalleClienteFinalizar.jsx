import DetalleProducto from "./detalle-producto/DetalleProductoFinalizar";
import usePedido from '../../../../../context/PedidoContext'

const FinalClientDetails = ({name}) => {

  const { pedido } = usePedido()
  console.log(pedido)

  function keysWithName() {
    const keys = Array.from(pedido.personas.keys())
    const filtered = keys.filter(item => item.includes(name));
    console.log("filtered")
    console.log(filtered)
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
    console.log("updatedArray")
    console.log(updatedArray)
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
      console.log("acaaaa")
      console.log("acaaa")
      console.log("acaa")
      console.log("acaaaa")
      console.log(product.price)
      console.log(product.amount)
      console.log(product.sharedAmount)
      console.log(accumulator)
      return accumulator + ((product.price * product.amount) / product.sharedAmount);
    }, 0)
    console.log("total")
    console.log(total)
    return total
  }

  return (
    <div key={name}>
        <h1>Cuenta de {name}</h1>
        {//Array.from(productsMap.entries()).map(([product, cantidad]) => (
          //  <DetalleProducto key={product.id} product={product} cantidad={cantidad} />
        //))
        }
        <h2>Total a pagar por {name}: ${totalAPagar()}</h2>
    </div>

  )

}

export default FinalClientDetails;