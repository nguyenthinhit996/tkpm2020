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
import './ChooseOptionUserViewInfo.css'
import { red } from '@material-ui/core/colors';
import './Body.css'
import NavigationAppContext from '../../stores/NavigationAppContext'
import { List, ListItem, ListItemText } from '@material-ui/core';
import { axiosInstance } from '../../reducers/makeAPI';
import { getInfoUser } from '../../core/auth';

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


    const content = "Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.";

    const modalTitle = "View information of User";

    // var value = {
    //     idstaff: "222",
    //     username: "default",
    //     role: "tieptan",
    //     datework: "2020-12-12",
    //     salarymonth: "123456",
    //     bonussalary: "0"
    // };

    const [stateValue, setstateValue] = useState({});
    useEffect( async () => {
       await getInfoUser().then(res => {
        setstateValue(res);
       }) 
    }, [])

    const { openViewInfor, setopenViewInfor } = useContext(NavigationAppContext);

    const handleClose = () => {
        setopenViewInfor(false);
    };

    const getInforUser = async () => {
        try {
            let idStaff = localStorage.onlineAcademy_userName;
            console.log("idStaff" + idStaff);
            let { data } = await axiosInstance.get('/staff', {
                params: {
                    id: idStaff
                }
            });
            console.log("sdfadsfdasfdasfdasfdasfas" + data);
            return data;

        } catch (error) {
            console.log(error.response.data);
            return error.response.data;
        }
    }

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
                                <ListItem key={stateValue.username}>
                                    <ListItemText primary="Full Name:" />
                                    <ListItemText primary={stateValue.username} />
                                </ListItem>

                                <ListItem key={stateValue.role}>
                                    <ListItemText primary="Role :" />
                                    <ListItemText primary={stateValue.role} />
                                </ListItem>

                                <ListItem key={stateValue.datework}>
                                    <ListItemText primary="Start Work:" />
                                    <ListItemText primary={stateValue.datework} />
                                </ListItem>

                                <ListItem key={stateValue.SalaryMonth}>
                                    <ListItemText primary="Salary Month:" />
                                    <ListItemText primary={stateValue.salarymonth} />
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
