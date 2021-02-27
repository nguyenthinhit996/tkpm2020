import React, { useEffect, useState } from 'react'
import '../../../components/layout/Body.css'
import { Button, Container, Grid, InputLabel, makeStyles, NativeSelect, Paper, TextField, Typography } from '@material-ui/core'
import Navigation from '../../layout/Navigation'
import './StaffManagementAdmin.css'
import TableViewCheckingDaily from '../../componentChild/TableViewCheckingDaily'
import { useForm } from 'react-hook-form'
import TableViewSalaryStaff from '../../componentChild/TableViewSalaryStaff'
import { useHistory } from 'react-router-dom'
import { getTypeOfRoom, updateTypeOfRoom } from '../../../core/admin'
import { useSnackbar } from 'notistack'

export default function StaffManagementAdmin(props) {

    const history = useHistory();

    const dataGetServer = history.location.state.dataGetServer;

    console.log("dafsaf"+JSON.stringify(dataGetServer));

    const { register, handleSubmit, watch, errors, reset } = useForm();

   
    // const [dataGetServer, setdataGetServer] = useState([
    //     { "nametypeofroom": "double", "roomrateshours": 0, "roomratesdates": 350000, "numberinroom": 5, "roomratescharge": 50000 }
    //     , { "nametypeofroom": "single", "roomrateshours": 0, "roomratesdates": 250000, "numberinroom": 2, "roomratescharge": 50000 }
    //     , { "nametypeofroom": "vip", "roomrateshours": 0, "roomratesdates": 1000000, "numberinroom": 100, "roomratescharge": 0 }]);

    // const [dataGetServer, setdataGetServer] = useState([]);
    
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

    const onSubmit = async (data) => {
        console.log(data);
        let result = await updateTypeOfRoom(data);
        if(result){
            exportToastSuccess("Update sucess");
        }else{
            exportToastWarning("Not update Error ! reload page");
        }
        
        setTimeout(() => {
            history.goBack();
        }, 1500);
    }

    const handlerCancelButton = () => {
        reset();
        history.goBack();
    }

    return (
        <Container>
            <Typography component="div" className="containerQuanliKhachSan StaffManagementAdmin">
                <Navigation />
                <div className="StaffManagementAdminContainer">
                    <div className="StaffManagementAdminHeader">
                        <h2 className="container--removespace">Change regulation of Room</h2>
                    </div>
                    <div className="StaffManagementAdminBody">
                        <form className="StaffManagementAdminBodyFrom" onSubmit={handleSubmit(onSubmit)} >
                            <div className="ChangRegulationRoomsingle">
                                <h3>Change Regulation Room {dataGetServer[1].nametypeofroom}</h3>
                                <div className="form--mod--dailyWorking" >
                                    <label > Room Rates Hours:  </label>
                                    <input name="singleRoomRatesHours" type="number" defaultValue={dataGetServer[1].roomrateshours}
                                        ref={register({ required: true })}
                                    />
                                    {errors.singleRoomRatesHours && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dailyWorking" >
                                    <label > Room Rates Day:  </label>
                                    <input name="singleRoomRatesDay" type="number" defaultValue={dataGetServer[1].roomratesdates}
                                        ref={register({ required: true })}
                                    />
                                    {errors.singleRoomRatesDay && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dailyWorking" >
                                    <label > Number In Room:  </label>
                                    <input name="singleNumberInRoom" type="number" defaultValue={dataGetServer[1].numberinroom}
                                        ref={register({ required: true })}
                                    />
                                    {errors.singleNumberInRoom && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dailyWorking" >
                                    <label > Sub charged:  </label>
                                    <input name="singleSubcharged" type="number" defaultValue={dataGetServer[1].roomratescharge}
                                        ref={register({ required: true })}
                                    />
                                    {errors.singleSubcharged && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                            </div>

                            <div className="ChangRegulationRoomdouble">
                                <h3>Change Regulation Room {dataGetServer[0].nametypeofroom}</h3>
                                <div className="form--mod--dailyWorking" >
                                    <label > Room Rates Hours:  </label>
                                    <input name="doubleRoomRatesHours" type="number" defaultValue={dataGetServer[0].roomrateshours}
                                        ref={register({ required: true })}
                                    />
                                    {errors.doubleRoomRatesHours && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dailyWorking" >
                                    <label > Room Rates Day:  </label>
                                    <input name="doubleRoomRatesDay" type="number" defaultValue={dataGetServer[0].roomratesdates}
                                        ref={register({ required: true })}
                                    />
                                    {errors.doubleRoomRatesDay && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dailyWorking" >
                                    <label > Number In Room:  </label>
                                    <input name="doubleNumberInRoom" type="number" defaultValue={dataGetServer[0].numberinroom}
                                        ref={register({ required: true })}
                                    />
                                    {errors.doubleNumberInRoom && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dailyWorking" >
                                    <label > Sub charged:  </label>
                                    <input name="doubleSubcharged" type="number" defaultValue={dataGetServer[0].roomratescharge}
                                        ref={register({ required: true })}
                                    />
                                    {errors.doubleSubcharged && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                            </div>

                            <div className="ChangRegulationRoomvip">
                                <h3>Change Regulation Room  {dataGetServer[2].nametypeofroom}</h3>
                                <div className="form--mod--dailyWorking" >
                                    <label > Room Rates Hours:  </label>
                                    <input name="vipRoomRatesHours" type="number" defaultValue={dataGetServer[2].roomrateshours}
                                        ref={register({ required: true })}
                                    />
                                    {errors.vipRoomRatesHours && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dailyWorking" >
                                    <label > Room Rates Day:  </label>
                                    <input name="vipRoomRatesDay" type="number" defaultValue={dataGetServer[2].roomratesdates}
                                        ref={register({ required: true })}
                                    />
                                    {errors.vipRoomRatesDay && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dailyWorking" >
                                    <label > Number In Room:  </label>
                                    <input name="vipNumberInRoom" type="number" defaultValue={dataGetServer[2].numberinroom}
                                        ref={register({ required: true })}
                                    />
                                    {errors.vipNumberInRoom && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dailyWorking" >
                                    <label > Sub charged:  </label>
                                    <input name="vipSubcharged" type="number" defaultValue={dataGetServer[2].roomratescharge}
                                        ref={register({ required: true })}
                                    />
                                    {errors.vipSubcharged && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                            </div>
                            <div className="btn-saveupdate--group">
                                <Grid>
                                    <button onClick={handlerCancelButton} className="btn--quanlikhachsan btn--quanlikhachsan__cancel" > Cancel </button>
                                </Grid>
                                <Grid>
                                    {/* {disabled={value.status === "Shipping" ? true : false}} */}
                                    <button type="submit" className="btn--quanlikhachsan btn--quanlikhachsan__UpdateRegulartion" > Update </button>
                                </Grid>
                            </div>
                        </form>
                    </div>
                </div>
            </Typography>
        </Container>
    )
}
