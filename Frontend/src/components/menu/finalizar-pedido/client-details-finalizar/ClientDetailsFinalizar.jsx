import FinalClientDetails from "./detalle-cliente/DetalleClienteFinalizar";
import React from 'react';

const ClientDetailsFinalizar = ({comensales}) => {

    return (
        <div className="border">
          { comensales.map((name) => {
            return (<FinalClientDetails name={name} />)
          })
          }
        </div>
    );

};

export default ClientDetailsFinalizar;
