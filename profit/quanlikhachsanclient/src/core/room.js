import { axiosInstance } from "../reducers/makeAPI";
import { HeaderAccessToken } from '../core/header'


export const detailAllRoom = async (value) => {
  try {
    console.log(HeaderAccessToken());
    let { data } = await axiosInstance.get('/detailAllRoom', { headers: HeaderAccessToken() });
    console.log(data);
    return data;
  } catch (error) {
    console.log(error.response);
    return error.response;
  }
}

export const checkingTicket = async (value) => {
  try {
    let { data } = await axiosInstance.post('/ticketbooking', value, { headers: HeaderAccessToken() });
    console.log(data);
    return data;
  } catch (error) {
    console.log(error.response);
    return error.response;
  }
}

export const getRoomByNumber = async (idticket) => {
  try {
    let { data } = await axiosInstance.get('/ticketbooking', {
      params: {
        id: idticket
      },
      headers: HeaderAccessToken()
    });
    console.log(data);
    return data[0];
  } catch (error) {
    return error.response;
  }
}


export const getInforCheckOutByIdTicket = async (idticket, numberroom) => {
  try {
    console.log(" Check out room : " + idticket);

    let { data } = await axiosInstance.get('/ticketcheckoutroombyIdTicketChecking', {
      params: {
        id: idticket,
        numberroom: numberroom
      },
      headers: HeaderAccessToken()
    });
    console.log(data);
    return data;
  } catch (error) {
    return error.response;
  }
}

//   export const createCheckoutRoom = async (value) => {
//     try {
//         let { data } = await axiosInstance.post('/Ticketcheckoutroom',value);
//         console.log(data);
//         return data;
//     } catch (error) {
//           console.log(error.response);
//           return error.response;
//     }
// } 

export const createCheckoutTicket = async (value) => {
  try {
    console.log(value);
    let { data } = await axiosInstance.post('/ticketcheckoutroom', value, { headers: HeaderAccessToken() });
    console.log(data);
    return data;
  } catch (error) {
    console.log(error.response);
    return error.response;
  }
}

export const getRevenue = async () => {
  try {
    let { data } = await axiosInstance.get('/Revenue');
    console.log(data);
    return data;
  } catch (error) {
    console.log(error.response);
    return error.response;
  }
}
