import Login from "./components/Login"
import Register from "./components/Register";
// import HeaderComponent from './component/HeaderComponent';

import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import HomePage from "./components/HomePage";
import Restaurants from "./components/Restaurants";
import Menu from "./components/Menu";

import Dish from "./components/Dish";
import AdminRestaurant from "./components/AdminRestaurant";
import AddRestaurant from "./components/AddRestaurant";





function App() {
  return (
   
      <Router>
        {/* <main className="App"> */}
          <Routes>
            <Route path="/" index element={<HomePage/>}/>
            <Route path="/login" element={<Login/>}/>
            <Route path="/register" element={<Register/>}/>
            <Route path="/restaurants" element={<Restaurants/>}/>
            <Route path="/menu/:id" element = {<Menu/>}/>
            <Route path="/dish/:id" element = {<Dish/>}/>
            <Route path="/admin" element = {<AdminRestaurant/>}/>
            <Route path="/add-restaurant" element={<AddRestaurant/>} />
            <Route path="/edit-restaurant/:id" element={<AddRestaurant/>} />
          </Routes>
        {/* </main> */}
      </Router>
    
  );
}

export default App;