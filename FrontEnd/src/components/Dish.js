import React, {useState, useEffect} from 'react'
import { Link, useParams } from 'react-router-dom';
import restaurantService from '../services/restaurant.service';




function Dish() {

    const {id} = useParams();
    const [dish, setDish] = useState([])
   

    useEffect(() => 
        getDishes(id), [id]
    )

    const getDishes = (id) => {
        restaurantService.getAllDishes(id).then((response) => {
            setDish(response.data)
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
        
    };

    return (
        <div className = "container">
            <h1 className = "text-center"> Valgiaračio patiekalai</h1>
            <table className = "table table-striped">
                <thead>
                    <tr>
                        <th> Patiekalo nr.</th>
                        <th> Pavadinimas</th>
                        <th> Aprašymas</th>
                        <th> Į krepšelį?</th>
                    </tr>
                </thead>
                <tbody>
                    {
                       dish.map(
                                dis =>
                                <tr key = {dis.id}>
                                    <td> {dis.id }</td>
                                    <td> {dis.dishName}</td>
                                    <td> {dis.dishDescription}</td>
                                    <td>
                                    <button className='btn btn-success width-2 pt-0 pb-0 mt-0'>Į krepšelį</button>
                                    </td>    
                                </tr>
                        )
                    }
                </tbody>
            </table>

        </div>
    )
}

export default Dish