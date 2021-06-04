import { Container, Grid, IconButton, Typography } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import { useHistory } from 'react-router-dom';
import './StaffReceptionViewServices.css'
import { useSnackbar } from 'notistack';
import TableViewOrder from '../../../plugins/TableViewOrder';
import NavigationAppContext from '../../../../stores/NavigationAppContext'
import { detailServicesByChecking } from '../../../../core/product';


export default function StaffReceptionViewServices(props) {

    const history = useHistory();

    const numberRoom = history.location.state.numberRoom;
    const idticketbooking = history.location.state.idticketbooking;

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

    // data fetch from server
    // const rows = [{
    //     nameProduct: "sdfsadf",
    //     amountProduct: "3",
    //     priceProduct: "123",
    //     note: "This is note",
    //     sumPrice: "123000",
    //     statusService: "Prepare"
    // },
    // {
    //     nameProduct: "sdfsadf 1",
    //     amountProduct: "2",
    //     priceProduct: "123",
    //     note: "This is note",
    //     sumPrice: "123000",
    //     statusService: "Prepare"
    // },
    // {
    //     nameProduct: "sdfsadf 2",
    //     amountProduct: "1",
    //     priceProduct: "123",
    //     note: "This is note",
    //     sumPrice: "123000",
    //     statusService: "Done"
    // }
    // ];

    const [listRowData, setlistRowData] = useState([]);

    useEffect(() => {
        detailServicesByChecking(idticketbooking).then(data => {
            setlistRowData(data);
            console.log(data);
        })
    }, [])

    useEffect(async () => {
        console.log("update list to server");

    }, [listRowData])

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
