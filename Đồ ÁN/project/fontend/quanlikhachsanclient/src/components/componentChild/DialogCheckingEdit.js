import React, { useContext, useEffect, useRef, useState } from 'react'
import { Dialog, Grid, IconButton, NativeSelect, Typography, withStyles } from '@material-ui/core'
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import CloseIcon from '@material-ui/icons/Close';
import './DialogCheckingEdit.css'
import { useForm } from 'react-hook-form'
import { useHistory } from 'react-router-dom'
import NavigationAppContext from '../../stores/NavigationAppContext'
import '../layout/Body.css'
import MuiDialogContent from '@material-ui/core/DialogContent';
import { updateDailyworking } from '../../core/workstaff'
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

export default function DialogCheckingEdit(props) {


    const { register, handleSubmit, watch, errors, reset } = useForm();


    const history = useHistory();
 
    const refNote = useRef();

    // openDialogStaff, setopenDialogStaff , isAction , modalTitle ,data

    const { staffState, setstaffState, handlerEditUpdate } = useContext(NavigationAppContext);

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


    const onSubmitEditStaff = async (data) => {

        handlerEditUpdate(data, refNote);
             
       reset();
       handleClose();
        // console.log(refNote.current.value);

        //  console.log(JSON.stringify(data));

        // let dataresult = {
        //     idtoday : staffState.data.idtoday,
        //     idstaffwork : staffState.data.idstaffwork,
        //     timestart : data.timeStart,
        //     timeend : data.endStart,
        //     note : refNote.current.value,
        //     idstaffmanagement : localStorage.onlineAcademy_userName,
        //     usernamestaff : staffState.data.usernamework,
        // }

        // console.log(dataresult);

        // let result = await updateDailyworking(dataresult);

        // if (result) {
        //     exportToastSuccess("Update Checking Staff Success")
        // } else {
        //     exportToastError("Not Edit Checking Staff Success")
        // }

      
        // reset();
        // handleClose();
        // setTimeout(() => {
        //     history.go(0);
        // }, 1500);
    }

    const handleClose = () => {
        setstaffState({ ...staffState, status: false });
        reset();
    }

    return (
        <div>
            <Dialog className="DialogCheckingEdit" aria-labelledby="customized-dialog-title" open={staffState.status}>
                <DialogTitle className="font--Quanlikhachsan" id="customized-dialog-title" onClose={handleClose}>
                    Edit Checking
                </DialogTitle>
                <DialogContent dividers>
                        <form className="DialogCheckingEditForm" onSubmit={handleSubmit(onSubmitEditStaff)} >
                            <div className="ChangRegulationRoomsingle">
                                <div className="form--mod--checking" >
                                    <label > TimeStart:  </label>
                                    <input name="timeStart" defaultValue={staffState.data.timestart} type="time"
                                        ref={register({ required: true, maxLength: 255 })}
                                    />
                                    {errors.timeStart && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                 
                                <div className="form--mod--checking" >
                                    <label > EndStart:  </label>
                                    <input name="endStart" defaultValue={staffState.data.timeend} type="time"
                                        ref={register({ required: true })}
                                    />
                                    {errors.endStart && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>}
                                </div>
                                <div className="form--mod--checking" >
                                    <label > Note:  </label>
                                    <input name="note" ref={refNote} defaultValue={staffState.data.note}/>
                                    {/* {errors.note && < p className="container--removespace add--space-margin--left aler--error"> * is required </p>} */}
                                </div>
                            </div>
                            <div className="btn-saveupdate--group">
                                <Grid>
                                    <button onClick={handleClose} className="btn--quanlikhachsan btn--quanlikhachsan__cancel" > Cancel </button>
                                    <button type="submit" className="btn--quanlikhachsan btn--quanlikhachsan--saveAddproduct" > Save </button>
                                </Grid>
                            </div>
                        </form>
                </DialogContent>
            </Dialog>
        </div>
    );
}
