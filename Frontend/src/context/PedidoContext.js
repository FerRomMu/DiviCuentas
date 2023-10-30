import React, { createContext, useContext, useState } from 'react';

const PedidoContext = createContext();

export const usePedido = () => {
  return useContext(PedidoContext);
};

export const PedidoProvider = ({ children }) => {
  const pedidoInicial = {
    owner: 'Ninguno',
    personas : new Map(),
    soloPersonas : [],
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
    pedidoActualizado.soloPersonas.push(name)
    pedidoActualizado.personas.set(name, new Map())
    setPedido(pedidoActualizado)
  }

  const parseNames = (names) => {
    const last = names.pop()
    const name = names.length !== 0 ? names.join(', ') + ' y ' + last : last
    console.log(name)
    return name
  }

  const cambiarQuienesPiden = (names) => {
    const pedidoActualizado = { ...pedido };
    const name = Array.isArray(names)? parseNames(names) : names
    console.log(name)
    pedidoActualizado.owner = name
    if (!pedidoActualizado.personas.has(name)) {
      pedidoActualizado.personas.set(name, new Map());
    }
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
    <PedidoContext.Provider value={{ pedido, cambiarQuienesPiden, agregarProducto, quitarProducto, setName, agregarPersona }}>
      {children}
    </PedidoContext.Provider>
  );
};

export default usePedido;