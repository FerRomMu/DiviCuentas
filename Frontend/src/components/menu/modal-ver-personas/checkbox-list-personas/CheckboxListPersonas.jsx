import { useState } from "react";

const CheckboxListPersonas = ({ name, onMark, index}) => {

  const [mark, setMark] = useState(false)
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
                  style={{ marginRight: '7px', width: '18px', height: '18px'}}
              />
              {name}
          </p>
      </div>
  )

}

export default CheckboxListPersonas;