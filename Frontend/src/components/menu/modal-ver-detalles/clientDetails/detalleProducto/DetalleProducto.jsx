import './DetalleProducto.css'

const DetalleProducto = ({product}) => {

    return (
        <section className='detalle-producto'>
            <img src={ product.image }/>
            <p>{ product.name }</p>
            <p>{ product.amount }</p>
            <p>{ product.price }</p>
        </section>
    )

}

export default DetalleProducto;