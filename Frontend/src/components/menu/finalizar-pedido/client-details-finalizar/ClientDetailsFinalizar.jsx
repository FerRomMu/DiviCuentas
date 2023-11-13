import DetalleProductoFinalizar from "./detalle-producto/DetalleProductoFinalizar";
import React from 'react';

const ClientDetailsFinalizar = ({pedido}) => {

    const totalAPagarPor = (name) => {
        const soloPersonas = pedido.soloPersonas;
        let cuentaDeLaPersona = 0;

        soloPersonas.forEach((persona) => {
            const integrantesYGrupos = Array.from(pedido.personas.keys());
            const gruposEnLosQuePidio = integrantesYGrupos.filter((grupo) => grupo.includes(name));

            gruposEnLosQuePidio.forEach((grupo) => {
                const productosDelGrupo = pedido.personas.get(grupo);
                let totalAPagarPorGrupo = 0;

                for (const [product, cantidad] of productosDelGrupo.entries()) {
                    const { price } = product;
                    const precioTotalPorProducto = price * cantidad;
                    totalAPagarPorGrupo += precioTotalPorProducto;
                }

                let cantidadDePersonasDelGrupo = grupo.split(/, | y /).length;
                cuentaDeLaPersona = totalAPagarPorGrupo / cantidadDePersonasDelGrupo;
            })
        });
        console.log(name)
        console.log(cuentaDeLaPersona)
        return cuentaDeLaPersona;
    };

    return (
        <div className="border">
            {Array.from(pedido.personas.entries()).map(([name, productosMap], index) => {
                const totalPersona = totalAPagarPor(name);

                return (
                    <div key={index}>
                        <h1>{name}:</h1>
                        {productosMap.size > 0 ? (
                            Array.from(productosMap.entries()).map(([producto, cantidad]) => (
                                <div key={producto.id}>
                                    <p>{producto.name}: {producto.price}</p>
                                    {name.split(/, | y /).length === 1 ? (
                                        <p>Total a Pagar: {producto.price * cantidad + totalPersona}</p>
                                    ) : (
                                        <p></p>
                                    )}

                                    <hr />
                                </div>
                            ))
                        ) : (
                            <p>No ha pedido productos</p>
                        )}
                        {name.split(/, | y /).length === 1 && (
                            <p>Total compartido: {totalPersona}</p>
                        )}
                    </div>
                );
            })}
        </div>
    );

};

export default ClientDetailsFinalizar;

    // return (
    //     <div className="border">
    //         {Array.from(pedido.personas.entries()).map(([name, productsMap], index) => {
    //             {console.log(pedido.personas.entries())}
    //             const totalPersona = totalAPagarPor(name);
    //
    //             return (
    //                 <div key={index}>
    //                     <h1>{name}:</h1>
    //                     {Array.from(productsMap.entries()).map(([product, cantidad]) => (
    //                         <React.Fragment key={product.id}>
    //                             <p>{product.name}: {product.price}</p>
    //                             <p>Cantidad: {cantidad}</p>
    //                             <p>Total a Pagar: {totalPersona}</p>
    //                             <hr />
    //                         </React.Fragment>
    //                     ))}
    //                 </div>
    //             );
    //         })}
    //     </div>
    // );


    // return (
    //     <div className="border">
    //
    //         {Array.from(pedido.personas.entries()).map(([name, productsMap], index) => {
    //             const totalPersona = Array.from(productsMap.entries()).reduce((total, [product, cantidad]) => {
    //                 return total + product.price * cantidad;
    //             }, 0);
    //             return (
    //                 <div key={index}>
    //                     <h1> {name}: ${totalPersona}</h1>
    //                     {Array.from(productsMap.entries()).map(([product, cantidad]) => (
                 //          <DetalleProductoFinalizar key={product.id} product={product} cantidad={cantidad} />
    //                     ))}
    //                 </div>
    //             );
    //         })}
    //     </div>
    // )


