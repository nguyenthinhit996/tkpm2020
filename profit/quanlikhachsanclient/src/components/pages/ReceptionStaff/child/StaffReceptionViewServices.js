import { Container, Grid, IconButton, Typography } from '@material-ui/core'
import React, { useContext, useEffect, useState } from 'react'
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import { useHistory } from 'react-router-dom';
import './StaffReceptionViewServices.css'
import { useSnackbar } from 'notistack';
import TableViewOrder from '../../../plugins/TableViewOrder';
import NavigationAppContext from '../../../../stores/NavigationAppContext'
import { detailServicesByChecking } from '../../../../core/product';

// handle error and set loading process
import { HandleGetError, HandleErrorSystem } from '../../../../core/handleDataFromDB'
import {OpenLoadding, OffLoadding} from '../../../../core/Utils'
import Appcontext from '../../../../AppContext';

export default function StaffReceptionViewServices(props) {

    const history = useHistory();

    const {dispatch} = useContext(Appcontext);

    const numberRoom = history.location.state.numberRoom;

    const idticketbooking = history.location.state.idticketbooking;

    // data fetch from server
    const [listRowData, setlistRowData] = useState([]);

    useEffect( async() => {
        OpenLoadding(dispatch);
        let data = await detailServicesByChecking(idticketbooking);
        let messError = HandleGetError(data);
        if(messError.length !== 0){
            OffLoadding(dispatch);
            handlerMessageToast(messError,"error");
            HandleErrorSystem(data,history);
        }else{
            OffLoadding(dispatch);
            setlistRowData(data);
        }
    }, [])

    useEffect(async () => {
        console.log("update list to server");
    }, [listRowData])

    // toast  start
    const [messageToast, setmessageToast] = useState({ message: '', variant: '' });

    useEffect(() => {
        if (messageToast.message.length !== 0) {
            handlerMessageToast(messageToast.message, messageToast.variant);
        }
    }, [messageToast])

    const { enqueueSnackbar } = useSnackbar();


    const handlerMessageToast = (mess, variant) => {
        // variant could be success, error, warning, info, or default
        enqueueSnackbar(mess, { variant });
    };

    const exportToastSuccess = (mess) => {
        let a = 'success';
        setmessageToast({ message: mess, variant: a })
    }
    // toast  enddddddddddddddd

    const backViewRoomHandler = () => {
        history.goBack();
    }

    return (

        <div className="staffReceptionViewServices" >
            <div className="btn-group-control">
                <IconButton onClick={backViewRoomHandler} color="primary" aria-label="upload picture" component="span" className="btn-group-control__SytleColorText">
                    <ArrowBackIosIcon fontSize="small" />
                    <p className="container--removespace"> Back</p>
                </IconButton>
                <h2 className="container--removespace">View Order services of room {numberRoom}</h2>
                <div>
                    <span style={{ visibility: 'hidden' }}>hiddenColumn</span>
                </div>
            </div>
            <Typography component="div" className="group--chooseOrder">
                <NavigationAppContext.Provider value={{ listRowData, setlistRowData }}>
                    <TableViewOrder />
                </NavigationAppContext.Provider>

            </Typography>
        </div>
    )
}
