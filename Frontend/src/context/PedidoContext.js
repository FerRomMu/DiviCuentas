import React, { createContext, useContext, useState } from 'react';

const PedidoContext = createContext();

export const usePedido = () => {
  return useContext(PedidoContext);
};

export const PedidoProvider = ({ children }) => {
  const pedidoInicial = {
    owner: 'Ninguno',
    products: [],
    id: 0
  }
  const [pedido, setPedido] = useState(pedidoInicial);

  const setName = (name) => {
    const pedidoActualizado = { ...pedido };
    pedidoActualizado.owner = name;
    setPedido(pedidoActualizado)
  }

  const agregarProducto = (producto) => {
    const pedidoActualizado = { ...pedido };
    pedidoActualizado.products.push(producto);
    setPedido(pedidoActualizado);
  };

  const quitarProducto = (productoId) => {
    const pedidoActualizado = { ...pedido };

    const indiceProducto = pedidoActualizado.products.findIndex(
      (producto) => producto.id === productoId
    );

    if (indiceProducto !== -1) {
      pedidoActualizado.products.splice(indiceProducto, 1);
      setPedido(pedidoActualizado);
    };
  };

  return (
    <PedidoContext.Provider value={{ pedido, agregarProducto, quitarProducto, setName }}>
      {children}
    </PedidoContext.Provider>
  );
};

export default usePedido;