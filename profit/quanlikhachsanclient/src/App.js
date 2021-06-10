import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import Login from './components/pages/Login';
import ChooseOptionUserViewInfo from './components/layout/ChooseOptionUserViewInfo';
import Chooseoptionuserchangepass from './components/layout/ChooseOptionUserChangePass';
import { SnackbarProvider, useSnackbar } from 'notistack';
import React, { useEffect, useReducer, useState } from 'react'
import { IconButton } from '@material-ui/core';
import CloseIcon from '@material-ui/icons/Close';
import { Fragment } from 'react';
import PrivateRoute from './core/PrivateRoute';
import { initialState } from './AppState';
import Appcontext from './AppContext';
import reducer from './AppReducer';
import StaffServiceRoot from './components/pages/ServicesStaff/root/StaffServiceRoot'
import Managerstaffroot from './components/pages/ManageStaff/root/ManagerStaffRoot';
import Staffreceptionroot from './components/pages/ReceptionStaff/root/StaffReceptionRoot'
import CircularProgress from '@material-ui/core/CircularProgress';
import Footer from './components/layout/Footer';


const DismissAction = ({ id }) => {
  const { closeSnackbar } = useSnackbar()
  return (
    <Fragment>
      <IconButton
        aria-label="close"
        color="inherit"
        onClick={() => closeSnackbar(id)}
      >
        <CloseIcon />
      </IconButton>
    </Fragment>
  )}


function App() {

  const [statLoading, setLoading] = useState(false);

  const [store, dispatch] = useReducer(reducer, initialState);

  useEffect(() => {
    setLoading(store.flagLoadding);
  }, [store])

  return (
    <SnackbarProvider maxSnack={5} anchorOrigin={{ vertical: 'top', horizontal: 'right' }} autoHideDuration={5000}
      action={key => <DismissAction id={key} />}>
      <Appcontext.Provider value={{ store, dispatch }}>
        <div className="AppContainer">
          <div className={statLoading === true ? "AppContainer__loading" : "AppContainer--displayNone"}>
            <div className="AppContainer__loading AppContainer__loading--shadow"></div>
            <CircularProgress thickness={4} size="100px" variant="indeterminate" color="primary" />
          </div>
          <div className="AppContainer__content">
            <Router>
              <Switch>

                <PrivateRoute path="/admin">
                  <Managerstaffroot />
                </PrivateRoute>

                <PrivateRoute path="/rect">
                  <Staffreceptionroot />
                </PrivateRoute>
                
                <PrivateRoute path="/sv">
                  <StaffServiceRoot/>
                </PrivateRoute>

                <PrivateRoute path="/Chooseoptionuserchangepass">
                  <Chooseoptionuserchangepass />
                </PrivateRoute>

                <PrivateRoute path="/ChooseOptionUserViewInfo">
                  <ChooseOptionUserViewInfo />
                </PrivateRoute>

                <Route>
                  <Login />
                </Route>

              </Switch>
            </Router>
          </div>
          <Footer/>
        </div>
      </Appcontext.Provider>
    </SnackbarProvider>
  );
}

export default App;
