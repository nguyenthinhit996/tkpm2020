import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import React, { useContext, useEffect, useState } from 'react'
import './Chooseoptionuserchangepass.css'
import { red } from '@material-ui/core/colors';
import './Body.css'
import NavigationAppContext from '../../stores/NavigationAppContext'
import { Grid, List, ListItem, ListItemText } from '@material-ui/core';
import { useForm } from 'react-hook-form';
import { changePassWithServer } from '../../core/auth';
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

export default function Chooseoptionuserchangepass(props) {


    const modalTitle = "Change PassWord For User";
 

    const [changePass, setchangePass] = useState('');

    const { openChangePass, setopenChangePass } = useContext(NavigationAppContext);

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
    // toast enddddddddddddd

    const handleClose = () => {
        setopenChangePass(false);
    };

    const { register, handleSubmit, watch, errors } = useForm();

    // useEffect(() => {
    //     if(errors === null){
    //         const messageLogin = "";
    //         setchangePass(messageLogin);
    //     }
    // }, [errors])

    const onSubmit = async (data) => {

        if (data.inpPasswordNew !== data.inpPasswordAgain) {
            const messageLogin = "Password new not similar !";
            setchangePass(messageLogin);
        }else{
            let message = await changePassWithServer(data.inpPasswordOld,data.inpPasswordNew);
            if("Password old incorrect".includes(message)){
                setchangePass(message);
            }else{
                exportToastSuccess(message);
                setopenChangePass(false);
            }
        }  
    }

    // const onSubmit = async (data) => {
    //     console.log(data);
    //     let res = await login(data);
    //     if (res.authenticated !== undefined && res.authenticated) {
            
            
    //         if (res.role === STAFF_RECEPTION) {
    //             history.push('/staffreception');  
    //         }
    //         if (res.role === STAFF_SERVICE) {
    //             history.push('/staffservice');  
    //         }
    //         if (res.role === STAFF_MANAGER) {
    //             history.push('/staffmanager');  
    //         }

    //     } else {
    //         // alertMessage({ type: 'error', message: 'Tài khoản hoặc mật khẩu không đúng!' });
    //         const messageLogin = "User or password incorrect !";
    //         setloginState(messageLogin);
    //     }
    // }

    return (
        <div>
            <Dialog className="chooseoptionuserchangepass" aria-labelledby="customized-dialog-title" open={openChangePass}>
                <DialogTitle className="font--Quanlikhachsan" id="customized-dialog-title" onClose={handleClose}>
                    {modalTitle}
                </DialogTitle>
                <p className="container--removespace">Password new max 6 word</p>
                <DialogContent dividers>
                    <form className="chooseoptionuserchangepassForm" onSubmit={handleSubmit(onSubmit)} >

                        <h3 className={changePass !== "" ? "aler--error container--removespace" : "display--none"}> {changePass} </h3>
                       
                        <div className="form--mod" >
                            <label > Password Old:  </label>
                            <input name="inpPasswordOld"
                                ref={register({ required: true, maxLength: 10 })}
                            />
                            {errors.inpPasswordOld && < p className="container--removespace add--space-margin--left aler--error">* required </p>}
                        </div >

                        <div className="form--mod" >
                            <label > Password New:  </label>
                            <input name="inpPasswordNew"
                                ref={register({ required: true, maxLength: 10 })}
                            />
                            {errors.inpPasswordNew && < p className="container--removespace add--space-margin--left aler--error"> * required </p>}
                        </div>

                        <div className="form--mod" >
                            <label > Again Password New:  </label>
                            <input name="inpPasswordAgain"
                                ref={register({ required: true, maxLength: 10 })}
                            />
                            {errors.inpPasswordAgain && < p className="container--removespace add--space-margin--left aler--error"> * required </p>}
                        </div>
                        
                        <Grid className="btn--quanlikhachsan__changePass">
                            <button type="submit" className="btn--quanlikhachsan" > Change Pass </button>
                        </Grid>

                    </form >
                </DialogContent>
            </Dialog>
        </div>
    );
}
