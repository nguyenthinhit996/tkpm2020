import { Grid } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form';
import { useHistory } from 'react-router-dom';
import './StaffReceptionBookingRoom.css'
import { useSnackbar } from 'notistack';
import { checkingTicket } from '../../../../core/room'

// handle error and set loading process
import { HandleGetError, HandleErrorSystem } from '../../../../core/handleDataFromDB'
import {OpenLoadding, OffLoadding} from '../../../../core/Utils'
import Appcontext from '../../../../AppContext';


export default function Staffreceptionbookingroom(props) {

    const history = useHistory();

    const numberRoom = history.location.state.numberRoom;

    const { register, handleSubmit, watch, errors } = useForm();

    //-----------------toast start
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
        let variant = 'success';
        setmessageToast({ message: mess, variant: variant })
    }

    const exportToastError = (mess) => {
        let variant = 'error';
        setmessageToast({ message: mess, variant: variant })
    }

    const onSubmit = async (data) => {
        console.log(data);
        var value = {
            idticketbooking: "nono",
            iduserrentroom: data.identity,
            usernamerentroom: data.nameRent,
            timestamprent: "2021-02-21T15:33:43.814891800",
            idstaffreception: localStorage.quanlikhachsan_iduser,
            numberroom: numberRoom,
            numberinroom: data.numberinroom,
            status: "On",
        }
        let result = await checkingTicket(value);
        if (result) {
            exportToastSuccess("Success Booking Room (" + numberRoom + ")");
            history.goBack();
        } else {
            exportToastError("Not Complete Booking Room (" + numberRoom + ")");
        }


    }

    const handlerCancelBooking = () => {
        history.goBack();
    }


    return (
        <div className="staffreceptionbookingroom">
            <div className="gridBookingRoom" >
                <h2> Ticket Booking room  {numberRoom} </h2>
                <form className="bookingForm" onSubmit={handleSubmit(onSubmit)} >
                    {/* <h3 className={loginState !== "" ? "aler--error container--removespace" : "display--none"}> {loginState} </h3> */}
                    <div className="form--mod" >
                        <label > Name:  </label>
                        <input name="nameRent"
                            ref={register({ required: true, maxLength: 255 })}
                        />
                        {errors.nameRent && < p className="container--removespace add--space-margin--left aler--error"> * required </p>}
                    </div >

                    <div className="form--mod" >
                        <label > Identity:  </label>
                        <input name="identity" type="number"
                            ref={register({ required: true, maxLength: 9 })}
                        />
                        {errors.identity && < p className="container--removespace add--space-margin--left aler--error"> * required max 9 </p>}
                    </div>

                    <div className="form--mod" >
                        <label > Number in room:  </label>
                        <input name="numberinroom" type="number" min="1" max="20"
                            ref={register({ required: true, maxLength: 20 })}
                        />
                        {errors.numberinroom && < p className="container--removespace add--space-margin--left aler--error"> * required </p>}
                    </div>

                    <div className="btn-bookingRoom">
                        <Grid>
                            <button onClick={handlerCancelBooking} className="btn--quanlikhachsan btn--quanlikhachsan__cancel" > Cancel </button>
                        </Grid>
                        <Grid>
                            <button type="submit" className="btn--quanlikhachsan btn--quanlikhachsan__booking" > Booking </button>
                        </Grid>
                    </div>

                </form >

            </div>

        </div>
    )
}
