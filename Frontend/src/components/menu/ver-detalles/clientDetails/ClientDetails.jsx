import DetalleProducto from "./detalleProducto/DetalleProducto";

const ClientDetails = ({name, products}) => {

    return (
        <section>
            <h1>Cuenta de { name }</h1>
            { products.map((product) => <DetalleProducto product={product}/> )}
        </section>
    )

}

export default ClientDetails;