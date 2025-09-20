import axios from "axios";

const API_URL = 'http://localhost:8080/api/v1.0';
const AUTH_HEADER = {
    headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Content-Type': 'application/json' // Đảm bảo gửi JSON
    }
};

export const createStripeOrder = async (data) => {
    console.log("Request Data:", data);
    console.log("Headers:", AUTH_HEADER);
    return await axios.post(`${API_URL}/payments/create-order`, data, AUTH_HEADER);
};

export const verifyPayment = async (paymentData) => {
    return await axios.post(`${API_URL}/payments/verify`, paymentData, AUTH_HEADER);
};
