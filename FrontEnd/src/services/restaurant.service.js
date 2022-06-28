import axios from "../api/axios";
import authHeader from "./auth-header";



const REST_API_URL = 'http://localhost:8080';

class RestaurantService {
    getAllRestaurants(id){
        return axios.get(REST_API_URL + '/api/auth/user/menu/' + id )
}

    getAllDishes(id){
        return axios.get(REST_API_URL + '/api/auth/user/dish/' + id)
    }

    getRestaurants(){
        return axios.get(REST_API_URL + "/api/admin/restaurants")
    }

    deleteRestaurant(id){
        return axios.delete(REST_API_URL + "/api/admin/restaurant/" + id)
    }

    addRestaurant(restaurantDetails){
        return axios.post(REST_API_URL + "/api/admin/restaurant", restaurantDetails)
    }

    updateRestaurant(id, restaurant){
        return axios.put(REST_API_URL + "/api/admin/restaurant/" + id, restaurant)
    }


}

export default new RestaurantService();




