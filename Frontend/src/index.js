import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { ChakraProvider, ColorModeScript } from '@chakra-ui/react';
import theme from "./theme";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { DatabaseProvider } from './Mocking';
import Home from './components/home/Home';
import Views from './components/Views';
import Menu from './components/menu/Menu';
import CreateOrder from "./components/createOrder/CreateOrder";
import ClientDetails from './components/menu/verDetalles/clientDetails/ClientDetails';
import VerDetalles from './components/menu/verDetalles/VerDetalles';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <ChakraProvider theme={theme}>
        <DatabaseProvider>
          <ColorModeScript initialColorMode={theme.config.initialColorMode}/>
          <App />
          <Routes>
            <Route path="/menu" element={<Menu />} />
            <Route path="/home" element={<Home />} />
            <Route path="/createOrder" element={<CreateOrder />} />
          </Routes>
        </DatabaseProvider>
      </ChakraProvider>
    </BrowserRouter>
  </React.StrictMode>
);