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
import './TableViewCheckingDaily.css'
import NavigationAppContext from '../../stores/NavigationAppContext'
import CurrencyFormat from 'react-currency-format';
import { DetailservicesUpdate } from '../../core/product';
import { useSnackbar } from 'notistack';


const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

export default function TableViewCheckingDaily(pros) {

    const { listRowData, setlistRowData,staffState , setstaffState } = useContext(NavigationAppContext);

    const classes = useStyles();

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

    // toast  enddddddddddddddd

    const handlerEditButton = (row) => {
        setstaffState({...staffState, status:true, data:row});
    }

    const updateRowTable = async (data) => {
        // let booleanStatus = false;
        // await DetailservicesUpdate(data).then(res => {
        //     booleanStatus = res;
        // })
        // if(booleanStatus){
        //     exportToastSuccess("Update success");
        // }
    }

    var valueSumPrice = 0;

    return (
        <TableContainer component={Paper} className="TableViewCheckingDaily" >
            <Table className={classes.table} aria-label="simple table">
                <TableHead className="group--header-table-viewer">
                    <TableRow>
                        <TableCell align="justify">idStaff</TableCell>
                        <TableCell align="justify">Name</TableCell>
                        <TableCell align="justify">TimeStart</TableCell>
                        <TableCell align="justify">EndStart</TableCell>
                        <TableCell align="justify">Note</TableCell>
                        <TableCell align="justify">Edit</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody className="group--body-table-viewer">
                    {listRowData.map((row) => (
                        <TableRow key={Math.random()} >
                            <TableCell align="justify" component="th" scope="row">
                                {row.idstaffwork}
                            </TableCell>
                            <TableCell align="justify">
                                {row.usernamework}
                            </TableCell>
                            <TableCell align="justify">
                                {row.timestart}
                            </TableCell>
                            <TableCell align="justify">
                                {row.timeend}
                            </TableCell>
                            <TableCell align="justify">
                                {row.note}
                            </TableCell>
                            <TableCell align="justify">
                            <button onClick={()=> handlerEditButton(row)} className="btn--quanlikhachsan btn--quanlikhachsan__green__Edit" > Edit </button>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}