
import {ON_LOADING,OFF_LOADING } from '../constants/ConstApp'

export const OpenLoadding = (dispatch) =>{
    dispatch({
        type: ON_LOADING,
        payload: {
            flagLoadding: true
        }
    })
}

export const OffLoadding = (dispatch) =>{

    dispatch({
        type: OFF_LOADING,
        payload: {
            flagLoadding: false
        }
    })
}