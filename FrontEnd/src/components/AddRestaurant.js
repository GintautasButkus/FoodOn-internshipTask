import React, {useState, useEffect} from 'react'
import {Link, useNavigate, useParams } from 'react-router-dom';
import axios from '../api/axios';
import restaurantService from '../services/restaurant.service';



const AddRestaurant = () => {

    const [name, setName] = useState('')
    const [address, setAddress] = useState('')
    const history = useNavigate();
    const {id} = useParams();

    const saveOrUpdateRestaurant = (e) => {
        e.preventDefault();

        const restaurant = {name, address}

        if(id){
            restaurantService.updateRestaurant(id, restaurant).then((response) =>{
                console.log(response.data)
                history.push('/admin')
            }).catch(error => {
                console.log(error)
            })

        }else{
            restaurantService.addRestaurant(restaurant).then((response) =>{

                console.log(response.data)
    
                history.push('/admin');
    
            }).catch(error => {
                console.log(error)
            })
        }
    }

    useEffect(() => {

        restaurantService.getAllRestaurants(id).then((response) =>{
            setName(response.data.name)
            setAddress(response.data.address)
            
        }).catch(error => {
            console.log(error)
        })
    }, [])

    const title = () => {

        if(id){
            return <h2 className = "text-center">Koreguoti restoraną</h2>
        }else{
            return <h2 className = "text-center">Pridėti restoraną</h2>
        }
    }

    return (
        <div>
           <br /><br />
           <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3 offset-md-3">
                       {
                           title()
                       }
                        <div className = "card-body">
                            <form>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Pavadinimas :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Restorano pavadinimas"
                                        name = "pavadinimas"
                                        className = "form-control"
                                        value = {name}
                                        onChange = {(e) => setName(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Adresas :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Adresas"
                                        name = "adresas"
                                        className = "form-control"
                                        value = {address}
                                        onChange = {(e) => setAddress(e.target.value)}
                                    >
                                    </input>
                                </div>

                               

                                <button className = "btn btn-success" onClick = {(e) => saveOrUpdateRestaurant(e)} >Patvirtinti </button>
                                <Link to="/admin" className="btn btn-danger"> Atšaukti </Link>
                            </form>

                        </div>
                    </div>
                </div>

           </div>

        </div>
    )
}

export default AddRestaurant