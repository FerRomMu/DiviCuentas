import Home from './components/home/Home';
import Menu from './components/menu/Menu';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import FinalizarPedido from './components/menu/finalizar-pedido/FinalizarPedido';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/menu" element={<Menu />} />
        <Route path="/" element={<Home />} />
        <Route path='/fin' element={<FinalizarPedido/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
