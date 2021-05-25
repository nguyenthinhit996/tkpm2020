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
import './TableViewOrder.css'
import NavigationAppContext from '../../stores/NavigationAppContext'
import CurrencyFormat from 'react-currency-format';
import { DetailservicesUpdate } from '../../core/product';
import { useSnackbar } from 'notistack';


const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

export default function TableViewOrder(pros) {

    const { listRowData, setlistRowData } = useContext(NavigationAppContext);

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

    const changeInputAmountHandler = (nameProduct, event) => {
        console.log(nameProduct + " " + event.target.value);
        var foundIndex = listRowData.findIndex(x => x.production.nameproduct === nameProduct);
        var item = listRowData[foundIndex];
        item.detailservices.amount = event.target.value;
        listRowData[foundIndex] = {...item}
        setlistRowData([...listRowData]);
        console.log(listRowData);
        let dataSendServer = item.detailservices;
        console.log(" data send server: "+ dataSendServer);
        updateRowTable(dataSendServer);
    }

    const changeSelectStatusHandler = (nameProduct, event) => {
        console.log(event);
        console.log(nameProduct + " " + event.target.value);

        // if done not change status
        var foundIndex = listRowData.findIndex(x => x.production.nameproduct === nameProduct);
        var item = listRowData[foundIndex];
        item.detailservices.status=event.target.value;
        if(item.detailservices.status === "Cancel"){
            item.detailservices.amount=0;
        }
        listRowData[foundIndex] = { ...item}
        setlistRowData([...listRowData]);
        console.log(listRowData);
        let dataSendServer = item.detailservices;
        console.log(" data send server: "+ dataSendServer);
        updateRowTable(dataSendServer);
    }

    const updateRowTable = async (data) => {
        let booleanStatus = false;
        await DetailservicesUpdate(data).then(res => {
            booleanStatus = res;
        })
        if(booleanStatus){
            exportToastSuccess("Update success");
        }
    }

    var valueSumPrice = 0;

    return (
        <TableContainer component={Paper} className="tableViewOrder" >
            <Table className={classes.table} aria-label="simple table">
                <TableHead className="group--header-table-viewer">
                    <TableRow>
                        <TableCell align="justify">Name</TableCell>
                        <TableCell align="justify">Amount</TableCell>
                        <TableCell align="justify">Price(1)</TableCell>
                        <TableCell align="justify">Note</TableCell>
                        <TableCell align="justify">SumPrice</TableCell>
                        <TableCell align="justify">Status</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody className="group--body-table-viewer">
                    {listRowData.map((row) => (
                        <TableRow key={row.production.nameproduct} >
                            <TableCell align="justify" component="th" scope="row">
                                {row.production.nameproduct}
                            </TableCell>
                            <TableCell align="justify">
                                <input disabled={row.detailservices.status === "Done" || row.detailservices.status === "Cancel" ? true : false} defaultValue={row.detailservices.amount} onChange={(event) => { changeInputAmountHandler(row.production.nameproduct, event) }} className="group--body-table-viewer--inputAmount" type="number" name="quantity" min="1" ></input>
                            </TableCell>
                            <TableCell align="justify">
                                <CurrencyFormat value={row.production.productrates} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                            </TableCell>
                            <TableCell align="justify">
                                {row.production.extention}
                            </TableCell>
                            <TableCell align="justify">
                                <CurrencyFormat value={row.detailservices.sumaryservices = row.detailservices.amount * row.production.productrates} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                                <span style={{ visibility: 'hidden' }}>{valueSumPrice += row.detailservices.sumaryservices}</span>
                            </TableCell>
                            <TableCell align="justify">
                                <NativeSelect
                                    disabled={row.detailservices.status === "Done" || row.detailservices.status === "Cancel" ? true : false}
                                    className="select--status--service"
                                    defaultValue={row.detailservices.status}
                                    onChange={(event) => { changeSelectStatusHandler(row.production.nameproduct, event) }}
                                    inputProps={{
                                        name: 'status',
                                        id: 'age-native-helper',
                                    }}>
                                    <option disabled={row.detailservices.status === "Shipping" ? true : false} value={"Prepare"} >Prepare</option>
                                    <option value={"Shipping"} >Shipping</option>
                                    <option value={"Done"}>Done</option>
                                    <option disabled={row.detailservices.status === "Shipping" ? true : false} value={"Cancel"}>Cancel</option>
                                </NativeSelect>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
                <TableFooter className="group--footer-table-viewer">
                    <TableRow>
                        <TableCell align="left">Total: </TableCell>
                        <TableCell ></TableCell>
                        <TableCell ></TableCell>
                        <TableCell ></TableCell>
                        <TableCell align="justify">
                            <CurrencyFormat value={valueSumPrice} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                        </TableCell>
                        <TableCell >vnd</TableCell>
                    </TableRow>
                </TableFooter>
            </Table>
        </TableContainer>
    );
}