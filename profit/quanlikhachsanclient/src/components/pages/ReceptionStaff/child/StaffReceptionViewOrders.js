import {IconButton, Typography } from '@material-ui/core'
import React, { useContext, useEffect, useState } from 'react'
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import { useHistory } from 'react-router-dom';
import './StaffReceptionViewOrders.css'
import Serviceview from '../../../plugins/ServiceView';
// test image
import { useSnackbar } from 'notistack';
import { getAllServices, putAddProductToCart } from '../../../../core/product';

// handle error and set loading process
import { HandleGetError, HandleErrorSystem } from '../../../../core/handleDataFromDB'
import {OpenLoadding, OffLoadding} from '../../../../core/Utils'
import Appcontext from '../../../../AppContext';

export default function StaffReceptionViewOrders(props) {

    const history = useHistory();

    const {dispatch} = useContext(Appcontext);

    const numberRoom = history.location.state.numberRoom;
    const idticketbooking = history.location.state.idticketbooking;

    const [stateData, setstateData] = useState([]);

    useEffect( async () => {
        OpenLoadding(dispatch);
        let data = await getAllServices();
        let messError = HandleGetError(data);
        if(messError.length !== 0){
            OffLoadding(dispatch);
            handlerMessageToast(messError, "error");
            HandleErrorSystem(data,history);
        }else{
            setstateData(data);
            OffLoadding(dispatch);
        }
    }, [])

    // data fetch from server

    // const data = [{
    //     idProduct: Math.random(),
    //     imageSrc:'../../asset/images/2.JPG',
    //     nameProduct: "Name Product 1",
    //     cardContent: " this is contect of product",
    //     moneyProduct: "5000000"
    // }, {
    //     idProduct: Math.random(),
    //     imageSrc: images,
    //     nameProduct: "Name Product 2",
    //     cardContent: " this is contect of product",
    //     moneyProduct: "5000000"
    // }, {
    //     idProduct: Math.random(),
    //     imageSrc: images,
    //     nameProduct: "Name Product 3",
    //     cardContent: " this is contect of product",
    //     moneyProduct: "5000000"
    // }, {
    //     idProduct: Math.random(),
    //     imageSrc: images,
    //     nameProduct: "Name Product 4",
    //     cardContent: " this is contect of product",
    //     moneyProduct: "5000000"
    // },
    // {
    //     idProduct: Math.random(),
    //     imageSrc: images,
    //     nameProduct: "Name Product 5",
    //     cardContent: " this is contect of product",
    //     moneyProduct: "5000000"
    // },
    // {
    //     idProduct: Math.random(),
    //     imageSrc: images,
    //     nameProduct: "Name Product 6",
    //     cardContent: " this is contect of product",
    //     moneyProduct: "5000000"
    // }]

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
        setmessageToast({ message: mess, variant: "success" })
    }

    const exportToastError = (mess) => {
        setmessageToast({...messageToast,message:mess,variant: "error"});
    }

    // toast  enddddddddddddddd

    const addProductHandler = async (value) => {
        // post detail services 

        const datas = {
            idDetailsServicesEntity: {
                idTicketBooking: idticketbooking,
                idProduct: value.idProduct
            }
            ,
            staffService: {
                idStaff : localStorage.quanlikhachsan_iduser
            },
            amount: 1,
            startRent: "2021-02-21T15:33:43.814891800",
            status: "Prepare",
            BigdesumaryMoneySerives: 0 
        }

        OpenLoadding(dispatch);
        let data = await putAddProductToCart(datas);
        let messError = HandleGetError(data); 
        if(messError.length !== 0){
            OffLoadding(dispatch);
            handlerMessageToast(messError,"error");
            HandleErrorSystem(data,history);
        }else{
            OffLoadding(dispatch);
            if (data) {
                let mess = "Add Success Product (" + value.nameProduct + ")";
                exportToastSuccess(mess);
            } else {
                let mess = "Error Not add Product (" + value.nameProduct + ")";
                exportToastError(mess);
            }
        } 
    }

    const backViewRoomHandler = () => {
        history.goBack();
    }


    const handlerViewDetailServicesRoom = () => {
        history.push({
            pathname: '/rect/staffReceptionViewServices',
            state: {
                numberRoom: numberRoom,
                idticketbooking: idticketbooking
            }
        });
    }

    return (

        <div className="staffReceptionViewOrders">
            <div className="btn-group-control">
                <IconButton onClick={backViewRoomHandler} color="primary" aria-label="upload picture" component="span" className="btn-group-control__SytleColorText">
                    <ArrowBackIosIcon fontSize="small" />
                    <p className="container--removespace"> Back</p>
                </IconButton>
                <h2 className="container--removespace">Choose services for room {numberRoom}</h2>
                <button onClick={handlerViewDetailServicesRoom} className="btn--quanlikhachsan btn--quanlikhachsan__green__MoreService" > View Orders </button>
            </div>
            <Typography component="div" className="group--chooseOrder">

                {
                    stateData.map(value => {
                        return <Serviceview key={value.idProduct} value={value} addProductHandler={addProductHandler} />
                    })
                }
            </Typography>
        </div>
    )
}
