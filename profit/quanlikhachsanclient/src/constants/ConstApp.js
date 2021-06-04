
export const SEVERITY_ERROR = 'error';

export const SEVERITY_WARNING = 'warning';

export const SEVERITY_INFO = 'info';

export const SEVERITY_SUCCESS = 'success';
 
export const STAFF_RECEPTION = 'tieptan'

export const STAFF_MANAGER = 'quanli'

export const STAFF_MANAGER_ADMIN = 'admin'

export const STAFF_SERVICE = 'dichvu'

export const LOGIN_SUCCESS = 'login_success'

export const getHomePage = () => {
    let role =  localStorage.quanlikhachsan_role;
    if(role === STAFF_RECEPTION){
        return '/rect/staffreception';
    }
    if(role === STAFF_MANAGER || role === STAFF_MANAGER_ADMIN){
        return '/admin/staffmanager';
    }
    if(role === STAFF_SERVICE){
        return '/staffservice';
    }
}

export const ES_001 ="ES_001";