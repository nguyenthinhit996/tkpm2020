import axios from 'axios';
import { HeaderRefreshToken,HeaderAccessToken } from '../core/header'

export const axiosInstance = axios.create({
    baseURL: "http://localhost:8089/backendhotel",
    timeout: 300000,
    headers: {
        'Content-Type': 'application/json'
    }
});

axiosInstance.interceptors.response.use(
    (response => {
        console.debug("axiosInstance.interceptors.response");
        console.log(response);
        return response;
    }),
    (error => {
        if(error.isAxiosError && error.message === "Network Error"){
            console.log(error);
            let errorsystem = {
                response:{
                    data:{
                        error:"ES_001",
                        content_error:"Network Error"
                    }
                }
            }
            return Promise.reject(errorsystem);
        }
        const code_error = error.response.data.code_error;
        const originalRequest = error.config;
        console.log(HeaderRefreshToken());
        if (code_error == "EJ_002") { // "JWT EXpried Error, Access Token Expired, Must refresh token"
            return axiosInstance.post("/token", null ,{ headers: HeaderRefreshToken() },)
                .then(res => {
                    console.debug(" new accessToken axiosInstance.post(/token");
                    localStorage.quanlikhachsan_accessToken = res.data.accessToken;
                    originalRequest.headers = HeaderAccessToken();
                    return axiosInstance(originalRequest);
                }).catch(error => {
                    console.log(error);
                    if (error.response.status == 500 || error.response.data.code_error == "EJ_003") //"JWT EXpried Error, Refresh Token Expired, Must login "
                    {
                        delete localStorage.quanlikhachsan_accessToken;
                        delete localStorage.quanlikhachsan_refreshToken;
                        delete localStorage.quanlikhachsan_authen;
                        delete localStorage.quanlikhachsan_role;
                        delete localStorage.quanlikhachsan_iduser;
                        delete localStorage.quanlikhachsan_fullName;
                    }
                    return Promise.reject(error);
                });
        }else if(error.response.status == 500){
            delete localStorage.quanlikhachsan_accessToken;
            delete localStorage.quanlikhachsan_refreshToken;
            delete localStorage.quanlikhachsan_authen;
            delete localStorage.quanlikhachsan_role;
            delete localStorage.quanlikhachsan_iduser;
            delete localStorage.quanlikhachsan_fullName;
            // require login again
        }
        return Promise.reject(error);
    })
)

axiosInstance.interceptors.request.use((req => {
    console.log("axiosInstance.interceptors.request request");
    console.log(req);
    return req;
}),(error => {
    console.log("axiosInstance.interceptors.request Error");
    console.log(error);
}))