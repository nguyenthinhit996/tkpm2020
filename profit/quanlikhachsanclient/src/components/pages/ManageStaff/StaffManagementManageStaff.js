import React, { useEffect, useState } from 'react'
import '../../../components/layout/Body.css'
import { Button, Container, InputLabel, makeStyles, NativeSelect, Paper, TextField, Typography } from '@material-ui/core'
import Navigation from '../../layout/Navigation'
import './StaffManagementManageStaff.css'
import TableViewCheckingDaily from '../../componentChild/TableViewCheckingDaily'
import { useForm } from 'react-hook-form'
import TableViewManagerStaff from '../../componentChild/TableViewManagerStaff'
import { useHistory } from 'react-router-dom'
import { editStaff, getAllStaff } from '../../../core/staff'
import NavigationAppContext from '../../../stores/NavigationAppContext'
import DialogStaffAction from '../../componentChild/DialogStaffAction'

export default function StaffManagementManageStaff(props) {

    const history = useHistory();

    const [listRowData, setlistRowData] = useState([])

    const [staffState, setstaffState] = useState({
        openDialogStaff: false
        , isAction: "New"
        , modalTitle: "New Staff"
        , data: {}
    })

    useEffect(async () => {
        await getAllStaff().then(value => {
            setlistRowData(value);
        })
    }, [])

     

    const handlerDailyWorkingButton = () => {
        history.push({
            pathname: '/Staffmanagementdailyworking'
        });
    }

    const handlerSalaryButton = () => {
        history.push({
            pathname: '/StaffManagementSalary'
        });
    }

    const handlerAddStaffButton = () => {
        setstaffState({ ...staffState, openDialogStaff: true, isAction: "New",data:{}, modalTitle: "New Staff" });
    }

    const handlerEditStaffButton = (data) => {
        setstaffState({ ...staffState, openDialogStaff: true, isAction: "Edit", data: data, modalTitle: "Edit Staff" });
    }


    return (
        <Container>
            <Typography component="div" className="containerQuanliKhachSan StaffManagementManageStaff">
                <Navigation />
                <div className="StaffManagementManageStaffHeader">
                    <button onClick={handlerAddStaffButton} className="btn--quanlikhachsan btn--quanlikhachsan__green__ManagementStaff" > Add Staff </button>
                    <button onClick={handlerDailyWorkingButton} className="btn--quanlikhachsan btn--quanlikhachsan__green__Salary" > Daily Working </button>
                    <button onClick={handlerSalaryButton} className="btn--quanlikhachsan btn--quanlikhachsan__green__Salary" > Salary </button>
                </div>
                <div className="StaffManagementManageStaffBody">
                <h2>View ManageMent Staff</h2>
                    <NavigationAppContext.Provider value={{ listRowData, setlistRowData, staffState, setstaffState, handlerEditStaffButton }}>
                        <TableViewManagerStaff />
                        <DialogStaffAction />
                    </NavigationAppContext.Provider>
                </div>
            </Typography>
        </Container>
    )
}
