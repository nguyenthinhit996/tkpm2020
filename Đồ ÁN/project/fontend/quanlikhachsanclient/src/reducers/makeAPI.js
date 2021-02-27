import axios from 'axios';


export const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/sourcebackend",
    timeout: 300000,
    headers: {
        'Content-Type': 'application/json'
    }
});