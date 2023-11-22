import DetalleProductoFinalizar from "./detalle-producto/DetalleProductoFinalizar";
import React from 'react';
import usePedido from '../../../../context/PedidoContext';

const ClientDetailsFinalizar = () => {

  const { pedido } = usePedido();
  console.log(pedido)

  const totalAPagarPor = (name) => {
  };

    return (
        <div className="border">
          { pedido.soloPersonas.map((name) => {
            return (<div>{ name }</div>)
          })}
        </div>
    );

};

export default ClientDetailsFinalizar;

/*
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
*/

