import React, { useEffect, useState } from 'react'
import './StaffManagementManageStaff.css'
import { useForm } from 'react-hook-form'
import TableViewManagerStaff from '../../../plugins/TableViewManagerStaff'
import { useHistory } from 'react-router-dom'
import { editStaff, getAllStaff } from '../../../../core/staff'
import NavigationAppContext from '../../../../stores/NavigationAppContext'
import DialogStaffAction from '../../../plugins/DialogStaffAction'

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
            pathname: '/admin/staffmanagementdailyworking'
        });
    }

    const handlerSalaryButton = () => {
        history.push({
            pathname: '/admin/staffManagementSalary'
        });
    }

    const handlerAddStaffButton = () => {
        setstaffState({ ...staffState, openDialogStaff: true, isAction: "New", data: {}, modalTitle: "New Staff" });
    }

    const handlerEditStaffButton = (data) => {
        setstaffState({ ...staffState, openDialogStaff: true, isAction: "Edit", data: data, modalTitle: "Edit Staff" });
    }

    return (
        <div className="StaffManagementManageStaff">
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
        </div>
    )
}
