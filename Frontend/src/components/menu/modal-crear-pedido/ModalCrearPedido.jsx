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
                <div style={{ textAlign: 'center', marginTop: '5vh', transform: 'translateY(-50%)' }}>
                    <div style={{ marginBottom: '10px' }}>
                        <input
                            type="text"
                            placeholder="Ingresar un nombre"
                            value={nombre}
                            onChange={(e) => setNombre(e.target.value)}
                            required
                            style={{ height: '30px', width: '270px',  textAlign: 'center', fontSize: '18px' }}
                        />
                    </div>
                    <div style={{ marginTop: '20px' }}>
                        <button onClick={handleConfirmar} style={{width: '150px'}}>Confirmar</button>
                    </div>
                    {error && <p>{error}</p>}
                </div>
                {/* <div className="close-button" >
                    Cerrar
                </div> */}
            </div>
        </div>
    )


}

export default ModalCrearPedido;