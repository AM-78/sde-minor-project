import axios from 'axios';

const getToken = () => {
    return localStorage.getItem('token');
};

const API_URL = 'http://172.31.91.243:8080';


const axiosInstance = axios.create({
    baseURL: API_URL,
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET,POST,PUT,DELETE,OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type, Authorization',
        'Content-Type': 'application/json',
    }
});

export { axiosInstance, getToken }
