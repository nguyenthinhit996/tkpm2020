import { Grid, List, ListItem, ListItemText } from '@material-ui/core'
import React, { useContext, useEffect, useState } from 'react'
import { useHistory } from 'react-router-dom';
import './StaffReceptionCheckOutTicket.css'
import { useSnackbar } from 'notistack';
import NavigationAppContext from '../../../../stores/NavigationAppContext'
import Staffreceptionviewdetailsubcharge from './StaffReceptionViewdetailSubcharge';
import StaffReceptionViewdetailDamaged from './StaffReceptionViewdetailDamaged';
import { createCheckoutTicket, getInforCheckOutByIdTicket } from '../../../../core/room';
import CurrencyFormat from 'react-currency-format';

// handle error and set loading process
import { HandleGetError, HandleErrorSystem } from '../../../../core/handleDataFromDB'
import { OpenLoadding, OffLoadding } from '../../../../core/Utils'
import Appcontext from '../../../../AppContext';

export default function Staffreceptioncheckoutticket(props) {

    const history = useHistory();

    const { dispatch } = useContext(Appcontext);

    const numberRoom = history.location.state.numberRoom;

    const idticketbooking = history.location.state.idticketbooking;

    const [openViewInforSubCharge, setopenViewInforSubCharge] = useState(false);

    const [openViewDamged, setopenViewDamged] = useState(false);

    const [value, setValue] = useState({
        idticketcheckoutroom: null,
        idticketbooking: "---",
        timeendrent: "---",
        idstaffreceptionsupport: null,
        numberroomrent: 0,
        sumaryratesandservices: 0,
        rateRent: 0,
        rateservices: 0,
        roomSubCharge: 0,
        roomDamaged: 0,
        status: "Shipping",
        ticketbooking: {
            idticketbooking: "---",
            iduserrentroom: "---",
            usernamerentroom: "-",
            timestamprent: "--",
            idstaffreception: "--",
            numberroom: 0,
            numberinroom: 0,
            status: "on"
        },
        timeRent: "---",
        rateSubChargeInRoom: 0,
        maxRentNumberInRoom: 0,
        listDamaged: "None"
    });

    useEffect(async () => {
        OpenLoadding(dispatch);
        let data = await getInforCheckOutByIdTicket(idticketbooking, numberRoom);
        let messError = HandleGetError(data);
        if (messError.length !== 0) {
            OffLoadding(dispatch);
            handlerMessageToast(messError, "error");
            HandleErrorSystem(data, history);
        } else {
            OffLoadding(dispatch);
            setValue(data);
        }
    }, []);

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
        let mess = "Paid Success Room " + numberRoom;
        let variant = 'info';
        setmessageToast({ message: mess, variant: variant })
    }


    const handlerCancelPaid = () => {
        history.goBack();
    }

    const handlerPaidRoom = async () => {

        // 1) tao CheckOut clean
        // 2) cap nhat trang thai cho checkking off

        const dataSendToServer = {
            idTicketCheckout: "now time",
            ticketBooking: {
                idTicketBooking: idticketbooking
            }
            , timeEndRentRoom: value.timeendrent,
            staffCheckoutRoom: {
                idStaff: localStorage.quanlikhachsan_iduser
            }
            , numberRoomRent: value.roomnumber,
            totalRateAll: value.sumaryratesandservices,
            rateRentRoom: value.rateRent,
            rateSevices: value.rateservices,
            rateRoomSubCharge: value.roomSubCharge,
            rateRoomDamaged: value.roomDamaged,
            status: "Clean",  // Clean Off
            timeRent: value.timeRent
        }

        console.log("dataSendToServer" + JSON.stringify(dataSendToServer));

        let dataResult = await createCheckoutTicket(dataSendToServer);

        if (dataResult) {
            exportToastSuccessBooking();
            setTimeout(() => {
                history.push("/rect/staffreception");
            }, 1000);
        }

    }

    const handlerViewDetailServicesRoom = () => {
        exportToastSuccessBooking();
        history.push({
            pathname: '/rect/staffReceptionViewServices',
            state: {
                numberRoom: numberRoom,
                idticketbooking: idticketbooking
            }
        });
    }

    return (
        <>
            <div className="staffreceptioncheckoutticket">
                <div className="gridViewPaidRoom" >
                    <h2> Checkout room number {numberRoom} </h2>
                    <div>
                        <List dense={false}>
                            {
                                <div className="formViewInfoPaidRoom">
                                    <div>
                                        <ListItem key={value.ticketbooking.userNameRentRoom}>
                                            <ListItemText primary="Name: " />
                                            <ListItemText primary={value.ticketbooking.userNameRentRoom} />
                                        </ListItem>

                                        <ListItem key={value.ticketbooking.idUserRentRoom}>
                                            <ListItemText primary="Identity: " />
                                            <ListItemText primary={value.ticketbooking.idUserRentRoom} />
                                        </ListItem>
                                    </div>

                                    <div>
                                        <ListItem key={value.ticketbooking.numberPeopleInRoom}>
                                            <ListItemText primary="Number in room: " />
                                            <ListItemText primary={value.ticketbooking.numberPeopleInRoom} />
                                        </ListItem>

                                        <ListItem key={value.timeStartRentRoom}>
                                            <ListItemText primary="Time Start Rent: " />
                                            <ListItemText primary={value.ticketbooking.timeStartRentRoom} />
                                        </ListItem>
                                    </div>
                                </div>
                            }
                        </List>
                    </div>

                    <h3 className="container--removespace">Bill</h3>

                    <div>
                        <List dense={false}>
                            {
                                <div className="formViewDetailPaidRoom">
                                    <ListItem >
                                        <ListItemText primary={value.timeRent} />
                                        <ListItemText >
                                            <CurrencyFormat value={value.rateRent} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                                        </ListItemText>
                                    </ListItem>

                                    <ListItem >
                                        <ListItemText primary="Services: " />
                                        <ListItemText >
                                            <CurrencyFormat value={value.rateservices} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                                        </ListItemText>
                                        <Grid>
                                            <button onClick={handlerViewDetailServicesRoom} className="btn--quanlikhachsan btn--quanlikhachsan__green__MoreService" > View Detail </button>
                                        </Grid>
                                    </ListItem>

                                    <ListItem >
                                        <ListItemText primary="RoomSubCharge: " />
                                        {/* <ListItemText primary={value.roomSubCharge} /> */}
                                        <ListItemText >
                                            <CurrencyFormat value={value.roomSubCharge} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                                        </ListItemText>
                                        <Grid>
                                            <button onClick={() => setopenViewInforSubCharge(true)} className="btn--quanlikhachsan btn--quanlikhachsan__green__MoreService" > View Detail </button>
                                        </Grid>
                                    </ListItem>

                                    <ListItem >
                                        <ListItemText primary="RoomDamaged: " />
                                        {/* <ListItemText primary={value.roomDamaged} /> */}
                                        {value.status === "Done" &&
                                            <ListItemText >
                                                <CurrencyFormat value={value.roomDamaged} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                                            </ListItemText>
                                        }
                                        {value.status === "Shipping" &&
                                            <ListItemText primary="Shipping " />
                                        }
                                        {value.status === "ToDo" &&
                                            <ListItemText primary="Todo " />
                                        }
                                        <Grid>
                                            <button disabled={value.status !== "Done" ? true : false} onClick={() => setopenViewDamged(true)} className="btn--quanlikhachsan btn--quanlikhachsan__green__MoreService" > View Detail </button>
                                        </Grid>
                                    </ListItem>

                                    <ListItem>
                                        <ListItemText primary="Total: " />
                                        {/* <ListItemText primary={value.sumaryratesandservices} /> */}
                                        <ListItemText >
                                            <CurrencyFormat value={value.sumaryratesandservices} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                                        </ListItemText>
                                        <ListItemText primary="      " />
                                    </ListItem>
                                </div>
                            }
                        </List>

                    </div>

                    <div className="btn-paidRoom--group">
                        <Grid>
                            <button onClick={handlerCancelPaid} className="btn--quanlikhachsan btn--quanlikhachsan__cancel" > Cancel </button>
                        </Grid>
                        <Grid>
                            {/* {disabled={value.status === "Shipping" ? true : false}} */}
                            <button disabled={value.status !== "Done" ? true : false} onClick={handlerPaidRoom} className="btn--quanlikhachsan btn--quanlikhachsan__Paid" > Paid </button>
                        </Grid>
                    </div>

                </div>
            </div>

            <NavigationAppContext.Provider value={{ openViewInforSubCharge, setopenViewInforSubCharge, openViewDamged, setopenViewDamged, }}>
                {openViewInforSubCharge && <Staffreceptionviewdetailsubcharge numberInRoom={value.ticketbooking.numberPeopleInRoom} rateSubCharge={value.rateSubChargeInRoom} maxRentNumberInRoom={value.maxRentNumberInRoom} />}
                {openViewDamged && <StaffReceptionViewdetailDamaged listDamaged={value.listDamaged} roomDamaged={value.roomDamaged} />}
            </NavigationAppContext.Provider>
        </>
    )
}
