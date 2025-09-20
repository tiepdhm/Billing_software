import axios from "axios";

const API_URL = 'http://localhost:8080/api/v1.0';
const AUTH_HEADER = {
    headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
};

export const latestOrders = async () => {
    return await axios.get(`${API_URL}/orders/latest`, AUTH_HEADER);
};

export const createOrder = async (order) => {
    return await axios.post(`${API_URL}/orders`, order, AUTH_HEADER);
};

export const deleteOrder = async (orderId) => {
    return await axios.delete(`${API_URL}/orders/${orderId}`, AUTH_HEADER);
};
