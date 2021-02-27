import { axiosInstance } from "../reducers/makeAPI";


export const getAllServices = async (value) => {
    try {
        let { data } = await axiosInstance.get('/Production');
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
        let { data } = await axiosInstance.put('/Detailservices', value);
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
        let { data } = await axiosInstance.post('/DetailservicesUpdateOneProduct', value);
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
        await axiosInstance.delete('/Detailservices', value);
    } catch (error) {
        console.log(error.response);
        return error.response;
    }
}

export const detailServicesByChecking = async (value) => {
    try {
        console.log(value)
        let { data } = await axiosInstance.get('/DetailservicesByChecking', {
            params: {
                idticketbooking: value
              }
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
        let { data } = await axiosInstance.get('/Production');
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
        let { data } = await axiosInstance.post('/Production', value);
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
        let { data } = await axiosInstance.put('/Production', value);
        console.log("data " + data);
        return data;
    } catch (error) {
        console.log(error.response);
        return error.response;
    }
}

