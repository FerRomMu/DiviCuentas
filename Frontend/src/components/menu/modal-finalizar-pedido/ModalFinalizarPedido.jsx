import { useNavigate } from 'react-router-dom'
import './ModalFinalizarPedido.css'

const ModalFinalizarPedido = ({close, setOrder, restaurant}) => {
  const navigate = useNavigate();

  const handleConfirmacion = () => {
     setOrder(false)
     navigate('/fin', { state: { restaurant } });
  }
  
  return (
    <div className="modal-background" onClick={close}>
      <div className="modal-content bg border">
        <h2>¿Esta seguro que desea finalizar el pedido?</h2>
          <div className="co-buttons">
            <button onClick={handleConfirmacion}>Si</button>
            <button onClick={close}>No</button>
          </div>
      </div>
    </div>
    )
}

export default ModalFinalizarPedido;