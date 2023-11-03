import { useNavigate } from 'react-router-dom'
import './ModalCancelarPedido.css'

const ModalCancelarPedido = ({close, setOrder}) => {
  const navigate = useNavigate();

  const handleConfirmacion = () => {
     setOrder(false)
     navigate('/');
     window.location.reload();
  }
  
  return (
    <div className="modal-background" onClick={close}>
      <div className="modal-content bg border">
        <h2>¿Esta seguro que desea cancelar el pedido?</h2>
          <div className="co-buttons">
            <button onClick={handleConfirmacion}>Si</button>
            <button onClick={close}>No</button>
          </div>
      </div>
    </div>
    )
}

export default ModalCancelarPedido;