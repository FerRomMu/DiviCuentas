import DetalleProductoFinalizar from "./detalle-producto/DetalleProductoFinalizar";

const ClientDetailsFinalizar = ({pedido}) => {

    return (
        <div className="border">
            {Array.from(pedido.personas.entries()).map(([name, productsMap], index) => {
                const totalPersona = Array.from(productsMap.entries()).reduce((total, [product, cantidad]) => {
                    return total + product.price * cantidad;
                }, 0);
                return (
                    <div key={index}>
                        <h1> {name}: ${totalPersona}</h1>
                        {Array.from(productsMap.entries()).map(([product, cantidad]) => (
                            <DetalleProductoFinalizar key={product.id} product={product} cantidad={cantidad} />
                        ))}
                    </div>
                );
            })}
        </div>
    )

}

export default ClientDetailsFinalizar;