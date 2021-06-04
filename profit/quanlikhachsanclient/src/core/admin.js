
import { axiosInstance } from "../reducers/makeAPI";
import { HeaderAccessToken } from '../core/header'


export const getTypeOfRoom = async () => {
  try {
    let { data } = await axiosInstance.get('/Typeofroom', { headers: HeaderAccessToken() });
    console.log(data);
    return data;
  } catch (error) {
    return error.response;
  }
}


export const updateTypeOfRoom = async (dataSendTo) => {
  try {
    let { data } = await axiosInstance.put('/Typeofroom', { headers: HeaderAccessToken(),data: dataSendTo });
    console.log(data);
    return data;
  } catch (error) {
    return error.response;
  }
}