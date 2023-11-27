import FinalClientDetails from "./detalle-cliente/DetalleClienteFinalizar";
import React from 'react';

const ClientDetailsFinalizar = ({comensales, propina}) => {

    return (
        <div className="border">
          { comensales.map((name) => {
            return (<FinalClientDetails name={name} propina={ propina / (comensales.length) }/>)
          })
          }
        </div>
    );

};

export default ClientDetailsFinalizar;
