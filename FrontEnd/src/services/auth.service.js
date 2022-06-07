import axios from "axios";

const API_URL = "http://localhost:8080/";
const register = (userName, userPassword, userFirstName, userLastName) => {
  return axios.post(API_URL + "signup", {
    userName,
    userPassword,
    userFirstName,
    userLastName,
  });
};

const login = (userName, userPassword) => {
  return axios
    .post(API_URL + "signin", {
      userName,
      userPassword,
    })
    .then((response) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }
      return response.data;
    });
};
const logout = () => {
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
};

export default AuthService;
