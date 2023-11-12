import './DetalleProductoFinalizar.css'


    const DetalleProductoFinalizar = ({ product, cantidad }) => {
        return (
            <section >
                <div className='detalle-producto'>
                  <p>
                    * {product.name} X{cantidad} ${product.price}
                  </p>               
                </div>
            </section>
        );

}

export default DetalleProductoFinalizar;