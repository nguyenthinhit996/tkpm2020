import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import React, { useContext, useEffect, useRef, useState } from 'react'
import './DialogAddProduct.css'
import '../layout/Body.css'
import NavigationAppContext from '../../stores/NavigationAppContext'
import { CardMedia, Grid, List, ListItem, ListItemText, makeStyles, NativeSelect, TextareaAutosize } from '@material-ui/core';
import { useForm } from 'react-hook-form';
import { useSnackbar } from 'notistack';
import { storage } from "../../firebase/firebase";
import {addNewProduct} from '../../core/product'
import { useHistory } from 'react-router-dom';


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

const useStyles = makeStyles({
    media: {
        width: 200,
        height: 50,
        paddingTop: '30.25%', // 16:9
    },
});

export default function DialogAddProduct(props) {


    const modalTitle = "Add new Product";

    const history = useHistory();

    const classes = useStyles();

    const { opeAddProduct, setopeAddProduct } = useContext(NavigationAppContext);

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
    // toast enddddddddddddd

    const { register, handleSubmit, watch, errors, reset } = useForm();

    const handleClose = () => {
        setopeAddProduct(false);
        reset();
    };




    // firebase start

    const [file, setFile] = useState(null);
    const [url, setURL] = useState("");

    function handleChangeFile(e) {
        setFile(e.target.files[0]);
    }

    function handleUpload(e) {
        console.log("update load image");
        e.preventDefault();
        const uploadTask = storage.ref(`/images/${file.name}`).put(file);
        uploadTask.on("state_changed", console.log, console.error, () => {
            storage
                .ref("images")
                .child(file.name)
                .getDownloadURL()
                .then((url) => {
                    setFile(null);
                    setURL(url);
                    console.log(url);
                });
        });
    }
    // firebase end

    const refSelectNew = useRef({});

    const onSubmit = async (data) => {

        if (url.length !== 0) {
            let dataSendToServer = {
                idproduction: "autogen",
                nameproduct: data.nameproduct,
                extention: data.extention,
                productrates: data.productrates,
                img: url,
                type: refSelectNew.current.firstChild.value,
                status: "On"
            }
            console.log(data);

            let result = await addNewProduct(dataSendToServer);

            if (result) {
                exportToastSuccess("Create Product Success")
            } else {
                exportToastError("Not Create Product Success")
            }

            reset();
            handleClose();
            setTimeout(() => {
                history.go(0);
            }, 1500);
        } else {
            exportToastError("Not Add Image Product");
        }

    }

    return (
        <div>
            <Dialog className="DialogAddProduct" aria-labelledby="customized-dialog-title" open={opeAddProduct}>
                <DialogTitle className="font--Quanlikhachsan" id="customized-dialog-title" onClose={handleClose}>
                    {modalTitle}
                </DialogTitle>
                <DialogContent dividers>
                    <form className="DialogAddProductForm" onSubmit={handleSubmit(onSubmit)} >
                        <div className="form--mod--addProduct" >
                            <label > Name product:  </label>
                            <input name="nameproduct"
                                ref={register({ required: true})}
                            />
                            {errors.nameproduct && < p className="container--removespace add--space-margin--left aler--error">* required </p>}
                        </div >

                        <div className="form--mod--addProduct" >
                            <label > extention:  </label>
                            {/* <input name="extention"
                                ref={register({ required: true})}
                            /> */}
                            <TextareaAutosize aria-label="minimum height" rowsMin={3} name="extention"
                                ref={register({ required: true})} />
                            {errors.extention && < p className="container--removespace add--space-margin--left aler--error"> * required </p>}
                        </div>

                        <div className="form--mod--addProduct" >
                            <label > productrates:  </label>
                            <input name="productrates" type="number"
                                ref={register({ required: true})}
                            />
                            {errors.productrates && < p className="container--removespace add--space-margin--left aler--error"> * required </p>}
                        </div>
                        <div className="form--mod--addProduct" >
                            <label > type:  </label>
                            <NativeSelect
                                ref={refSelectNew}
                                className="select--status--service"
                                defaultValue={"Drinkandfood"}
                                // onChange={(event) => { changeSelectStatusHandler(row.production.nameproduct, event) }}
                                inputProps={{
                                    name: 'status',
                                    id: 'age-native-helper',
                                }}>
                                <option value={"Drinkandfood"} >Drinkandfood</option>
                                <option value={"MotoBike"} >MotoBike</option>
                            </NativeSelect>
                        </div>

                        <Grid className="btn--quanlikhachsan__SaveAddProduct">
                            <button type="submit" className="btn--quanlikhachsan btn--quanlikhachsan--saveAddproduct" > Save </button>
                        </Grid>
                    </form >

                    <div className="form--mod--image-create-product" >
                        <form onSubmit={handleUpload}>
                            <input className="btn--quanlikhachsan" type="file" onChange={handleChangeFile} />
                            <button className="btn--quanlikhachsan btn-uploadImageAddProduct" disabled={!file}>upload to firebase</button>
                        </form>

                        <CardMedia
                            className={classes.media}
                            image={url} />
                    </div>
                </DialogContent>
            </Dialog>
        </div>
    );
}
