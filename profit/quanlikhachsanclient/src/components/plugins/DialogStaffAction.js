import React, { useContext, useEffect, useRef, useState } from 'react'
import { Dialog, Grid, IconButton, NativeSelect, Typography, withStyles } from '@material-ui/core'
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import CloseIcon from '@material-ui/icons/Close';
import './DialogStaffAction.css'
import { useForm } from 'react-hook-form'
import { useHistory } from 'react-router-dom'
import NavigationAppContext from '../../stores/NavigationAppContext'
import '../layout/Body.css'
import MuiDialogContent from '@material-ui/core/DialogContent';
import { STAFF_MANAGER_ADMIN } from '../../constants/ConstApp';
import { newStaff, editStaff, resetPass } from '../../core/staff'
import { useSnackbar } from 'notistack';

const styles = (theme) => ({
    root: {
        margin: 0,
        padding: theme.spacing(2),

    },
    closeButton: {
        position: 'absolute',
        right: theme.spacing(1),
        top: theme.spacing(1),
        color: '#ff0000'

    },
});

const DialogTitle = withStyles(styles)((props) => {
    const { children, classes, onClose, ...other } = props;
    return (
        <MuiDialogTitle disableTypography className={classes.root} {...other}>
            <Typography variant="h6">{children}</Typography>
            {onClose ? (
                <IconButton aria-label="close" className={classes.closeButton} onClick={onClose}>
                    <CloseIcon />
                </IconButton>
            ) : null}
        </MuiDialogTitle>
    );
});

const DialogContent = withStyles((theme) => ({
    root: {
        padding: theme.spacing(2),
    },
}))(MuiDialogContent);

