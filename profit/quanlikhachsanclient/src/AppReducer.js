import {LOGIN_SUCCESS} from './constants/ConstApp'

const reducer = (state, action) => {

    switch(action.type){     
        case LOGIN_SUCCESS:
        return {
            ...state,
            role:action.payload.role,
            isLogged: action.payload.isLogged
        }


        default: 
        return state;
    }
}

export default reducer;