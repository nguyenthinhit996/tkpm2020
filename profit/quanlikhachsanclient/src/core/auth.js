import { axiosInstance } from "../reducers/makeAPI";
import jwt_decode from "jwt-decode";
import { ES_001 } from '../constants/ConstApp'
import { HeaderAccessToken } from './header';


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
  let data = await axiosInstance.post('/login', loginPojo).then(res => {
    console.log(res);
    // set token 
    localStorage.quanlikhachsan_accessToken = res.data.accessToken;
    localStorage.quanlikhachsan_refreshToken = res.data.refreshToken;
    localStorage.quanlikhachsan_authen = res.data.authenticated;
    localStorage.quanlikhachsan_role = res.data.role;
    localStorage.quanlikhachsan_iduser = res.data.iduser;
    localStorage.quanlikhachsan_fullName = res.data.fullName;
    return res.data;
  }).catch(function (error) {
    console.log(error);
    return error.response;
  });
  return data;
}

export const getInfoUser = async () => {
  try {
    console.log(HeaderAccessToken);
    let idstaff = localStorage.quanlikhachsan_iduser
    let { data } = await axiosInstance.get('/staff', { headers: HeaderAccessToken(), params: {id: idstaff} } );
    return data[0];
  } catch (error) {
    return error.response;
  }
}

export const changePassWithServer = async (passOld, passNew) => {
  try {
    let idstaff = localStorage.quanlikhachsan_iduser
    let changePass = {
      idStaff: idstaff,
      passwordOld: passOld,
      passwordNew: passNew,
    }
    let { data } = await axiosInstance.post('/changepass',changePass ,{ headers: HeaderAccessToken()});
    console.log(data);
    return data;
  } catch (error) {
    console.log(error.response);
    return error.response;
  }
}