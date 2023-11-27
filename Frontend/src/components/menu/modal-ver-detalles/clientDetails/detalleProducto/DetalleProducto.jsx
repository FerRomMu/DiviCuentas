import './DetalleProducto.css'


    const DetalleProducto = ({ product, cantidad }) => {
        return (
            <section className='detalle-producto'>
                <img src={product.image} alt={product.name} />
                <div className="detalle-texto">
                    <p>{product.name}</p>
                    <p>Precio: {product.price}</p>
                    <p>Cantidad: {cantidad}</p>
                </div>
            </section>
        );

}

export default DetalleProducto;