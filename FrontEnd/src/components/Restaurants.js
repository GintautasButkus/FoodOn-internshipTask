import React, {useState, useEffect} from 'react'
import { Link } from 'react-router-dom';
import axios from "../api/axios";


function Restaurants() {

    const [restaurants, setRestaurants] = useState([])
    const ALL_RESTAURANTS = "/api/auth/user/restaurants"

    useEffect(() => {
        getRestaurants()
    }, [])

    const getRestaurants = () => {

        axios.get(ALL_RESTAURANTS).then((response) => {
            setRestaurants(response.data)
            console.log(response.data);
        });
    };

    return (
        <div className = "container">
            
            <h1 className = "text-center"> Restoranai</h1>

            <table className = "table table-striped">
                <thead>
                    <tr>
                        <th> Restorano nr.</th>
                        <th> Pavadinimas</th>
                        <th> Adresas</th>
                        <th> Valgiaraštis</th>
                    </tr>

                </thead>
                <tbody>
                    {
                       restaurants.map(
                                restaurant =>
                                <tr key = {restaurant.id}>
                                    <td> {restaurant.id }</td>
                                    <td> {restaurant.restaurantName}</td>
                                    <td> {restaurant.address}</td>    
                                    <td>
                                        <Link to={`/menu/${restaurant.id}`} className='link-primary'>Meniu</Link>
                                    </td>
                                    

                                </tr>

                        )
                    }

                </tbody>


            </table>

        </div>
    )
}

export default Restaurants