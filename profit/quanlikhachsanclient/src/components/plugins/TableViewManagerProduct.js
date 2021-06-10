import React, { useContext, useEffect, useRef, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { CardMedia, NativeSelect, TableFooter, TextField } from '@material-ui/core';
import './TableViewManagerProduct.css'
import NavigationAppContext from '../../stores/NavigationAppContext'
import CurrencyFormat from 'react-currency-format';
import { DetailservicesUpdate, editProduct } from '../../core/product';
import { useSnackbar } from 'notistack';
import DialogEditProduct from './DialogEditProduct';
import { useHistory } from 'react-router-dom';


const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
    media: {
        width: 150,
        height: 100,
        paddingTop: '56.25%', // 16:9
    },
});

export default function TableViewManagerProduct(pros) {

    const { listRowData, setlistRowData } = useContext(NavigationAppContext);

    const classes = useStyles();

    const history = useHistory();

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
        let a = 'erro';
        setmessageToast({ message: mess, variant: a })
    }
    // toast  enddddddddddddddd

    // openEdit, setopenEdit
    const [openEdit, setopenEdit] = useState({
        status: false,
        data: {}
    })

    const handlerEditProduct = (row) => {
        console.log(row);
        setopenEdit({ ...openEdit, status: true, data: row });
    }

    const handlerRemoveProduct = async (row) => {

        row.status = "Off";
        console.log(row);
        let result = await editProduct(row);
        if (result) {
            exportToastSuccess("Remove  Product Success");
        } else {
            exportToastError("Not Update Product Success");
        }
        setTimeout(() => {
            history.go(0);
        }, 1500);
    }

    return (
        <TableContainer component={Paper} className="TableViewManagerProduct" >
            <Table className={classes.table} aria-label="simple table">
                <TableHead className="group--header-table-viewer">
                    <TableRow>
                        <TableCell align="justify">IdProduct</TableCell>
                        <TableCell align="justify">Nameproduct</TableCell>
                        <TableCell align="justify">Extention</TableCell>
                        <TableCell align="justify">Productrates</TableCell>
                        <TableCell align="justify">img</TableCell>
                        <TableCell align="justify">type</TableCell>
                        <TableCell align="justify">Edit</TableCell>
                        <TableCell align="justify">Remove</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody className="group--body-table-viewer">
                    {listRowData.map((row) => (
                        <TableRow key={Math.random()} >
                            <TableCell align="justify" component="th" scope="row">

                                {row.idproduction}
                            </TableCell>
                            <TableCell align="justify">
                                {row.nameproduct}
                            </TableCell>
                            <TableCell align="justify">
                                {row.extention}
                            </TableCell>
                            <TableCell align="justify">

                                <CurrencyFormat value={row.productrates} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                            </TableCell>
                            <TableCell align="justify">
                                <CardMedia
                                    className={classes.media}
                                    image={row.img} />
                            </TableCell>
                            <TableCell align="justify">
                                {row.type}
                            </TableCell>
                            <TableCell align="justify">
                                <button onClick={() => handlerEditProduct(row)} className="btn--quanlikhachsan btn--quanlikhachsan__green__Edit" > Edit </button>
                            </TableCell>

                            <TableCell align="justify">
                                <button onClick={() => handlerRemoveProduct(row)} className="btn--quanlikhachsan btn--quanlikhachsan__green__Remove" > Remove </button>
                            </TableCell>

                        </TableRow>
                    ))}
                </TableBody>
            </Table>
            <NavigationAppContext.Provider value={{ openEdit, setopenEdit }}>
                {openEdit.status && <DialogEditProduct />}
            </NavigationAppContext.Provider>
        </TableContainer>
    );
}