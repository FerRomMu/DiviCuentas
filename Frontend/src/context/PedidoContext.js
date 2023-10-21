import React, { createContext, useContext, useState } from 'react';

const PedidoContext = createContext();

export const usePedido = () => {
  return useContext(PedidoContext);
};

export const PedidoProvider = ({ children }) => {
  const pedidoInicial = {
    owner: 'Ninguno',
    personas : new Map(),
    id: 0
  }
  const [pedido, setPedido] = useState(pedidoInicial);

  const setName = (name) => {
    const pedidoActualizado = { ...pedido };
    pedidoActualizado.owner = name;
    setPedido(pedidoActualizado)
  }

  const agregarPersona = (name) => {
    const pedidoActualizado = { ...pedido };
    pedidoActualizado.owner = name
    pedidoActualizado.personas.set(name, new Map())
    setPedido(pedidoActualizado)
  }

  const agregarProducto = (producto, name) => {
      const pedidoActualizado = { ...pedido }
      if (pedidoActualizado.personas.has(name)) {
        const persona = pedidoActualizado.personas.get(name);
        if (persona.has(producto)) {
          const cantidadExistente = persona.get(producto);
          persona.set(producto, cantidadExistente + 1);
        } else {
          persona.set(producto, 1);
        }
      }
      setPedido(pedidoActualizado);
  };
    const quitarProducto = (product, nombrePersona) => {
      const pedidoActualizado = { ...pedido };

      if (pedidoActualizado.personas.has(nombrePersona)) {
        const productos = pedidoActualizado.personas.get(nombrePersona);
        if (productos.has(product)) {
          productos.delete(product);
        }
      }
      setPedido(pedidoActualizado);
    };


  return (
    <PedidoContext.Provider value={{ pedido, agregarProducto, quitarProducto, setName, agregarPersona }}>
      {children}
    </PedidoContext.Provider>
  );
};

export default usePedido;