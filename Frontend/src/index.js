import React from 'react';
import ReactDOM from 'react-dom/client';
import { ChakraProvider, ColorModeScript } from '@chakra-ui/react';
import theme from "./theme";
import App from './App';
import { DatabaseProvider } from './context/Mocking';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>

      <ChakraProvider theme={theme}>
        <DatabaseProvider>
          <ColorModeScript initialColorMode={theme.config.initialColorMode}/>
          <App/>
        </DatabaseProvider>
      </ChakraProvider>

  </React.StrictMode>
);