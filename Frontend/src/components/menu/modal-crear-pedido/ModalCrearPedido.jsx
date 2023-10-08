import React, { useEffect, useState } from 'react';
import './ModalCrearPedido.css';


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
        <div className="modal-background">
            <div className="modal-content bg">
                <h2>¿Qué desea pedir?</h2>
                <input
                    type="text"
                    placeholder="Ingresar un nombre"
                    value={nombre}
                    onChange={(e) => setNombre(e.target.value)}
                    required/>

                <button onClick={handleConfirmar}>Confirmar</button>
                {error && <p>{error}</p>}
                {/* <div className="close-button" >
                    Cerrar
                </div> */}
            </div>
        </div>
    )


}

export default ModalCrearPedido;