export default function DialogStaffAction(props) {


    const { register, handleSubmit, watch, errors, reset } = useForm();


    const history = useHistory();

    const role = localStorage.quanlikhachsan_role;

    const refSelectNew = useRef("");
    const refSelectEdit = useRef("");

    // openDialogStaff, setopenDialogStaff , isAction , modalTitle ,data

    const { staffState, setstaffState } = useContext(NavigationAppContext);

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

    const onSubmitNewStaff = async (data) => {

        console.log(refSelectNew.current.firstChild.value);

        let dataresult = {
            idstaff: ""
            , username: data.username
            , pass: data.pass
            , role: refSelectNew.current.firstChild.value
            , datework: data.datework
            , salarymonth: data.salarymonth
            , bonussalary: data.bonussalary
        }
        let result = await newStaff(dataresult);

        if (result) {
            exportToastSuccess("Create Staff Success")
        } else {
            exportToastError("Not Create Staff Success")
        }

        console.log(JSON.stringify(data));
        reset();
        handleClose();
        setTimeout(() => {
            history.go(0);
        }, 1500);
    }

    const onSubmitEditStaff = async (data) => {
        console.log(refSelectEdit.current.firstChild.value);

        console.log(JSON.stringify(data));

        let dataresult = {
            idstaff: staffState.data.idstaff
            , username: data.username
            , pass: ""
            , status: staffState.data.status
            , role: refSelectEdit.current.firstChild.value
            , datework: data.datework
            , salarymonth: data.salarymonth
            , bonussalary: data.bonussalary
        }
        let result = await editStaff(dataresult);

        if (result) {
            exportToastSuccess("Edit Staff Success")
        } else {
            exportToastError("Not Edit Staff Success")
        }


        reset();
        handleClose();
        setTimeout(() => {
            history.go(0);
        }, 1500);
    }




    const handleClose = () => {
        setstaffState({ ...staffState, openDialogStaff: false });
        reset();
    }

    const resetPassHandler = async () => {
        console.log(" Reset pass " + staffState.data.idstaff);
        let result = await resetPass(staffState.data.idstaff);
        if (result) {
            exportToastSuccess("Reset Pass Staff Success")
        } else {
            exportToastError("Not Reset Pass Staff Success")
        }
    }

    return (
        <div>
            <Dialog className="DialogStaffAction" aria-labelledby="customized-dialog-title" open={staffState.openDialogStaff}>
                <DialogTitle className="font--Quanlikhachsan" id="customized-dialog-title" onClose={handleClose}>
                    {staffState.modalTitle}
                </DialogTitle>
                <DialogContent dividers>
                    {staffState.isAction === "New" &&
                        <form className="DialogStaffActionForm" onSubmit={handleSubmit(onSubmitNewStaff)} >
                            <div className="ChangRegulationRoomsingle">
                                <div className="form--mod--dialogstaff" >
                                    <label > User name:  </label>
                                    <input name="username"
                                        ref={register({ required: true, maxLength: 255 })}
                                    />
                                    {errors.username && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dialogstaff" >
                                    <label > Role:  </label>
                                    <NativeSelect
                                        ref={refSelectNew}
                                        className="select--status--service"
                                        defaultValue={"tieptan"}
                                        // onChange={(event) => { changeSelectStatusHandler(row.production.nameproduct, event) }}
                                        inputProps={{
                                            name: 'status',
                                            id: 'age-native-helper',
                                        }}>
                                        <option value={"tieptan"} >tieptan</option>
                                        <option value={"dichvu"} >dichvu</option>
                                        <option value={"quanli"}>quanli</option>
                                        <option disabled={role === STAFF_MANAGER_ADMIN ? false : true} value={"admin"}>admin</option>
                                    </NativeSelect>
                                </div>
                                <div className="form--mod--dialogstaff" >
                                    <label > Datework:  </label>
                                    <input name="datework" type="date"
                                        ref={register({ required: true })}
                                    />
                                    {errors.datework && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dialogstaff" >
                                    <label > salarymonth:  </label>
                                    <input name="salarymonth" type="number"
                                        ref={register({ required: true })}
                                    />
                                    {errors.salarymonth && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dialogstaff" >
                                    <label > bonussalary:  </label>
                                    <input name="bonussalary" type="number"
                                        ref={register({ required: true })}
                                    />
                                    {errors.bonussalary && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                            </div>
                            <div className="btn-saveupdate--group">
                                <Grid>
                                    <button type="button" onClick={handleClose} className="btn--quanlikhachsan btn--quanlikhachsan__cancel" > Cancel </button>
                                    <button type="submit" className="btn--quanlikhachsan btn--quanlikhachsan--saveAddproduct" > Save </button>
                                </Grid>
                            </div>
                        </form>
                    }

                    {staffState.isAction === "Edit" &&
                        <form className="DialogStaffActionForm" onSubmit={handleSubmit(onSubmitEditStaff)} >
                            <div className="ChangRegulationRoomsingle">
                                <div className="form--mod--dialogstaff" >
                                    <label > User name:  </label>
                                    <input name="username" defaultValue={staffState.data.username}
                                        ref={register({ required: true, maxLength: 255 })}
                                    />
                                    {errors.username && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dialogstaff" >
                                    <label > Role:  </label>
                                    <NativeSelect
                                        ref={refSelectEdit}
                                        className="select--status--service"
                                        defaultValue={staffState.data.role}
                                        // onChange={(event) => { changeSelectStatusHandler(row.production.nameproduct, event) }}
                                        inputProps={{
                                            name: 'status',
                                            id: 'age-native-helper',
                                        }}>
                                        <option value={"tieptan"} >tieptan</option>
                                        <option value={"dichvu"} >dichvu</option>
                                        <option value={"quanli"}>quanli</option>
                                        <option disabled={role === STAFF_MANAGER_ADMIN ? false : true} value={"admin"}>admin</option>
                                    </NativeSelect>
                                </div>
                                <div className="form--mod--dialogstaff" >
                                    <label > Datework:  </label>
                                    <input name="datework" defaultValue={staffState.data.datework} type="date"
                                        ref={register({ required: true })}
                                    />
                                    {errors.datework && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dialogstaff" >
                                    <label > salarymonth:  </label>
                                    <input name="salarymonth" type="number" defaultValue={staffState.data.salarymonth}
                                        ref={register({ required: true })}
                                    />
                                    {errors.salarymonth && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--dialogstaff" >
                                    <label > bonussalary:  </label>
                                    <input name="bonussalary" type="number" defaultValue={staffState.data.bonussalary}
                                        ref={register({ required: true })}
                                    />
                                    {errors.bonussalary && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                            </div>
                            <div className="btn-saveupdate--group">
                                <Grid>
                                    <button type="button" onClick={handleClose} className="btn--quanlikhachsan btn--quanlikhachsan__cancel" > Cancel </button>
                                    <button type="button" onClick={resetPassHandler} className="btn--quanlikhachsan btn--quanlikhachsan--ResetPass" > ResetPass </button>
                                    <button type="submit" className="btn--quanlikhachsan btn--quanlikhachsan--saveAddproduct" > Save </button>
                                </Grid>
                            </div>
                        </form>
                    }
                </DialogContent>
            </Dialog>
        </div>
    );
}
