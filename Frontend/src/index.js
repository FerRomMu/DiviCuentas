import React from 'react';
import ReactDOM from 'react-dom/client';
import "./theme.css";
import App from './App';
import { DatabaseProvider } from './context/Mocking';
import { PedidoProvider } from './context/PedidoContext';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>

        <PedidoProvider>
          <App/>
        </PedidoProvider>

  </React.StrictMode>
);