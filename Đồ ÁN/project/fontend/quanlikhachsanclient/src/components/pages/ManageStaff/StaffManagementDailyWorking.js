import React, { useEffect, useRef, useState } from 'react'
import '../../../components/layout/Body.css'
import { Button, Container, InputLabel, makeStyles, NativeSelect, Paper, TextField, Typography } from '@material-ui/core'
import Navigation from '../../layout/Navigation'
import './StaffManagementDailyWorking.css'
import TableViewCheckingDaily from '../../componentChild/TableViewCheckingDaily'
import { useForm } from 'react-hook-form'
import { useHistory } from 'react-router-dom'
import { useSnackbar } from 'notistack'
import { getDailyWorking, updateDailyworking } from '../../../core/workstaff'
import NavigationAppContext from '../../../stores/NavigationAppContext'
import DialogCheckingEdit from '../../componentChild/DialogCheckingEdit'

export default function Staffmanagementdailyworking(props) {

    const history = useHistory();

    const refDayChecking = useRef();

    const [staffState, setstaffState] = useState({
        openDialogStaff: false
        , data: {}
    })


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

    const exportToastWarning = (mess) => {
        let a = 'Warning';
        setmessageToast({ message: mess, variant: a })
    }

    // toast  enddddddddddddddd

    const handlerManagementButton = () => {
        history.push({
            pathname: '/StaffManagementManageStaff'
        });
    }

    const handlerSalaryButton = () => {
        history.push({
            pathname: '/StaffManagementSalary'
        });
    }

    const [listRowData, setlistRowData] = useState([]);

    // useEffect(async () => {
    //     if (numberDay.length !== 0) {

    //     }
    // }, [])

    const handlerChangeDate = async (e) => {

        if (isValidDate(e.target.value)) {
            // check compare  with today
            var d1 = new Date();
            var d2 = new Date(e.target.value);
            if (d2.getFullYear() <= d1.getFullYear()) {
                if (d2.getMonth() <= d1.getMonth()) {
                    if (d2.getDate() <= d1.getDate()) {
                        console.log(e.target.value);

                        // get data
                        await getDailyWorking(e.target.value).then(value => {
                            setlistRowData(value);
                        })

                    } else {
                        exportToastWarning("Not Checking Date bigger Today");
                        console.log(refDayChecking) //yyyy-MM-dd
                        setTimeout(() => {
                            history.go(0);
                        }, 1500);

                    }
                } else {
                    exportToastWarning("Not Checking Date bigger Today");
                    console.log(refDayChecking) //yyyy-MM-dd
                    setTimeout(() => {
                        history.go(0);
                    }, 1500);

                }
            } else {
                exportToastWarning("Not Checking Date bigger Today");
                console.log(refDayChecking) //yyyy-MM-dd
                setTimeout(() => {
                    history.go(0);
                }, 1500);

            }
        }
    }

    const handlerEditUpdate = async (data, refNote) => {

        console.log(refNote.current.value);

        console.log(JSON.stringify(data));

        let dataresult = {
            idtoday: staffState.data.idtoday,
            idstaffwork: staffState.data.idstaffwork,
            timestart: data.timeStart,
            timeend: data.endStart,
            note: refNote.current.value,
            idstaffmanagement: localStorage.onlineAcademy_userName,
            usernamestaff: staffState.data.usernamework,
        }

        console.log(dataresult);

        let result = await updateDailyworking(dataresult);

        if (result) {
            exportToastSuccess("Update Checking Staff Success")
        } else {
            exportToastError("Not Edit Checking Staff Success")
        }

        // get data
        await getDailyWorking(refDayChecking.current.value).then(value => {
            setlistRowData(value);
        })
        // history.push({
        //     pathname: '/Staffmanagementdailyworking',
        //     state: {
        //         numberDay: refDayChecking.current.value,
        //     }
        // });
    }



    function isValidDate(dateString) {
        var regEx = /^\d{4}-\d{2}-\d{2}$/;
        if (!dateString.match(regEx)) return false;  // Invalid format
        var d = new Date(dateString);
        var dNum = d.getTime();
        if (!dNum && dNum !== 0) return false; // NaN value, Invalid date
        return d.toISOString().slice(0, 10) === dateString;
    }

    return (
        <Container>
            <Typography component="div" className="containerQuanliKhachSan staffmanagementdailyworking">
                <Navigation />
                <div className="StaffmanagementdailyworkingHeader">
                    <button onClick={handlerManagementButton} className="btn--quanlikhachsan btn--quanlikhachsan__green__ManagementStaff" > Management Staff </button>
                    <button onClick={handlerSalaryButton} className="btn--quanlikhachsan btn--quanlikhachsan__green__Salary" > Salary </button>
                </div>
                <div className="StaffmanagementdailyworkingBody">
                    <h2>Checking and Checkout Staff in work day</h2>
                    <div className="form--mod--dailyWorking" >
                        <label > Daily Working:  </label>
                        <input name="dailywork" type="date" onChange={handlerChangeDate} ref={refDayChecking}
                        />
                    </div>

                    <NavigationAppContext.Provider value={{ listRowData, setlistRowData, staffState, setstaffState, handlerEditUpdate }}>
                        <TableViewCheckingDaily />
                        {staffState.status && <DialogCheckingEdit />}
                    </NavigationAppContext.Provider>
                </div>
            </Typography>

        </Container>
    )
}
