import './DetalleProductoFinalizar.css'

const DetalleProductoFinalizar = ({ products }) => {
  const dividedBeetwen = (product) => product.sharedAmount > 1 ? ` (dividido entre ${ product.sharedAmount}) ` : ""

  return (
    <section >
      <div className='detalle-producto-finalizar'>
          {
            products.map( (product) => {
              return <p> * {product.name} X{product.amount}{dividedBeetwen(product)} ${(product.price * product.amount) / product.sharedAmount}</p>
            })
          }
      </div>
    </section>
  );

}

export default DetalleProductoFinalizar;