import { useState } from "react";

const CheckboxListPersonas = ({owner, name, onMark, index}) => {

  const [mark, setMark] = useState(owner === name)
  const handleCheckboxChange = () => {
    onMark(index, !mark)
    setMark(!mark)
  }

  return (
    <div key={name}>
      <p>
      <input
        type="checkbox"
        checked={mark}
        onChange={() => handleCheckboxChange(name)}
      />
      {name}</p>
    </div>
  )

}

export default CheckboxListPersonas;