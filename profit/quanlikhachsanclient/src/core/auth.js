import { axiosInstance } from "../reducers/makeAPI";
import jwt_decode from "jwt-decode";
import { ES_001 } from '../constants/ConstApp';


export const login = async (value) => {

  console.log(value);
  let loginPojo = {
    iduser: value.UserName,
    password: value.Password,
    accessToken: "accessToken",
    refreshToken: "refreshToken",
    authenticated: "false",
    role: "staff",
    fullName: "staff"
  }
  console.log("dataSendItem" + JSON.stringify(loginPojo));
  let data = await axiosInstance.post('/login', loginPojo)
    .then(res => {
      console.log(res);
      // set token 


      return res.data;
    }).catch(function (error) {
      console.log(error.response);
      let dataError;
      if (error.response.data.code_error === ES_001) {
        dataError = {
          authenticated: false
          , messerror: ""
        }

      } else {
        dataError = {
          authenticated: false
          , messerror: error.response.data.content_error
        }
      }
      return dataError;
    });
  return data;
}


export const getInfoUser = async () => {
  try {
    let idstaff = localStorage.onlineAcademy_userName
    let { data } = await axiosInstance.get('/staff', {
      params: {
        id: idstaff
      }
    });
    return data[0];
  } catch (error) {
    return error.response;
  }
}


export const changePassWithServer = async (passOld, passNew) => {
  try {
    let idstaff = localStorage.onlineAcademy_userName
    let changePass = {
      idStaff: idstaff,
      passwordOld: passOld,
      passwordNew: passNew,
    }

    let { data } = await axiosInstance.post('/changepass', changePass);
    console.log(data);
    //   //   let { userName, role } = jwt_decode(data.accessToken);
    // if (data.authenticated) {
    //   localStorage.onlineAcademy_accessToken = data.accessToken;
    //   localStorage.onlineAcademy_refreshToken = data.refreshToken;
    //   localStorage.onlineAcademy_authenticated = data.authenticated;
    //   localStorage.onlineAcademy_role = data.role;
    //   localStorage.onlineAcademy_userName = data.username;
    // }
    //   //   data.role = role;
    return data;
  } catch (error) {
    console.log(error.response.data);
    return error.response.data;
  }
}