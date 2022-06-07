import Login from "./components/Login"
import Register from "./components/Register";
// import HeaderComponent from './component/HeaderComponent';

import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

function App() {
  return (
   
      <Router>
        {/* <main className="App"> */}
          <Routes>
            <Route path="/" index element={<Login/>}/>
            <Route path="/register" element={<Register/>}/>
          </Routes>
        {/* </main> */}
      </Router>
    
  );
}

export default App;