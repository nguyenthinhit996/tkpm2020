import { Grid, List, ListItem, ListItemText } from '@material-ui/core'
import React, { useContext, useEffect, useState } from 'react'
import { useHistory } from 'react-router-dom';
import './Staffreceptionviewroom.css'
import { useSnackbar } from 'notistack';
import { getRoomByNumber } from '../../../../core/room';

// handle error and set loading process
import { HandleGetError, HandleErrorSystem } from '../../../../core/handleDataFromDB'
import {OpenLoadding, OffLoadding} from '../../../../core/Utils'
import Appcontext from '../../../../AppContext';

export default function Staffreceptionviewroom(props) {

    const history = useHistory();
    const {dispatch} = useContext(Appcontext);

    const numberRoom = history.location.state.numberRoom;
    const idticketbooking = history.location.state.idticketbooking;

    const [value, setvalue] = useState({})

    useEffect(async () => {
        OpenLoadding(dispatch);
        let data = await getRoomByNumber(idticketbooking);
        let messError = HandleGetError(data);
        if(messError.length !== 0){
            OffLoadding(dispatch);
            handlerMessageToast(messError,"error");
            HandleErrorSystem(data,history);
        }else{
            setvalue(data);
            OffLoadding(dispatch);
        }
    }, [])

    // const value = {
    //     name: 'Nguyen van abc',
    //     identity: '30125684',
    //     numberInRoom: '4',
    //     timerenting: '2:30',
    // }


    //-----------------toast start
    const [messageToast, setmessageToast] = useState({ message: '', variant: '' });

    useEffect(() => {
        if (messageToast.message.length !== 0) {
            handlerMessageToast(messageToast.message, messageToast.variant);
        }
    }, [messageToast])

    const { enqueueSnackbar } = useSnackbar();

    //-----------------toast end

    const handlerMessageToast = (mess, variant) => {
        // variant could be success, error, warning, info, or default
        enqueueSnackbar(mess, { variant });
    };

    const exportToastSuccessBooking = () => {
        let mess = "Booking Success ";
        let variant = 'success';
        setmessageToast({ message: mess, variant: variant })
    }

    const onSubmit = data => {
        exportToastSuccessBooking();

        history.goBack();
    }

    const handlerCancelBooking = () => {
        history.goBack();
    }

    const handlerCheckOut = () => {
        console.log(" dddddddddddddddddddddddddd Checkout");
        history.push({
            pathname: '/rect/staffreceptioncheckoutticket',
            state: {
                numberRoom: numberRoom,
                idticketbooking: idticketbooking
            }
        });
    }

    const addServiceHandler = () => {
        console.log(" View moreService");
        history.push({
            pathname: '/rect/staffReceptionViewOrders',
            state: {
                numberRoom: numberRoom,
                idticketbooking: idticketbooking
            }
        });
    }

    return (

        <div className="staffreceptionviewroom">
            <div className="gridViewBookingRoom" >
                <h2> Information room number {numberRoom} </h2>

                <div className="formViewBooking">

                    <List dense={false}>
                        {
                            <div>
                                <ListItem key={value.userNameRentRoom}>
                                    <ListItemText primary="Full Name:" />
                                    <ListItemText primary={value.userNameRentRoom} />
                                </ListItem>

                                <ListItem key={value.idUserRentRoom}>
                                    <ListItemText primary="Identity :" />
                                    <ListItemText primary={value.idUserRentRoom} />
                                </ListItem>

                                <ListItem key={value.numberPeopleInRoom}>
                                    <ListItemText primary="Number in room:" />
                                    <ListItemText primary={value.numberPeopleInRoom} />
                                </ListItem>

                                <ListItem key={value.timeStartRentRoom}>
                                    <ListItemText primary="Time start:" />
                                    <ListItemText primary={value.timeStartRentRoom} />
                                </ListItem>

                            </div>
                        }
                    </List>
                </div>

                <div className="btn-viewbookingRoom--group">
                    <Grid>
                        <button onClick={addServiceHandler} className="btn--quanlikhachsan btn--quanlikhachsan__green__MoreService" > More Services </button>
                    </Grid>

                    <div className="btn-CheckOutRoom--group">
                        <Grid>
                            <button onClick={handlerCancelBooking} className="btn--quanlikhachsan btn--quanlikhachsan__cancel" > Cancel </button>
                        </Grid>
                        <Grid>
                            <button onClick={handlerCheckOut} className="btn--quanlikhachsan btn--quanlikhachsan__checkOut" > CheckOut </button>
                        </Grid>
                    </div>
                </div>

            </div>
        </div>
    )
}
