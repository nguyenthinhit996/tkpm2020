import { axiosInstance } from "../reducers/makeAPI";
import { HeaderAccessToken } from '../core/header'


export const getAllServices = async (value) => {
    try {
        let { data } = await axiosInstance.get('/production', { headers: HeaderAccessToken() });
        console.log(data);
        return data;
    } catch (error) {
        console.log(error.response);
        return error.response;
    }
}

export const putAddProductToCart = async (value) => {
    try {
        console.log(value)
        let { data } = await axiosInstance.put('/detailservices', value, { headers: HeaderAccessToken() });
        console.log("data " + data);
        return data;
    } catch (error) {
        console.log(error.response);
        return error.response;
    }
}

export const DetailservicesUpdate = async (value) => {
    try {
        console.log(value)
        let valueSendToDB = {
            idDetailsServicesEntity: {
                idTicketBooking: value.ticketBookingindetail.idTicketBooking
                , idProduct: value.productDetail.idProduct
            }
            , amount: value.amount
            , startRent: value.startRent
            , status: value.status
            , BigdesumaryMoneySerives: value.bigdesumaryMoneySerives
            , staffService: {
                idStaff: value.staffService.idStaff
            }
            , ticketBookingindetail: {
                idTicketBooking: value.ticketBookingindetail.idTicketBooking
            }
            , productDetail: {
                idProduct: value.productDetail.idProduct
            }
        }
        let { data } = await axiosInstance.post('/detailservicesUpdateOneProduct', valueSendToDB, { headers: HeaderAccessToken() });
        console.log("data " + data);
        return data;
    } catch (error) {
        console.log(error.response);
        return error.response;
    }
}

export const deleteProductToCart = async (value) => {
    try {
        console.log(value)
        await axiosInstance.delete('/detailservices', { headers: HeaderAccessToken(), data: value });
    } catch (error) {
        console.log(error.response);
        return error.response;
    }
}

export const detailServicesByChecking = async (value) => {
    try {
        console.log(value)
        let { data } = await axiosInstance.get('/detailservicesByChecking', {
            params: {
                idticketbooking: value
            }
            , headers: HeaderAccessToken()
        });
        console.log("data " + data);
        return data;
    } catch (error) {
        console.log(error.response);
        return error.response;
    }
}

export const getAllProduct = async (value) => {
    try {
        // console.log(value)
        let { data } = await axiosInstance.get('/production', { headers: HeaderAccessToken(), data: value });
        // console.log("data " + JSON.stringify(data));
        return data;
    } catch (error) {
        console.log(error.response);
        return error.response;
    }
}

export const addNewProduct = async (value) => {
    try {
        console.log(value)
        let { data } = await axiosInstance.post('/production', value);
        console.log("data " + data);
        return data;
    } catch (error) {
        console.log(error.response);
        return error.response;
    }
}

export const editProduct = async (value) => {
    try {
        console.log(value)
        let { data } = await axiosInstance.put('/production', value);
        console.log("data " + data);
        return data;
    } catch (error) {
        console.log(error.response);
        return error.response;
    }
}