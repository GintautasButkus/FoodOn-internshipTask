import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/";
const getPublicContent = () => {
    return axios.get(API_URL + "public");
};

const getUserPage = () => {
    return axios.get(API_URL + "forUser", {headers: authHeader()});
};

const getAdminPage = () => {
    return axios.get(API_URL + "forAdmin", {headers: authHeader()});
}

const UserService = {
    getPublicContent, getUserPage, getAdminPage
};

export default UserService;
