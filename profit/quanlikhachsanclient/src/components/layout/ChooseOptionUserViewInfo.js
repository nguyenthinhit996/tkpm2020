import { withStyles } from '@material-ui/core/styles';
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import React, { useContext, useEffect, useState } from 'react'
import './ChooseOptionUserViewInfo.css'
import './Body.css'
import NavigationAppContext from '../../stores/NavigationAppContext'
import { Hidden, List, ListItem, ListItemText } from '@material-ui/core';
import { getInfoUser } from '../../core/auth';
import { useSnackbar } from 'notistack';
import { HandleGetError, HandleErrorSystem } from '../../core/handleDataFromDB'
import { useHistory } from 'react-router-dom'
import {OpenLoadding, OffLoadding} from '../../core/Utils'
import Appcontext from '../../AppContext';

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

export default function Chooseoptionuserviewinfo(pros) {


    const {dispatch} = useContext(Appcontext);

    const history = useHistory();

    const content = "Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.";

    const modalTitle = "View information of User";

     //-----------------toast start

    const { enqueueSnackbar } = useSnackbar();

    const handlerMessageToast = (mess, variant) => {
        // variant could be success, error, warning, info, or default
        enqueueSnackbar(mess, { variant });
    };

    const handlerMessageToastError = (mess) => {
        // variant could be success, error, warning, info, or default
        enqueueSnackbar(mess, {variant :'error'} );
    };

    //--------------------toast end

    const [stateValue, setstateValue] = useState({});
    useEffect( async () => {
    OpenLoadding(dispatch);
       let data = await getInfoUser();
       let messError = HandleGetError(data);
       if (messError.length !== 0) {
            OffLoadding(dispatch);
           handlerMessageToastError(messError);
           HandleErrorSystem(data, history);
       } else {
        setstateValue(data);
        OffLoadding(dispatch);
       }
    }, [])

    const { openViewInfor, setopenViewInfor } = useContext(NavigationAppContext);

    const handleClose = () => {
        setopenViewInfor(false);
    };

    return (
        <div>
            <Dialog className="chooseoptionuserviewinfo" onClose={handleClose} aria-labelledby="customized-dialog-title" open={openViewInfor}>
                <DialogTitle className="font--Quanlikhachsan" id="customized-dialog-title" onClose={handleClose}>
                    {modalTitle}
                </DialogTitle>
                <DialogContent dividers>
                    <List dense={false}>
                        {
                            <div>
                                <ListItem key={stateValue.nameStaff}>
                                    <ListItemText primary="Full Name:" />
                                    <ListItemText primary={stateValue.nameStaff} />
                                </ListItem>

                                <ListItem key={stateValue.roleOfStaff}>
                                    <ListItemText primary="Role :" />
                                    <ListItemText primary={stateValue.roleOfStaff} />
                                </ListItem>

                                <ListItem key={stateValue.dateStartWork}>
                                    <ListItemText primary="Start Work:" />
                                    <ListItemText primary={stateValue.dateStartWork} />
                                </ListItem>

                                <ListItem key={stateValue.salaryStaffByMonth}>
                                    <ListItemText primary="Salary Month:" />
                                    <ListItemText primary={stateValue.salaryStaffByMonth} />
                                </ListItem>

                                <ListItem key={stateValue.bonussalary}>
                                    <ListItemText primary="Bonus Month:" />
                                    <ListItemText primary={stateValue.bonussalary} />
                                </ListItem>
                            </div>
                        }
                    </List>
                </DialogContent>
            </Dialog>
        </div>
    );
}
