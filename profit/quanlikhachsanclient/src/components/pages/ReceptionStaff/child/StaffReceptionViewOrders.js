import {IconButton, Typography } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import { useHistory } from 'react-router-dom';
import './StaffReceptionViewOrders.css'
import Serviceview from '../../../plugins/ServiceView';
// test image
import { useSnackbar } from 'notistack';
import { getAllServices, putAddProductToCart } from '../../../../core/product';

export default function StaffReceptionViewOrders(props) {

    const history = useHistory();

    const numberRoom = history.location.state.numberRoom;
    const idticketbooking = history.location.state.idticketbooking;

    const [stateData, setstateData] = useState([]);

    useEffect(() => {
        getAllServices().then(value => {
            setstateData(value);
        })
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
        let a = 'success';
        setmessageToast({ message: mess, variant: a })
    }

    const exportToastError = (mess) => {
        let a = 'error';
        setmessageToast({ message: mess, variant: a })
    }

    // toast  enddddddddddddddd

    const addProductHandler = async (idProduct, namePro) => {
        // post detail services 

        const datas = {
            idticketbooking: idticketbooking,
            idproduct: idProduct,
            idstaffservicesrepo: localStorage.quanlikhachsan_iduser,
            amount: 1,
            startrent: "2021-02-21T15:33:43.814891800",
            endrent: "2021-02-21T15:33:43.814891800",
            status: "Prepare",
            sumaryservices: 0
        }

        // const datas = {  
        //     "amount": 1,
        //     "endrent": "2021-02-21T15:33:43.814891800",
        //     "idproduct": "11",
        //     "idstaffservicesrepo": "222",
        //     "idticketbooking": "2021-02-21T16:31:01.102493100",
        //     "startrent": "2021-02-21T15:33:43.814891800",
        //     "status": "Prepare",
        //     "sumaryservices": "0"
        // }

        let status = await putAddProductToCart(datas);

        if (status) {
            let mess = "Add Success Product (" + namePro + ")";
            exportToastSuccess(mess);
        } else {
            let mess = "Error Not add Product (" + namePro + ")";
            exportToastError(mess);
        }
    }

    const backViewRoomHandler = () => {
        history.goBack();
    }


    const handlerViewDetailServicesRoom = () => {
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
                        return <Serviceview key={value.idproduction} value={value} addProductHandler={addProductHandler} />
                    })
                }
            </Typography>
        </div>
    )
}
