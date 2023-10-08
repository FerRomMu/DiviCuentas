import React from 'react';
import ReactDOM from 'react-dom/client';
import { ChakraProvider, ColorModeScript } from '@chakra-ui/react';
import theme from "./theme";
import App from './App';
import { DatabaseProvider } from './context/Mocking';
import { PedidoProvider } from './context/PedidoContext';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>

      <ChakraProvider theme={theme}>
        <PedidoProvider>
          <ColorModeScript initialColorMode={theme.config.initialColorMode}/>
          <App/>
        </PedidoProvider>
      </ChakraProvider>

  </React.StrictMode>
);