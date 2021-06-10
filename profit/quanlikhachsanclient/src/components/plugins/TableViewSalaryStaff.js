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
import './TableViewSalaryStaff.css'
import NavigationAppContext from '../../stores/NavigationAppContext'
import CurrencyFormat from 'react-currency-format';
import { DetailservicesUpdate } from '../../core/product';
import { useSnackbar } from 'notistack';


const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

export default function TableViewSalaryStaff({ listRowData }) {

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

    const changeSelectStatusHandler = (nameProduct, event) => {
        //     console.log(event);
        //    console.log(nameProduct + " " + event.target.value);

        //     // if done not change status
        //     var foundIndex = listRowData.findIndex(x => x.production.nameproduct === nameProduct);
        //     var item = listRowData[foundIndex];
        //     item.detailservices.status=event.target.value;
        //     if(item.detailservices.status === "Cancel"){
        //         item.detailservices.amount=0;
        //     }
        //     listRowData[foundIndex] = { ...item}
        //     setlistRowData([...listRowData]);
        //     console.log(listRowData);
        //     let dataSendServer = item.detailservices;
        //     console.log(" data send server: "+ dataSendServer);
        //     updateRowTable(dataSendServer); 
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
        <TableContainer component={Paper} className="TableViewSalaryStaff" >
            <Table className={classes.table} aria-label="simple table">
                <TableHead className="group--header-table-viewer">
                    <TableRow>
                        <TableCell align="justify">idStaff</TableCell>
                        <TableCell align="justify">Name</TableCell>
                        <TableCell align="justify">Role</TableCell>
                        <TableCell align="justify">NumberWork</TableCell>
                        <TableCell align="justify">Salary Month</TableCell>
                        <TableCell align="justify">Bonus</TableCell>
                        <TableCell align="justify">Real Salary</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody className="group--body-table-viewer">
                    {listRowData.map((row) => (
                        <TableRow key={Math.random()} >
                            <TableCell align="justify" component="th" scope="row">
                                {row.idStaff}
                            </TableCell>
                            <TableCell align="justify">
                                {row.nameStaff}
                            </TableCell>
                            <TableCell align="justify">

                                {row.role}
                            </TableCell>
                            <TableCell align="justify">

                                {row.numberWork}
                            </TableCell>
                            <TableCell align="justify">
                                <CurrencyFormat value= {row.salaryMonth} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                            </TableCell>
                            <TableCell align="justify">
                                <CurrencyFormat value= {row.bonus} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                            </TableCell>
                            <TableCell align="justify">
                                <CurrencyFormat value= {row.realSalary} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>

            </Table>
        </TableContainer>
    );
}