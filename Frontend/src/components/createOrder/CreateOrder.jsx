import {useState} from "react";
import {useNavigate} from "react-router-dom";

const CreateOrder = () => {

    const [nombre, setNombre] = useState('');
    const navigate = useNavigate();

    const handleConfirmar = () => {
        navigate('/menu/order');
    };

    return (
        <div className="create-order-container">
            <h2>Crear Pedido</h2>
            <input
                type="text"
                placeholder="Nombre"
                value={nombre}
                onChange={(e) => setNombre(e.target.value)}
            />
            <button onClick={handleConfirmar}>Confirmar</button>
        </div>
    )
}

export default CreateOrder;