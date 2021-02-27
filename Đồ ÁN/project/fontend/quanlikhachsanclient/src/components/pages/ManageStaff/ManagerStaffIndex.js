
import React, { useEffect, useState } from 'react'
import '../../../components/layout/Body.css'
import { Button, Container, InputLabel, makeStyles, NativeSelect, Paper, TextField, Typography } from '@material-ui/core'
import Navigation from '../../layout/Navigation'
import './ManagerStaffIndex.css'
import { useHistory } from 'react-router-dom'
import { STAFF_MANAGER, STAFF_MANAGER_ADMIN } from '../../../constants/ConstApp'
import { getTypeOfRoom } from '../../../core/admin'
import ChartRevenue from './testChart'
import { getRevenue } from '../../../core/room'


export default function Managerstaffindex(props) {

    const history = useHistory();

    const role = localStorage.onlineAcademy_role;

    console.log(role);

    const handlerManagementStaff = () => {
        history.push({
            pathname: '/StaffManagementManageStaff'
        });
    }

    const handlerManagementProduct = () => {
        history.push({
            pathname: '/StaffManagementAdminChangeProduct'
        });
    }

    const [dataGetServer, setdataGetServer] = useState([]);

    useEffect(() => {
        if (dataGetServer.length !== 0) {
            history.push({
                pathname: '/StaffManagementAdmin',
                state: {
                    dataGetServer: dataGetServer
                }
            });
        }

    }, [dataGetServer])

    const [data, setdata] = useState([])
    const [viewChart, setviewChart] = useState(false)

    useEffect(async () => {
        await getRevenue().then(value => {
            setdata(value);
            setviewChart(true);
        });
    }, [])

    const handlerAdmin = async () => {
        await getTypeOfRoom().then(value => {
            setdataGetServer([...value]);
        })
    }

    return (
        <Container>
            <Typography component="div" className="containerQuanliKhachSan managerstaffindex">
                <Navigation />
                <div className="ManagerstaffindexHeader">
                    <button onClick={handlerManagementStaff} className="btn--quanlikhachsan btn--quanlikhachsan__green__ManagementStaff" > Management Staff </button>
                    <button onClick={handlerManagementProduct} className="btn--quanlikhachsan btn--quanlikhachsan__green__ManagementProduct" > Management Product </button>
                    <button disabled={role === STAFF_MANAGER_ADMIN ? false : true} onClick={handlerAdmin} className="btn--quanlikhachsan btn--quanlikhachsan__green__Admin" > Admin </button>
                </div>
                <div className="ManagerstaffindexBody">
                   {viewChart && <ChartRevenue valueData={data}/>}  
                </div>
            </Typography>
        </Container>
    )
}
