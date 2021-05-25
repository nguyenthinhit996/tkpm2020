import React, { useState } from 'react'
import '../../../components/layout/Body.css'
import { Button, Container, InputLabel, makeStyles, NativeSelect, Paper, TextField, Typography } from '@material-ui/core'
import Navigation from '../../layout/Navigation'
import './StaffManagementSalary.css'
import TableViewCheckingDaily from '../../componentChild/TableViewCheckingDaily'
import { useForm } from 'react-hook-form'
import TableViewSalaryStaff from '../../componentChild/TableViewSalaryStaff'
import { useHistory } from 'react-router-dom'
import { getSalary } from '../../../core/workstaff'

export default function StaffManagementSalary(props) {

    const history = useHistory();

    const handlerManagementButton = () => {
        history.push({
            pathname: '/StaffManagementManageStaff'
        });
    }

    const handlerDailyWorkingButton = () => {
        history.push({
            pathname: '/Staffmanagementdailyworking'
        });
    }

    const { register, handleSubmit, watch, errors } = useForm();

    const [listData, setlistData] = useState([])

    const onSubmit = async (data) => {
        console.log(data);
        await getSalary(data.salarymon).then(res => {
            setlistData(res);
        })
    }

    return (
        <Container>
            <Typography component="div" className="containerQuanliKhachSan StaffManagementSalary">
                <Navigation />
                <div className="StaffManagementSalaryHeader">
                    <button onClick={handlerManagementButton}  className="btn--quanlikhachsan btn--quanlikhachsan__green__ManagementStaff" > Management Staff </button>
                    <button onClick={handlerDailyWorkingButton} className="btn--quanlikhachsan btn--quanlikhachsan__green__Salary" > Daily Working </button>
                </div>
                <div className="StaffManagementSalaryBody">

                    <form className="StaffManagementSalaryBodyFrom"  >
                        <h2>Checking and Calculator Salary of Staff </h2>
                        <div className="form--mod--dailyWorking" >
                            <label > Salary Month:  </label>
                            <input name="salarymon" type="month" onChange={handleSubmit(onSubmit)}
                                ref={register({ required: true, maxLength: 10 })}
                            />
                            {errors.salarymon && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                        </div>
                    </form>
                    <TableViewSalaryStaff listRowData={listData}/>
                </div>
            </Typography>
        </Container>
    )
}
