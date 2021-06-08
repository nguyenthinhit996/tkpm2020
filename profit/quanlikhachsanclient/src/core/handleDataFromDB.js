

export const HandleGetError = (res) => {
    if (undefined !== res.data) {
        if(undefined !== res.data.content_error){// error custom defination
            return res.data.content_error;
        } 
        if(undefined !== res.data.error){
            return res.data.error; //error system
        }
    }
    return "";
}

export const HandleErrorSystem = (res, history) => {
    if (undefined !== res.data) {
        if (res.data.code_error !== undefined && res.data.code_error.includes("ES")) // error system return page login.
        {
            history.push("/"); // return login with error system
        }
        else if(res.status === 500){
            history.push("/"); // return login with error system
        }
    }
}