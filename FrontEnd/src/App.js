import Login from "./components/Login"
import Register from "./components/Register";
// import HeaderComponent from './component/HeaderComponent';

import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import HomePage from "./components/HomePage";

function App() {
  return (
   
      <Router>
        {/* <main className="App"> */}
          <Routes>
            <Route path="/" element={<HomePage/>}/>
            <Route path="/login" index element={<Login/>}/>
            <Route path="/register" element={<Register/>}/>
          </Routes>
        {/* </main> */}
      </Router>
    
  );
}

export default App;