const DetalleProducto = ({product}) => {

    return (
        <section>
            <img src={ product.image }/>
            <p>{ product.name }</p>
            <p>{ product.amount }</p>
            <p>{ product.price }</p>
        </section>
    )

}

export default DetalleProducto;