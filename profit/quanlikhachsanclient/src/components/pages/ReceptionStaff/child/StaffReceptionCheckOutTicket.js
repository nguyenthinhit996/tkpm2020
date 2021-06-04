import { Grid, List, ListItem, ListItemText } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import { useHistory } from 'react-router-dom';
import './StaffReceptionCheckOutTicket.css'
import { useSnackbar } from 'notistack';
import NavigationAppContext from '../../../../stores/NavigationAppContext'
import Staffreceptionviewdetailsubcharge from './StaffReceptionViewdetailSubcharge';
import StaffReceptionViewdetailDamaged from './StaffReceptionViewdetailDamaged';
import { createCheckoutTicket, getInforCheckOutByIdTicket } from '../../../../core/room';
import CurrencyFormat from 'react-currency-format';

export default function Staffreceptioncheckoutticket(props) {

    const history = useHistory();

    const numberRoom = history.location.state.numberRoom;

    const idticketbooking = history.location.state.idticketbooking;

    // const value = {
    //     name: 'Nguyen van a',
    //     identity: '30125684',
    //     numberInRoom: '4',
    //     timeStartRent: ' 2020/12/12 11:30:30',
    //     timeRent: '2:30',
    //     rateRent: '300000',
    //     rateServices: '3000000',
    //     roomSubCharge: '1000000',
    //     roomDamaged: '0',
    //     toTal: '3000000000'
    // }


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
        await getInforCheckOutByIdTicket(idticketbooking, numberRoom).then(data => {
            setValue(data);
        })
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
        console.log(" dddddddddddddddddddddddddd Paid");

        // 1) tao CheckOut clean
        // 2) cap nhat trang thai cho checkking off

        const dataSendToServer = {
            idticketcheckoutroom: "2021-02-21T15:33:43.814891800",
            idticketbooking: idticketbooking,
            timeendrent: value.timeendrent,
            idstaffreceptionsupport: localStorage.quanlikhachsan_iduser,
            numberroomrent: value.ticketbooking.numberroom,
            sumaryratesandservices: value.sumaryratesandservices,
            raterentroom: value.rateRent,
            rateservices: value.rateservices,
            roomSubCharge: value.roomSubCharge,
            roomDamaged: value.roomDamaged,
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
        // setTimeout(() => {
        //     history.push("/staff");
        // }, 2000);

        history.push({
            pathname: '/rect/staffReceptionViewServices',
            state: {
                numberRoom: numberRoom,
                idticketbooking: idticketbooking
            }
        });
    }

    const [openViewInforSubCharge, setopenViewInforSubCharge] = useState(false);

    const [openViewDamged, setopenViewDamged] = useState(false);

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
                                        <ListItem key={value.ticketbooking.usernamerentroom}>
                                            <ListItemText primary="Name: " />
                                            <ListItemText primary={value.ticketbooking.usernamerentroom} />
                                        </ListItem>

                                        <ListItem key={value.ticketbooking.iduserrentroom}>
                                            <ListItemText primary="Identity: " />
                                            <ListItemText primary={value.ticketbooking.iduserrentroom} />
                                        </ListItem>
                                    </div>

                                    <div>
                                        <ListItem key={value.ticketbooking.numberinroom}>
                                            <ListItemText primary="Number in room: " />
                                            <ListItemText primary={value.ticketbooking.numberinroom} />
                                        </ListItem>

                                        <ListItem key={value.timerenting}>
                                            <ListItemText primary="Time Start Rent: " />
                                            <ListItemText primary={value.ticketbooking.timestamprent} />
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
                {openViewInforSubCharge && <Staffreceptionviewdetailsubcharge numberInRoom={value.ticketbooking.numberinroom} rateSubCharge={value.rateSubChargeInRoom} maxRentNumberInRoom={value.maxRentNumberInRoom} />}
                {openViewDamged && <StaffReceptionViewdetailDamaged listDamaged={value.listDamaged} roomDamaged={value.roomDamaged} />}
            </NavigationAppContext.Provider>
        </>
    )
}
