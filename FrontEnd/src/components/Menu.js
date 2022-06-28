import React, {useState, useEffect} from 'react'
import { Link, useParams } from 'react-router-dom';
import restaurantService from '../services/restaurant.service';




function Menu() {

    const {id} = useParams();
    const [menus, setMenu] = useState([])
  

    useEffect(() => 
        getMenus(id), [id]
    )

    const getMenus = (id) => {
        restaurantService.getAllRestaurants(id).then((response) => {
            setMenu(response.data)
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
        
    };

    return (
        <div className = "container">
            <h1 className = "text-center"> Restorano valgiaraščiai</h1>
            <table className = "table table-striped">
                <thead>
                    <tr>
                        <th> Valgiaraščio nr.</th>
                        <th> Pavadinimas</th>
                        <th> Patiekalai</th>
                    </tr>
                </thead>
                <tbody>
                    {
                       menus.map(
                                menu =>
                                <tr key = {menu.menuId}>
                                    <td> {menu.menuId }</td>
                                    <td> {menu.menuName}</td>
                                    <td>
                                        <Link to={`/dish/${menu.menuId}`} className='link-primary'>Patiekalai</Link>
                                    </td>    
                                </tr>
                        )
                    }
                </tbody>
            </table>

        </div>
    )
}

export default Menu