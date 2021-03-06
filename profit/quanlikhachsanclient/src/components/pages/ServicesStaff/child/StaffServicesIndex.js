import { Button, Container, InputLabel, makeStyles, NativeSelect, Paper, TextField, Typography } from '@material-ui/core'
import React, { useContext, useEffect, useRef, useState } from 'react'
import Navigation from '../../../layout/Navigation'
import { SEVERITY_INFO, SEVERITY_WARNING } from '../../../../constants/ConstApp'
import NavigationAppContext from '../../../../stores/NavigationAppContext'
import { useSnackbar } from 'notistack';
import './StaffServicesIndex.css'
import { useHistory } from 'react-router-dom'
import { getWorkAllStaff, updateStatusWorkAllStaff, detailservicesInforDrinkAndFood } from '../../../../core/workstaff'
import DialogUpdateViewInfo from './DialogUpdateViewInfo'
import DialogUpdateDamaged from './DialogUpdateDamaged'

// handle error and set loading process
import { HandleGetError, HandleErrorSystem } from '../../../../core/handleDataFromDB'
import { OpenLoadding, OffLoadding } from '../../../../core/Utils'
import Appcontext from '../../../../AppContext';
import Staffserviceindextable from '../../../plugins/PageStaffServices/StaffServiceIndexTable'

const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

// 3 table 
// checkingoutroomdamaged check hu hai Checking -> DoneCheck
// ticketcheckoutroom cap nhat tu clean -> off
// detailservices  prepare-> shipping ( reception send to user service) -> Todo(userservices doing) -> Done

// StaffServicesIndex only view status Shipping Todo Done 
export default function StaffServicesIndex(props) {

    const { dispatch } = useContext(Appcontext);

    const history = useHistory();

    const classes = useStyles();

    const [dataFetchIsServer, setdataFetchIsServer] = useState([]);

    useEffect(async () => {
        OpenLoadding(dispatch);
        let data = await getWorkAllStaff();
        let messError = HandleGetError(data);
        if (messError.length !== 0) {
            OffLoadding(dispatch);
            handlerMessageToast(messError, "error");
            HandleErrorSystem(data, history);
        } else {
            OffLoadding(dispatch);
            setdataFetchIsServer([...data]);
        }
    }, [])

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
        let a = 'success';
        setmessageToast({ message: mess, variant: a })
    }

    const exportToastError = (mess) => {
        let a = 'error';
        setmessageToast({ message: mess, variant: a })
    }

    //--------------------toast end

    const changeSelectStatusHandler = async (row, event) => {
        console.log(event);
        console.log(row + " " + event.target.value);

        var foundIndex = dataFetchIsServer.findIndex(x =>
            x.detailservicesIdticketbooking === row.detailservicesIdticketbooking
            && x.detailservicesidproduct === row.detailservicesidproduct
            && x.idcheckingoutroomdamaded === row.idcheckingoutroomdamaded
            && x.idstaff === row.idstaff
            && x.idticketcheckoutroom == row.idticketcheckoutroom
            && x.numberroom === row.numberroom
            && x.typeservices === row.typeservices
            && x.username === row.username);
        console.log(foundIndex);
        var item = dataFetchIsServer[foundIndex];
        item.username = localStorage.quanlikhachsan_fullName;
        dataFetchIsServer[foundIndex] = { ...item, status: event.target.value, idstaff: localStorage.quanlikhachsan_iduser }
        setdataFetchIsServer([...dataFetchIsServer]);

        var dataSendUPdate = dataFetchIsServer[foundIndex];
        // update to server
        let result = await updateStatusWorkAllStaff(dataSendUPdate);
        if (result) {
            exportToastSuccess("Update success");
        } else {
            exportToastError("Error Not Update");
        }
    }

    const [stateInforDialog, setstateInforDialog] = useState({
        status: false,
        data: "",
        statusDamaged: false,
        numberroom: -1,
        idDamaged: "000"
    })

    const handlerButtonDetailViewServices = async (row) => {

        if (row.status === "ToDo") {
            if (row.typeservices === "Drinkandfood" || row.typeservices === "MotoBike") {
                console.log("Drink and food")
                let dataresult = await detailservicesInforDrinkAndFood(row.detailservicesIdticketbooking, row.detailservicesidproduct);
                stateInforDialog.status = true;
                stateInforDialog.data = dataresult;
                setstateInforDialog({ ...stateInforDialog });
            }
            if (row.typeservices === "CleanRoom") {
                console.log("CleanRoom")
                stateInforDialog.status = true;
                stateInforDialog.data = "Clean Room number: " + row.numberroom;
                setstateInforDialog({ ...stateInforDialog });
            }
            if (row.typeservices === "Checkoutdamaged") {
                console.log("Checkoutdamaged")
                // let dataresult = await detailservicesInforDrinkAndFood(row.detailservicesIdticketbooking, row.detailservicesidproduct);
                stateInforDialog.statusDamaged = true;
                stateInforDialog.numberroom = row.numberroom;
                stateInforDialog.idDamaged = row.idcheckingoutroomdamaded;
                setstateInforDialog({ ...stateInforDialog });
            }
        }
    }

    return (
        <div className="staffServicesIndex">
            <div className="groupHeaderStaffServicesIndex">
                <h3> Work for user Services</h3>
                <button className="btn--quanlikhachsan quanlikhachsan__green__viewDrinkAndFood" > Drink And Food </button>
                <button className="btn--quanlikhachsan quanlikhachsan__green__viewMotoBike" > MotoBike </button>
                <button className="btn--quanlikhachsan quanlikhachsan__green__viewClean" > Clean </button>
                <button className="btn--quanlikhachsan quanlikhachsan__green__viewCheckOutDamaged" > CheckOutDamaged </button>
            </div>
            <div className="groupBodyStaffServicesIndex">
                {/* // Table */}
                <NavigationAppContext.Provider value={{ dataFetchIsServer, changeSelectStatusHandler, handlerButtonDetailViewServices }}>
                    <Staffserviceindextable />
                </NavigationAppContext.Provider>
            </div>

            <NavigationAppContext.Provider value={{ stateInforDialog, setstateInforDialog }}>
                {stateInforDialog.status && <DialogUpdateViewInfo />}
                {stateInforDialog.statusDamaged && <DialogUpdateDamaged />}
            </NavigationAppContext.Provider>
        </div>
    )
}
