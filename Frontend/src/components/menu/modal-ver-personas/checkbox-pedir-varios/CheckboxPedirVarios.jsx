const CheckboxPedirVarios = ({pidenVarios, setVarios}) => {

    return (
      <div>
        <input
        type="checkbox"
        checked={pidenVarios}
        onChange={() => setVarios(!pidenVarios)}
        />
        <label htmlFor="pedirDeVarios">Pedir de a varios</label>
      </div>
    )

}

export default CheckboxPedirVarios;