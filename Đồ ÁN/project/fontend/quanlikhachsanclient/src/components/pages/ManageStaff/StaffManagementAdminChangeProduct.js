import React, { useEffect, useState } from 'react'
import '../../../components/layout/Body.css'
import { Button, Container, InputLabel, makeStyles, NativeSelect, Paper, TextField, Typography } from '@material-ui/core'
import Navigation from '../../layout/Navigation'
import './StaffManagementAdminChangeProduct.css'
import DialogAddProduct from '../../componentChild/DialogAddProduct'
import NavigationAppContext from '../../../stores/NavigationAppContext'
import TableViewManagerProduct from '../../componentChild/TableViewManagerProduct'
import {getAllProduct} from '../../../core/product'

export default function StaffManagementAdminChangeProduct(props) {



    const [opeAddProduct, setopeAddProduct] = useState(false);

    const [listRowData, setlistRowData] = useState([]);

    useEffect(async () => {
        await getAllProduct().then(value => {
            setlistRowData(value);
        })
    }, [])

    const handlerOpenDialogAddProduct = () => {
        setopeAddProduct(true);
    }

    return (
        <Container>
            <Typography component="div" className="containerQuanliKhachSan StaffManagementAdminChangeProduct">
                <Navigation />
                <div className="StaffManagementAdminChangeProductHeader">
                    <button onClick={handlerOpenDialogAddProduct} className="btn--quanlikhachsan btn--quanlikhachsan__green__ManagementStaff" > Add Product </button>
                </div>
                <div className="StaffManagementAdminChangeProductBody">
                    <h2>Management Product</h2>
                   
                    <NavigationAppContext.Provider value={{ opeAddProduct, setopeAddProduct, listRowData, setlistRowData }}>
                        <TableViewManagerProduct/>
                        {opeAddProduct && <DialogAddProduct />}
                    </NavigationAppContext.Provider>
                </div>
            </Typography>
        </Container>
    )
}
