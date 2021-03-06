
export const SEVERITY_ERROR = 'error';

export const SEVERITY_WARNING = 'warning';

export const SEVERITY_INFO = 'info';

export const SEVERITY_SUCCESS = 'success';
 
export const STAFF_RECEPTION = 'tieptan'

export const STAFF_MANAGER = 'quanli'

export const STAFF_MANAGER_ADMIN = 'admin'

export const STAFF_SERVICE = 'dichvu'

export const getHomePage = () => {
    let role =  localStorage.onlineAcademy_role;
    if(role === STAFF_RECEPTION){
        return '/staffreception';
    }
    if(role === STAFF_MANAGER || role === STAFF_MANAGER_ADMIN){
        return '/staffmanager';
    }
    if(role === STAFF_SERVICE){
        return '/staffservice';
    }
}