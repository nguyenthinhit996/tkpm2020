import { axiosInstance } from "../reducers/makeAPI";
import { HeaderAccessToken } from '../core/header'

export const getWorkAllStaff = async () => {
  try {
    let { data } = await axiosInstance.get('/getAllDetailsServicesEntity', { headers: HeaderAccessToken() });
    console.log(data);
    return data;
  } catch (error) {
    console.log(error.response);
    return error.response;
  }
}

export const updateStatusWorkAllStaff = async (value) => {
  try {
    let { data } = await axiosInstance.post('/updateStatusServicesByUserServices', value, { headers: HeaderAccessToken() });
    console.log(data);
    return data;
  } catch (error) {
    console.log(error.response);
    return error.response;
  }
}

export const detailservicesInforDrinkAndFood = async (idticketbooking, idproduct) => {
  try {
    let { data } = await axiosInstance.get('/DetailservicesInforDrinkAndFood', {
      params: {
        idticketbooking: idticketbooking,
        idproduct: idproduct
      },
      headers : HeaderAccessToken()
    });
    console.log(data);
    return data;
  } catch (error) {
    console.log(error.response);
    return error.response;
  }
}

export const updateDamagedListOfRoom = async (value) => {
  try {
    let { data } = await axiosInstance.put('/checkingOutRoomDamaged', value, {headers:HeaderAccessToken()});
    console.log(data);
    return data;
  } catch (error) {
    console.log(error.response);
    return error.response;
  }
}

export const getDailyWorking = async (idtoday) => {
  try {
    let { data } = await axiosInstance.get('/Dailyworking', {
      params: {
        idtoday: idtoday
      }
    });
    console.log(data);
    return data;
  } catch (error) {
    return error.response;
  }
}

export const updateDailyworking = async (value) => {
  try {
    let { data } = await axiosInstance.put('/Dailyworking', value);
    console.log(data);
    return data;
  } catch (error) {
    console.log(error.response);
    return error.response;
  }
}

export const getSalary = async (month) => {
  try {
    let { data } = await axiosInstance.get('/Salary', {
      params: {
        month: month,
      }
    });
    console.log(data);
    return data;
  } catch (error) {
    console.log(error.response);
    return error.response;
  }
}