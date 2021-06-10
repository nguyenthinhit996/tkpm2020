import { withStyles } from '@material-ui/core/styles';
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import React, { useContext, useEffect, useState, } from 'react'
import { Grid } from '@material-ui/core';
import NavigationAppContext from '../../../../stores/NavigationAppContext'
import { useForm } from 'react-hook-form';
import './DialogUpdateDamaged.css'
import { useSnackbar } from 'notistack';
import { updateDamagedListOfRoom } from '../../../../core/workstaff';

// handle error and set loading process
import { HandleGetError, HandleErrorSystem } from '../../../../core/handleDataFromDB'
import { OpenLoadding, OffLoadding } from '../../../../core/Utils'
import Appcontext from '../../../../AppContext';
import { useHistory } from 'react-router-dom';


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

export default function DialogUpdateDamaged(pros) {

    const { dispatch } = useContext(Appcontext);

    const { history } = useHistory();

    const { stateInforDialog, setstateInforDialog } = useContext(NavigationAppContext);

    const modalTitle = "Update Damged of room " + stateInforDialog.numberroom;

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

    const handleClose = () => {
        setstateInforDialog({ ...stateInforDialog, statusDamaged: false });
    }

    const { register, handleSubmit, watch, errors } = useForm();

    const onSubmit = async (data) => {
        //update idDamaged

        var varData = {
            idCheckoutRoomDamaged: stateInforDialog.idDamaged,
            ticketCheckoutObject: null,
            idTicketBooking: null,
            listProductDamaded: data.listDamged,
            staffCheckOutRoomDamaged: null,
            sumaryIndemnify: data.money,
            status: ""
        }
        OpenLoadding(dispatch);
        let result = await updateDamagedListOfRoom(varData);
        let messError = HandleGetError(result);
        if (messError.length !== 0) {
            OffLoadding(dispatch);
            handlerMessageToast(messError, "error");
            HandleErrorSystem(result, history);
        } else {
            OffLoadding(dispatch);
            if (result) {
                exportToastSuccess("Updated List Damaged");
            } else {
                exportToastError("Not updated List Damaged");
            }
        }

        console.log("Update ------------- DialogUpdateDamaged ");
        setstateInforDialog({ ...stateInforDialog, statusDamaged: false });
    }

    return (
        <div>
            <Dialog className="dialogUpdateDamaged" aria-labelledby="customized-dialog-title" open={stateInforDialog.statusDamaged}>
                <DialogTitle className="font--Quanlikhachsan" id="customized-dialog-title" onClose={handleClose}>
                    {modalTitle}
                </DialogTitle>
                <DialogContent dividers>
                    <form className="dialogUpdateDamagedForm" onSubmit={handleSubmit(onSubmit)} >

                        <div className="form--mod" >
                            <label > List Damaged:  </label>
                            <input name="listDamged"
                                ref={register({ required: true, maxLength: 500 })}
                            />
                            {errors.listDamged && < p className="container--removespace add--space-margin--left aler--error">* required </p>}
                        </div >

                        <div className="form--mod" >
                            <label > Money:  </label>
                            <input name="money"
                                type="number"
                                ref={register({ required: true, maxLength: 10 })}
                            />
                            {errors.money && < p className="container--removespace add--space-margin--left aler--error"> * required </p>}
                        </div>

                        <Grid className="btn--quanlikhachsan__changePass">
                            <button type="submit" className="btn--quanlikhachsan" > Update </button>
                        </Grid>

                    </form >
                </DialogContent>
            </Dialog>
        </div>
    );
}
