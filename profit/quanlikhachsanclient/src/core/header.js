
export const HeaderAccessToken = () => {
    var headers = 
        {'Content-type': 'application/json; charset=utf-8'
        , 'Authorization': 'Bearer '+ localStorage.quanlikhachsan_accessToken};
    return headers;
}


export const HeaderRefreshToken = () => {
    var headers = 
        {'Content-type': 'application/json; charset=utf-8'
        , 'Authorization': 'Bearer '+ localStorage.quanlikhachsan_refreshToken};
    return headers;
}