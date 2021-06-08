import { LOGIN_SUCCESS,ON_LOADING,OFF_LOADING } from './constants/ConstApp'

const reducer = (state, action) => {

    switch (action.type) {
        case LOGIN_SUCCESS:
            return {
                ...state,
                role: action.payload.role,
                isLogged: action.payload.isLogged
            }
        case ON_LOADING:
            return {
                ...state,
                flagLoadding: action.payload.flagLoadding //true
            }
        case OFF_LOADING:
            return {
                ...state,
                flagLoadding: action.payload.flagLoadding // false
            }

        default:
            return state;
    }
}

export default reducer;

