import { Container, Grid, makeStyles, Paper, Typography } from '@material-ui/core'
import { useForm } from "react-hook-form";
import React, { useEffect, useState } from 'react'
import '../layout/Body.css'
import './Login.css'
import { useHistory, useLocation } from 'react-router-dom';
import { login } from '../../core/auth';
import { STAFF_RECEPTION, STAFF_SERVICE ,STAFF_MANAGER, STAFF_MANAGER_ADMIN} from '../../constants/ConstApp';
import { useSnackbar } from 'notistack';


const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
    },
}));

export default function Login(props) {

    const [loginState, setloginState] = useState('');

    const classes = useStyles();

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

     const exportToastInfo = (mess) => {
        let a = 'info';
        setmessageToast({ message: mess, variant: a })
    }
 
     const exportToastWarning = (mess) => {
         let a = 'Warning';
         setmessageToast({ message: mess, variant: a })
     }
 
     // toast  enddddddddddddddd

    const { register, handleSubmit, watch, errors } = useForm();

    // const onSubmit = data => {
    //     if (data.UserName !== "admin") {
    //         const messageLogin = "User or password incorrect !";
    //         setloginState(messageLogin);
    //     } else {
    //         const messageLogin = "ok !";
    //         setloginState(messageLogin);
    //     }
    // }

    // console.log(watch("example")); // watch input value by passing the name of it

    // const { dispatch, store } = useContext(AppContext);
    const location = useLocation();
    const history = useHistory();

    // const { from } = location.state || { from: { pathname: '/' } };


    const onSubmit = async (data) => {
        console.log(data);
        let res = await login(data);
        if (res.authenticated !== undefined && res.authenticated) {
            // dispatch({
            //     type: LOGIN_SUCCESS,
            //     payload: {
            //         isLogged: res.authenticated
            //     }
            // });
            // dispatch({
            //     type: SEARCH_ACTION,
            //     payload: {
            //         isSearchAction: false
            //     }
            // });
            // alertMessage({ type: 'success', message: 'Đăng nhập thành công.' });
            // const messageLogin = "Login Success !";
            //         setloginState(messageLogin);
            
            if (res.role === STAFF_RECEPTION) {
                history.push('/staffreception');  
            }
            if (res.role === STAFF_SERVICE) {
                history.push('/staffservice');  
            }
            if (res.role === STAFF_MANAGER || res.role === STAFF_MANAGER_ADMIN) {
                history.push('/staffmanager');  
            }

        } else {
            // alertMessage({ type: 'error', message: 'Tài khoản hoặc mật khẩu không đúng!' });
            const messageLogin = "User or password incorrect !";
            setloginState(messageLogin);
        }
    }

    const forgetPassWordHandler = () => {
        exportToastInfo("Let's notification for Your Manager ! ");
    }

    return (
        <Container >
            <Typography component="div"
                className="containerQuanliKhachSan" >
                <Grid container direction="row"
                    justify="center"
                    alignItems="center"
                    style={
                        { height: '80vh' }
                    } >
                    < Grid item xs={6} >
                        <div className="gridLogin" >
                            <h1> Welcom MinaHotel </h1>

                            <form className="loginForm" onSubmit={handleSubmit(onSubmit)} >
                                <h3 className={loginState !== "" ? "aler--error container--removespace" : "display--none"}> {loginState} </h3>
                                <div className="form--mod" >
                                    <label > UserName:  </label>
                                    <input name="UserName"
                                        ref={register({ required: true, maxLength: 255 })}
                                    />
                                    {errors.UserName && < p className="container--removespace add--space-margin--left aler--error"> Username is required </p>}
                                </div >

                                <div className="form--mod" >
                                    <label > Password:  </label>
                                    <input name="Password"
                                        ref={register({ required: true, maxLength: 10 })}
                                    />
                                    {errors.Password && < p className="container--removespace add--space-margin--left aler--error"> Password is required </p>}
                                </div>
                                <div className="form--mod form--mod__forgetpassword">
                                    <a href="#" onClick={forgetPassWordHandler}>Forget password</a>
                                </div>
                                <Grid>
                                    <button type="submit" className="btn--quanlikhachsan btn--quanlikhachsan__green__login" > Login </button>
                                </Grid>

                            </form >

                        </div>
                    </ Grid >
                </Grid>
            </Typography>
        </Container>
    )
}

