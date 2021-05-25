import React, { useContext } from 'react'
import NavigationAppContext from '../../../stores/NavigationAppContext'

import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import { useHistory } from 'react-router-dom';
import {DialogContent , List, ListItem, ListItemText, withStyles } from '@material-ui/core';
import './StaffReceptionViewdetailSubcharge.css'

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

export default function StaffReceptionViewdetailDamaged({listDamaged,roomDamaged}) {

    const history = useHistory();

    const numberRoom = history.location.state.numberRoom;

    const modelTitle = "Room Damaged In "+ numberRoom;

    const value = {
        listDamged: listDamaged,
        toTalCharge:roomDamaged
    }

    const {openViewDamged, setopenViewDamged} = useContext(NavigationAppContext);

    const handleClose = () => {
        setopenViewDamged(false);
    };

    return (
        <div>
            <Dialog className="staffreceptionviewdetailsubcharge" onClose={handleClose} aria-labelledby="customized-dialog-title" open={openViewDamged}>
                <DialogTitle className="font--Quanlikhachsan" id="customized-dialog-title" onClose={handleClose}>
                    {modelTitle}
                </DialogTitle>
                <DialogContent dividers>
                    <List dense={false}>
                        {
                            <div>
                                <ListItem key={value.listDamged}>
                                    <ListItemText primary="List Damaged: " />
                                    <ListItemText primary={value.listDamged} />
                                </ListItem>

                                <ListItem key={value.toTalCharge}>
                                    <ListItemText primary="Total: " />
                                    <ListItemText primary={value.toTalCharge} />
                                </ListItem>
                            </div>
                        }
                    </List>
                </DialogContent>
            </Dialog>
        </div>
    )
}
