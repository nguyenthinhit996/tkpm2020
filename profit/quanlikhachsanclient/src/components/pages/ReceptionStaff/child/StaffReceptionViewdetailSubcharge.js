import React, { useContext } from 'react'
import NavigationAppContext from '../../../../stores/NavigationAppContext'

import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import { useHistory } from 'react-router-dom';
import {DialogContent , List, ListItem, ListItemText, withStyles } from '@material-ui/core';
import './StaffReceptionViewdetailSubcharge.css'

// handle error and set loading process
import { HandleGetError, HandleErrorSystem } from '../../../../core/handleDataFromDB'


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

export default function Staffreceptionviewdetailsubcharge({numberInRoom,rateSubCharge,maxRentNumberInRoom}) {

    const history = useHistory();

    const numberRoom = history.location.state.numberRoom;

    const modelTitle = "Room subCharge In Room "+ numberRoom;

    // const value = {
    //     numberInRoom: '5',
    //     rateSubCharge: '50.000',
    //     toTalCharge:'5 x 50.000'
    // }

    var total="";

    if(numberInRoom > maxRentNumberInRoom){
        total = numberInRoom - maxRentNumberInRoom + " x " + rateSubCharge;
    }else{
        total="Not subCharge "
    }

    const {openViewInforSubCharge, setopenViewInforSubCharge} = useContext(NavigationAppContext);

    const handleClose = () => {
        setopenViewInforSubCharge(false);
    };

    return (
        <div>
            <Dialog className="staffreceptionviewdetailsubcharge" onClose={handleClose} aria-labelledby="customized-dialog-title" open={openViewInforSubCharge}>
                <DialogTitle className="font--Quanlikhachsan" id="customized-dialog-title" onClose={handleClose}>
                    {modelTitle}
                </DialogTitle>
                <DialogContent dividers>
                    <List dense={false}>
                        {
                            <div>
                                <ListItem >
                                    <ListItemText primary="Max in room: " />
                                    <ListItemText primary={maxRentNumberInRoom}/>
                                </ListItem>
                                <ListItem >
                                    <ListItemText primary="Number In Room: " />
                                    <ListItemText primary={numberInRoom} />
                                </ListItem>

                                <ListItem >
                                    <ListItemText primary="Rate SubCharge: " />
                                    <ListItemText primary={rateSubCharge} />
                                </ListItem>

                                <ListItem >
                                    <ListItemText primary="Total Charge: " />
                                    <ListItemText primary={total} />
                                </ListItem>
                            </div>
                        }
                    </List>
                </DialogContent>
            </Dialog>
        </div>
    )
}
