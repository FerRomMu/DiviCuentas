import DetalleProducto from "./detalleProducto/DetalleProducto";

const ClientDetails = ({pedido}) => {

    return (
        <div className="border">
            {Array.from(pedido.personas.entries()).map(([name, productsMap], index) => {
                const totalPersona = Array.from(productsMap.entries()).reduce((total, [product, cantidad]) => {
                    return total + product.price * cantidad;
                }, 0);

                return (
                    <div key={index}>
                        <h1>Cuenta de {name}</h1>
                        {Array.from(productsMap.entries()).map(([product, cantidad]) => (
                            <DetalleProducto key={product.id} product={product} cantidad={cantidad} />
                        ))}
                        <h2>Total a pagar por {name}: ${totalPersona}</h2>
                    </div>
                );
            })}
        </div>
    )

}

export default ClientDetails;