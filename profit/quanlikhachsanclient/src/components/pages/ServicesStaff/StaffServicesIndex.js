import { Button, Container, InputLabel, makeStyles, NativeSelect, Paper, TextField, Typography } from '@material-ui/core'
import React, { useContext, useEffect, useRef, useState } from 'react'
import Navigation from '../../layout/Navigation'
import { SEVERITY_INFO, SEVERITY_WARNING } from '../../../constants/ConstApp'
import reactDom from 'react-dom'
import App from '../../../App'
import NavigationAppContext from '../../../stores/NavigationAppContext'
import { useSnackbar } from 'notistack';
import './StaffServicesIndex.css'
import '../../../components/layout/Body.css'
import Roomview from '../../plugins/RoomView'
import { useHistory } from 'react-router-dom'
import { detailAllRoom } from '../../../core/room'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { TableFooter } from '@material-ui/core';
import { getWorkAllStaff, updateStatusWorkAllStaff, detailservicesInforDrinkAndFood } from '../../../core/workstaff'
import DialogUpdateViewInfo from './DialogUpdateViewInfo'
import DialogUpdateDamaged from './DialogUpdateDamaged'



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

    const { listRowData, setlistRowData } = useContext(NavigationAppContext);

    const history = useHistory();

    const classes = useStyles();

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




    const [dataFetchIsServer, setdataFetchIsServer] = useState([]);
    useEffect(async () => {
        await getWorkAllStaff().then(data => {
            setdataFetchIsServer(data);
        })
    }, [])

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
        <Container>
            <Typography component="div" className="containerQuanliKhachSan staffServicesIndex">
                <Navigation />
                <div className="groupHeaderStaffServicesIndex">
                    <h3> Work for user Services</h3>
                    <button className="btn--quanlikhachsan quanlikhachsan__green__viewDrinkAndFood" > Drink And Food </button>
                    <button className="btn--quanlikhachsan quanlikhachsan__green__viewMotoBike" > MotoBike </button>
                    <button className="btn--quanlikhachsan quanlikhachsan__green__viewClean" > Clean </button>
                    <button className="btn--quanlikhachsan quanlikhachsan__green__viewCheckOutDamaged" > CheckOutDamaged </button>
                </div>
                <div className="groupBodyStaffServicesIndex">
                    <TableContainer component={Paper} className="tableViewWordOfStaff" >
                        <Table stickyHeader className={classes.table} aria-label="sticky table">
                            <TableHead className="group--header-table-viewer-workstaff">
                                <TableRow>
                                    <TableCell align="justify">idStaff</TableCell>
                                    <TableCell align="justify">NameStaff</TableCell>
                                    <TableCell align="justify">NumberRoom</TableCell>
                                    <TableCell align="justify">Services</TableCell>
                                    <TableCell align="justify">Status</TableCell>
                                    <TableCell align="justify">DetailView</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody className="group--body-table-viewer-workstaff">
                                {dataFetchIsServer.map((row) => (
                                    <TableRow key={Math.random()}
                                        className={(row.status === "Off" || row.status === "Done") ? "group--body-table-viewer-workstaff-rowDisable" : ""}
                                    >
                                        <TableCell align="justify" component="th" scope="row">
                                            {row.idstaff}
                                        </TableCell>
                                        <TableCell align="justify">
                                            {row.username}
                                        </TableCell>
                                        <TableCell align="justify">
                                            {row.numberroom}
                                        </TableCell>
                                        <TableCell align="justify" className={row.typeservices}>
                                            {row.typeservices}
                                        </TableCell>
                                        <TableCell align="justify">
                                            {row.status !== "Off" && row.status !== "Done" &&
                                                <NativeSelect
                                                    className="select--status--service"
                                                    defaultValue={row.status}
                                                    onChange={(event) => { changeSelectStatusHandler(row, event) }}
                                                    inputProps={{
                                                        name: 'status',
                                                        id: 'age-native-helper',
                                                    }}>
                                                    <option disabled={row.status === "ToDo" ? true : false} value={"Shipping"} >Shipping</option>
                                                    <option value={"ToDo"} >ToDo</option>
                                                    <option value={"Done"}>Done</option>
                                                </NativeSelect>
                                            }
                                            {(row.status === "Off" || row.status === "Done")  && 
                                                <p>Done</p>
                                            }
                                        </TableCell>
                                        <TableCell align="justify">
                                            {row.status === "ToDo" &&
                                                <button onClick={() => { handlerButtonDetailViewServices(row) }} className="btn--quanlikhachsan btn--quanlikhachsan__green__MoreService" > Detail </button>
                                            }
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </div>


                <NavigationAppContext.Provider value={{ stateInforDialog, setstateInforDialog }}>
                    {stateInforDialog.status && <DialogUpdateViewInfo />}
                    {stateInforDialog.statusDamaged && <DialogUpdateDamaged/>}
                </NavigationAppContext.Provider>

            </Typography>
        </Container>
    )
}
