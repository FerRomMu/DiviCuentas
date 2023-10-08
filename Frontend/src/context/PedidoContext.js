import React, { createContext, useContext, useState } from 'react';

const PedidoContext = createContext();

export const usePedido = () => {
  return useContext(PedidoContext);
};

export const PedidoProvider = ({ children }) => {
  const [pedido, setPedido] = useState([]);

  const agregarProducto = (producto) => {
    const nuevoPedido = [...pedido, producto];
    setPedido(nuevoPedido);
  };

  const quitarProducto = (productoId) => {
    const index = pedido.findIndex(producto => producto.id === productoId);

    // Si se encontró un elemento con el id proporcionado, elimínalo
    if (index !== -1) pedido.splice(index, 1);
    
    setPedido(pedido);
  };

  const confirmarPedido = () => {
    // Lógica para enviar el pedido a la API
    console.log('Pedido confirmado:', pedido);
  };

  return (
    <PedidoContext.Provider value={{ pedido, agregarProducto, quitarProducto, confirmarPedido }}>
      {children}
    </PedidoContext.Provider>
  );
};

export default usePedido;