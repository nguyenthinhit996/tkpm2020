import { Container, Grid, makeStyles, Paper, Typography } from '@material-ui/core'
import { useForm } from "react-hook-form";
import React, { useState } from 'react'
import '../layout/Body.css'
import './Login.css'


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

    const { register, handleSubmit, watch, errors } = useForm();
    const onSubmit = data => {
        if (data.UserName !== "admin") {
            const messageLogin = "User or password incorrect !";
            setloginState(messageLogin);
        } else {
            const messageLogin = "ok !";
            setloginState(messageLogin);
        }
    }

    console.log(watch("example")); // watch input value by passing the name of it

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

                            <form onSubmit={handleSubmit(onSubmit)} >
                                <h3 className={loginState !== "" ? "aler--error container--removespace" : "display--none"}> {loginState} </h3>
                                <div className="form--mod" >
                                    <label > UserName:  </label>
                                    <input name="UserName"
                                        ref={register({ required: true, maxLength: 10 })}
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
                                    <a href="https://www.w3schools.com">Forget password</a>
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

