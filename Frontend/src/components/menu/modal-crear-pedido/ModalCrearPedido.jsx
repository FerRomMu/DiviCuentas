import React, { useEffect, useState } from 'react';
import './ModalCrearPedido.css';
import usePedido from '../../../context/PedidoContext';


const ModalCrearPedido = ({ close, setOrder, setOpenModal}) => {

    const [nombre, setNombre] = useState('');
    const [error, setError] = useState('');
    const { setName } = usePedido()
    const { agregarPersona } = usePedido();

    const handleConfirmar = () => {
        if (!nombre.trim()) {
            setError('Debe ingresar un nombre.');
          } else {
            setError('');
            setOrder(true);
            setOpenModal(false);
            // setName(nombre);
            agregarPersona(nombre);
          }
    };

    return (
        <div className="modal-background" onClick={close}>
            <div className="modal-content bg border">
                <h2>¿Quién va a pedir?</h2>
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