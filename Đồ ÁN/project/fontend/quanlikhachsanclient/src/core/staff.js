import { axiosInstance } from "../reducers/makeAPI";


export const getAllStaff = async (idticket) => {
    try {
      let { data } = await axiosInstance.get('/staff');
      console.log(data);
      return data;
    } catch (error) {
      return error.response;
    }
  }

  export const newStaff = async (value) => {
    try {
      let { data } = await axiosInstance.post('/staff', value);
      console.log(data);
      return data;
    } catch (error) {
      console.log(error.response);
      return error.response;
    }
  }


  export const editStaff = async (value) => {
    try {
      let { data } = await axiosInstance.put('/staff', value);
      console.log(data);
      return data;
    } catch (error) {
      console.log(error.response);
      return error.response;
    }
  }