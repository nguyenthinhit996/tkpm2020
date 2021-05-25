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
import '../../layout/Body.css'
import NavigationAppContext  from '../../../stores/NavigationAppContext'



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

export default function DialogUpdateViewInfo(pros) {

    const { stateInforDialog, setstateInforDialog } = useContext(NavigationAppContext);

    const modalTitle = "View information of work";

    const handleClose = () => {
        setstateInforDialog({
            status: false,
            data: stateInforDialog.data
        });
    }

    return (
        <div>
            <Dialog className="chooseoptionuserchangepass" aria-labelledby="customized-dialog-title" open={stateInforDialog.status}>
                <DialogTitle className="font--Quanlikhachsan" id="customized-dialog-title" onClose={handleClose}>
                    {modalTitle}
                </DialogTitle>
                <DialogContent dividers>
                     <h3>{stateInforDialog.data}</h3>
                </DialogContent>
            </Dialog>
        </div>
    );
}
