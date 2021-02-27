import { Container, Grid, List, ListItem, ListItemText, Typography } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form';
import { useHistory } from 'react-router-dom';
import Navigation from '../../layout/Navigation'
import './Staffreceptionviewroom.css'
import '../../layout/Body.css'
import { useSnackbar } from 'notistack';
import { getRoomByNumber } from '../../../core/room';

export default function Staffreceptionviewroom(props) {

    const history = useHistory();

    const numberRoom = history.location.state.numberRoom;
    const idticketbooking = history.location.state.idticketbooking;

    const [value, setvalue] = useState({})

    useEffect(() => {
        getRoomByNumber(idticketbooking).then( data => {
            setvalue(data);
        })
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
          console.log( " dddddddddddddddddddddddddd Checkout"); 
          history.push({
            pathname: '/Staffreceptioncheckoutticket',
            state: { numberRoom: numberRoom,
                idticketbooking:idticketbooking
            }
        });
    }

    const addServiceHandler = () => {
        console.log( " View moreService"); 
        history.push({
          pathname: '/StaffReceptionViewOrders',
          state: { numberRoom: numberRoom,
            idticketbooking:idticketbooking
         }
      });
    }

    return (
        <Container>
            <Typography component="div" className="containerQuanliKhachSan staffreceptionviewroom">
                <Navigation />

                <div className="gridViewBookingRoom" >
                    <h2> Information room number {numberRoom} </h2>

                    <div className="formViewBooking">

                        <List dense={false}>
                            {
                                <div>
                                    <ListItem key={value.name}>
                                        <ListItemText primary="Full Name:" />
                                        <ListItemText primary={value.usernamerentroom} />
                                    </ListItem>

                                    <ListItem key={value.Identity}>
                                        <ListItemText primary="Identity :" />
                                        <ListItemText primary={value.iduserrentroom} />
                                    </ListItem>

                                    <ListItem key={value.numberInRoom}>
                                        <ListItemText primary="Number in room:" />
                                        <ListItemText primary={value.numberinroom} />
                                    </ListItem>

                                    <ListItem key={value.timerenting}>
                                        <ListItemText primary="Time rent:" />
                                        <ListItemText primary={value.idstaffreception} />
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
                                <button onClick={handlerCheckOut}  className="btn--quanlikhachsan btn--quanlikhachsan__checkOut" > CheckOut </button>
                            </Grid>


                        </div>
                    </div>

                </div>
            </Typography>
        </Container>
    )
} 
