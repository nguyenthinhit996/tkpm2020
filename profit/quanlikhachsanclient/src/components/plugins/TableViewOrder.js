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

// handle error and set loading process
import { HandleGetError, HandleErrorSystem } from '../../core/handleDataFromDB'
import { OpenLoadding, OffLoadding } from '../../core/Utils'
import { useHistory } from 'react-router-dom';
import Appcontext from '../../AppContext';


const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

export default function TableViewOrder(pros) {

    const { listRowData } = useContext(NavigationAppContext);

    const classes = useStyles();

    const history = useHistory();

    const { dispatch } = useContext(Appcontext);

    const [stateListData, setListData] = useState([]);

    useEffect(() => {
        setListData([...listRowData])
    }, [listRowData])

    const inputAmount = useRef();

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

    const changeInputAmountHandler = async (nameProduct, event) => {
        let value = parseInt(event.target.value);
        if (event.target.value === "" || value <= 0) {
            return;
        }
        console.log(nameProduct + " " + event.target.value);
        var foundIndex = stateListData.findIndex(x => x.productDetail.nameProduct === nameProduct);
        var item = stateListData[foundIndex];
        const amountAfterChange = item.amount;
        item.amount = event.target.value;

        let dataSendServer = item;
        console.log(" data send server: " + dataSendServer);
        let status = await updateRowTable(dataSendServer);
        if (!status) {
            item.amount = amountAfterChange
            event.target.value = amountAfterChange;
        }
        stateListData[foundIndex] = { ...item }
        setListData([...stateListData]);
        console.log(stateListData);
    }

    const changeSelectStatusHandler = async (nameProduct, event) => {
        console.log(event);
        console.log(nameProduct + " " + event.target.value);

        // if done not change status
        var foundIndex = stateListData.findIndex(x => x.productDetail.nameProduct === nameProduct);
        var item = stateListData[foundIndex];
        const statusAfterChange = event.target.value;
        const amountAfterChange = item.amount;
        item.status = event.target.value;
        if (item.status === "Cancel") {
            item.amount = 0;
        }
        let dataSendServer = item;
        console.log(" data send server: " + dataSendServer);
        let status = await updateRowTable(dataSendServer);
        if (!status) {
            item.amount = amountAfterChange
            event.target.value = statusAfterChange;
        }
        stateListData[foundIndex] = { ...item }
        setListData([...stateListData]);
        console.log(stateListData);
    }

    const updateRowTable = async (data) => {
        OpenLoadding(dispatch);
        let dataResult = await DetailservicesUpdate(data);
        let messError = HandleGetError(dataResult);
        if (messError.length !== 0) {
            OffLoadding(dispatch);
            handlerMessageToast(messError, "error");
            HandleErrorSystem(dataResult, history);
            return false;
        } else {
            OffLoadding(dispatch);
            if (dataResult) {
                exportToastSuccess("Update success");
                return true;
            }
            exportToastError("Not Update occur error");
            return false;

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
                    {stateListData.map((row) => (
                        <TableRow key={row.productDetail.nameProduct} >
                            <TableCell align="justify" component="th" scope="row">
                                {row.productDetail.nameProduct}
                            </TableCell>
                            <TableCell align="justify">
                                <input ref={inputAmount} disabled={row.status === "Done" || row.status === "Cancel" ? true : false} defaultValue={row.amount} onChange={(event) => { changeInputAmountHandler(row.productDetail.nameProduct, event) }} className="group--body-table-viewer--inputAmount" type="number" name="quantity" min="1" ></input>
                            </TableCell>
                            <TableCell align="justify">
                                <CurrencyFormat value={row.productDetail.productRate} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                            </TableCell>
                            <TableCell align="justify">
                                {row.productDetail.extention}
                            </TableCell>
                            <TableCell align="justify">
                                <CurrencyFormat value={row.bigdesumaryMoneySerives = row.amount * row.productDetail.productRate} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                                <span style={{ visibility: 'hidden' }}>{valueSumPrice += row.bigdesumaryMoneySerives}</span>
                            </TableCell>
                            <TableCell align="justify">
                                <NativeSelect
                                    disabled={row.status === "Done" || row.status === "Cancel" ? true : false}
                                    className="select--status--service"
                                    defaultValue={row.status}
                                    onChange={(event) => { changeSelectStatusHandler(row.productDetail.nameProduct, event) }}
                                    inputProps={{
                                        name: 'status',
                                        id: 'age-native-helper',
                                    }}>
                                    <option disabled={row.status === "Shipping" ? true : false} value={"Prepare"} >Prepare</option>
                                    <option value={"Shipping"} >Shipping</option>
                                    <option value={"Done"}>Done</option>
                                    <option disabled={row.status === "Shipping" ? true : false} value={"Cancel"}>Cancel</option>
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