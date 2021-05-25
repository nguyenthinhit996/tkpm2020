
import { axiosInstance } from "../reducers/makeAPI";


export const getTypeOfRoom = async () => {
    try {
      let { data } = await axiosInstance.get('/Typeofroom');
      console.log(data);
      return data;
    } catch (error) {
      return error.response;
    }
  }

 
  export const updateTypeOfRoom = async (dataSendTo) => {
    try {
      let { data } = await axiosInstance.put('/Typeofroom',dataSendTo);
      console.log(data);
      return data;
    } catch (error) {
      return error.response;
    }
  }