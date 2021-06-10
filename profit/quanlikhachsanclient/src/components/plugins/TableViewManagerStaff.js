import React, { useContext, useEffect, useRef, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { NativeSelect, TableFooter, TextField } from '@material-ui/core';
import './TableViewManagerStaff.css'
import NavigationAppContext from '../../stores/NavigationAppContext'
import CurrencyFormat from 'react-currency-format';
import { DetailservicesUpdate } from '../../core/product';
import { useSnackbar } from 'notistack';
import { editStaff } from '../../core/staff';
import { useHistory } from 'react-router-dom';
import { STAFF_MANAGER_ADMIN } from '../../constants/ConstApp';


const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

export default function TableViewManagerStaff(pros) {

    const { listRowData, setlistRowData, handlerEditStaffButton } = useContext(NavigationAppContext);

    const classes = useStyles();

    const history = useHistory();

    const refInputAmount = useRef();

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

    const handlerEditStaff = (row) => {
        console.log(row);
        handlerEditStaffButton(row);
    }

    const handlerRemoveStaff = async (row) => {
        row.status = "Off";
        let result = await editStaff(row);
        if (result) {
            var id = row.idstaff;
            let mess = "Remove Staff (" + id + ") Success";
            exportToastSuccess(mess);
        } else {
            exportToastError("Not Detele Staff Success")
        }
        setTimeout(() => {
            history.go(0);
        }, 1500);
    }

    return (
        <TableContainer component={Paper} className="TableViewManagerStaff" >
            <Table className={classes.table} aria-label="simple table">
                <TableHead className="group--header-table-viewer">
                    <TableRow>
                        <TableCell align="justify">IdStaff</TableCell>
                        <TableCell align="justify">Name</TableCell>
                        <TableCell align="justify">Role</TableCell>
                        <TableCell align="justify">DateStart</TableCell>
                        <TableCell align="justify">SalaryMonth</TableCell>
                        <TableCell align="justify">BonusMonth</TableCell>
                        <TableCell align="justify">Action</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody className="group--body-table-viewer">
                    {listRowData.map((row) => (
                        <TableRow key={Math.random()} >
                            <TableCell align="justify" component="th" scope="row">
                                {row.idstaff}
                            </TableCell>
                            <TableCell align="justify">
                                {row.username}
                            </TableCell>
                            <TableCell align="justify">
                                {row.role}
                            </TableCell>
                            <TableCell align="justify">
                                {row.datework}
                            </TableCell>
                            <TableCell align="justify">
                                <CurrencyFormat value={row.salarymonth} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                            </TableCell>
                            <TableCell align="justify">
                                <CurrencyFormat value={row.bonussalary} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                            </TableCell>
                            {
                                // admin view len het ngoai tru no
                                localStorage.quanlikhachsan_role === STAFF_MANAGER_ADMIN
                                &&
                                <div>
                                    <TableCell align="justify">
                                        <button disabled={(row.idstaff === localStorage.quanlikhachsan_iduser) ? true : false} onClick={() => handlerEditStaff(row)} className="btn--quanlikhachsan btn--quanlikhachsan__green__Edit" > Edit </button>
                                    </TableCell>

                                    <TableCell align="justify">
                                        <button disabled={(row.idstaff === localStorage.quanlikhachsan_iduser) ? true : false} className="btn--quanlikhachsan btn--quanlikhachsan__green__Remove" > Remove </button>
                                    </TableCell>
                                </div>
                            }
                            {
                                // ko la admin 
                                // view le het ngoai tru no va admin
                                localStorage.quanlikhachsan_role !== STAFF_MANAGER_ADMIN &&
                                <div>
                                    <TableCell align="justify">
                                        <button disabled={(row.idstaff === localStorage.quanlikhachsan_iduser || row.role === STAFF_MANAGER_ADMIN) ? true : false} onClick={() => handlerEditStaff(row)} className="btn--quanlikhachsan btn--quanlikhachsan__green__Edit" > Edit </button>
                                    </TableCell>
                                    <TableCell align="justify">
                                        <button disabled={(row.idstaff === localStorage.quanlikhachsan_iduser || row.role === STAFF_MANAGER_ADMIN) ? true : false} className="btn--quanlikhachsan btn--quanlikhachsan__green__Remove" > Remove </button>
                                    </TableCell>
                                </div>
                            }
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}