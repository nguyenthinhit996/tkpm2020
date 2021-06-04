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
import React, {useReducer} from 'react'
import { IconButton } from '@material-ui/core';
import CloseIcon from '@material-ui/icons/Close';
import { Fragment } from 'react';
import PrivateRoute from './core/PrivateRoute';
import StaffServicesIndex from './components/pages/ServicesStaff/StaffServicesIndex';
import {initialState} from './AppState';
import Appcontext from './AppContext';
import reducer from './AppReducer';

import Managerstaffroot from './components/pages/ManageStaff/root/ManagerStaffRoot';
import Staffreceptionroot from './components/pages/ReceptionStaff/root/StaffReceptionRoot'

function App() {

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
    )
  }

  const [store, dispatch] = useReducer(reducer, initialState);

  return (

    <SnackbarProvider maxSnack={5} anchorOrigin={{ vertical: 'top', horizontal: 'right' }} autoHideDuration={5000}
      action={key => <DismissAction id={key} />}>

      <Appcontext.Provider value={{store,dispatch}}>
        <Router>
          <div>
            <Switch> 
              <PrivateRoute path="/admin">
                <Managerstaffroot/>
              </PrivateRoute>

              <PrivateRoute path="/rect">
                <Staffreceptionroot/>
              </PrivateRoute>

              <PrivateRoute path="/staffservice">
                <StaffServicesIndex />
              </PrivateRoute>

              <PrivateRoute path="/Chooseoptionuserchangepass">
                <Chooseoptionuserchangepass />
              </PrivateRoute>
              <PrivateRoute path="/ChooseOptionUserViewInfo">
                <ChooseOptionUserViewInfo />
              </PrivateRoute>

              <Route exact path="/">
                <Login />
              </Route>
            </Switch>
          </div>
        </Router>
      </Appcontext.Provider>
    </SnackbarProvider>
  );
}

export default App;
