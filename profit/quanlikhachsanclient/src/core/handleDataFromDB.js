

export const HandleError = (res) => {
    if(undefined !== res.data)
    {
        if(res.data.code_error == "ES_001") // error system return page login.
        {
           return true;
        } 
    }
    return false;
}