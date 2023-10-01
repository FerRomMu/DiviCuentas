import React, { useEffect, useState } from 'react';


const ModalCrearPedido = ({ setOrder , setName}) => {

    const [nombre, setNombre] = useState('');
    const [error, setError] = useState('');

    const handleConfirmar = () => {
        if (!nombre.trim()) {
            setError('Debe ingresar un nombre.');
          } else {
            setError('');
            setOrder(true)
            setName(nombre);
          }
    };

    return (
        <div className="">
            <h2>¿Qué desea pedir?</h2>
            <input
                type="text"
                placeholder="Ingresar un nombre"
                value={nombre}
                onChange={(e) => setNombre(e.target.value)}
                required/>
            <button onClick={handleConfirmar}>Confirmar</button>
            {error && <p>{error}</p>}
        </div>
    )


}

export default ModalCrearPedido;