import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ToggleColorMode from "./components/ToggleColorMode.jsx";
import Views from "./components/Views.jsx";
import Home from "./components/home/Home.jsx";

function App() {
  return (
    <>
    {/* <Views/> */}
    <ToggleColorMode></ToggleColorMode>
    </>
  );
}

export default App;
