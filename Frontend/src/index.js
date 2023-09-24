import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { ChakraProvider, ColorModeScript } from '@chakra-ui/react';
import theme from "./theme";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { DatabaseProvider } from './Mocking';
import Home from './components/home/Home';
import Views from './components/Views';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <ChakraProvider theme={theme}>
        <DatabaseProvider>
          <ColorModeScript initialColorMode={theme.config.initialColorMode}/>
          <App />
          <Routes>
            {/* <Route path="/" element={<Views />} /> */}
            <Route path="/home" element={<Home />} />
          </Routes>
        </DatabaseProvider>
      </ChakraProvider>
    </BrowserRouter>
  </React.StrictMode>
);