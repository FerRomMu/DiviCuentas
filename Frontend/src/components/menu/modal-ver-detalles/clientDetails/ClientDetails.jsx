import DetalleProducto from "./detalleProducto/DetalleProducto";

const ClientDetails = ({pedido}) => {

    return (
        <div className="border">
            {Array.from(pedido.personas.entries()).map(([name, productsMap], index) => (
                <div key={index}>
                    <h1>Cuenta de {name}</h1>
                    {Array.from(productsMap.entries()).map(([product, cantidad]) => (
                        <DetalleProducto key={product.id} product={product} cantidad={cantidad} />
                    ))}
                </div>
            ))}
        </div>
    )

}

export default ClientDetails;