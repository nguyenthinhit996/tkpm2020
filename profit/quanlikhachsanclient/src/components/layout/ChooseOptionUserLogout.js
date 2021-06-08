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
import './ChooseOptionUserLogout.css'
import { red } from '@material-ui/core/colors';
import './Body.css'
import NavigationAppContext from '../../stores/NavigationAppContext'
import { Grid, List, ListItem, ListItemText } from '@material-ui/core';
import { useForm } from 'react-hook-form';
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

export default function Chooseoptionuserlogout(props) {

    const modalTitle = "Do you logout ?";

    const { openLogout, setopenLogout } = useContext(NavigationAppContext);

    const history = useHistory();

    const handleClose = () => {
        setopenLogout(false);
    };

    const btnLogoutHandler = () => {
        //alert("Logouted");
        // localStorage.onlineAcademy_accessToken = data.accessToken;
        // localStorage.onlineAcademy_refreshToken = data.refreshToken;
        // localStorage.onlineAcademy_authenticated = data.authenticated;
        // localStorage.onlineAcademy_role = data.role;
        // localStorage.onlineAcademy_userName = data.username;
        localStorage.clear();
        console.log("localStorage.onlineAcademy_userName "+ localStorage.quanlikhachsan_iduser);
        history.push("/");
    }

    return (
        <div>
            <Dialog className="chooseoptionuserlogout" aria-labelledby="customized-dialog-title" open={openLogout}>
                <DialogTitle className="font--Quanlikhachsan" id="customized-dialog-title" onClose={handleClose}>
                    {modalTitle}
                </DialogTitle>
                <DialogContent dividers>
                    <div className="chooseoptionuserlogout--btn">
                        <Grid>
                            <button onClick={handleClose} className="btn--quanlikhachsan btn--quanlikhachsan__cancel" > Cancel </button>
                        </Grid>
                        <Grid>
                            <button onClick={btnLogoutHandler} className="btn--quanlikhachsan btn--quanlikhachsan__logout" > OK </button>
                        </Grid>

                    </div>
                </DialogContent>
            </Dialog>
        </div>
    )
}
