import React, {useState, useEffect} from 'react'
import { Link } from 'react-router-dom';
import axios from "../api/axios";
import restaurantService from '../services/restaurant.service';
import {MdDelete} from "react-icons/md";



function Admin() {

    const [restaurant, setRestaurant] = useState([])
 

    useEffect(() => {
        getRestaurants()
    }, [])

    const getRestaurants = () => {
        restaurantService.getRestaurants().then((response) => {
            setRestaurant(response.data)
            console.log(response.data);
        });
    };

    const handleDelete = (id) => {
        restaurantService.deleteRestaurant(id).then((response) => {
            getRestaurants();
        }).catch(error => {
            console.log(error)
        })
    }

    return (
        <div className = "container">
            
            <h1 className = "text-center"> Administruojami restoranai</h1>
            <Link to = "/add-restaurant" className = "btn btn-primary mb-2" > Naujas restoranas </Link>

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
                       restaurant.map(
                                restauran =>
                                <tr key = {restauran.id}>
                                    <td> {restauran.id }</td>
                                    <td> {restauran.restaurantName}</td>
                                    <td> {restauran.address}</td>    
                                    <td>
                                        <Link to={`/menu/${restauran.id}`} className='link-primary'>Meniu</Link>
                                        <MdDelete style={{color: "orange"}} size={'1.4em'} onClick={() =>handleDelete(restauran.id)}/>
                                        <Link className="btn btn-info" to={`/edit-restaurant/${restauran.id}`} >Keisti</Link>
                                    </td>
                                    

                                </tr>

                        )
                    }

                </tbody>


            </table>

        </div>
    )
}

export default Admin