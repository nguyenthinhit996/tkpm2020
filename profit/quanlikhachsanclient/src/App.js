import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useRouteMatch,
  useParams
} from "react-router-dom";
import Login from './components/pages/Login';
import Navigation from './components/layout/Navigation';
import Staffreceptionindex from './components/pages/ReceptionStaff/StaffReceptionIndex';
import ChooseOptionUserViewInfo from './components/layout/ChooseOptionUserViewInfo';
import Chooseoptionuserchangepass from './components/layout/ChooseOptionUserChangePass';
import { SnackbarProvider, useSnackbar } from 'notistack';
import React, { useEffect, useState } from 'react'
import { Button, IconButton } from '@material-ui/core';
import CloseIcon from '@material-ui/icons/Close';
import { Fragment } from 'react';
import Roomview from './components/componentChild/RoomView';
import Staffreceptionbookingroom from './components/pages/ReceptionStaff/StaffReceptionBookingRoom';
import Staffreceptionviewroom from './components/pages/ReceptionStaff/StaffReceptionViewRoom';
import Staffreceptioncheckoutticket from './components/pages/ReceptionStaff/StaffReceptionCheckOutTicket';
import StaffReceptionViewOrders from './components/pages/ReceptionStaff/StaffReceptionViewOrders';
import Serviceview from './components/componentChild/ServiceView';
import StaffReceptionViewServices from './components/pages/ReceptionStaff/StaffReceptionViewServices';
import TableViewOrder from './components/componentChild/TableViewOrder';
import PrivateRoute from './core/PrivateRoute';
import StaffServicesIndex from './components/pages/ServicesStaff/StaffServicesIndex';
import Managerstaffindex from './components/pages/ManageStaff/ManagerStaffIndex';
import Staffmanagementdailyworking from './components/pages/ManageStaff/StaffManagementDailyWorking';
import StaffManagementManageStaff from './components/pages/ManageStaff/StaffManagementManageStaff';
import StaffManagementSalary from './components/pages/ManageStaff/StaffManagementSalary';
import StaffManagementAdmin from './components/pages/ManageStaff/StaffManagementAdmin';
import StaffManagementAdminChangeProduct from './components/pages/ManageStaff/StaffManagementAdminChangeProduct';
import Test from './components/pages/ManageStaff/test';
import Testchart from './components/pages/ManageStaff/testChart';


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

  return (

    <SnackbarProvider maxSnack={5} anchorOrigin={{ vertical: 'top', horizontal: 'right' }} autoHideDuration={5000}
      action={key => <DismissAction id={key} />}>
      <Router>
        <div>
          <Switch>

          
          <Route path="/Testchart">
              <Testchart />
            </Route>
          
          <Route path="/Test">
              <Test />
            </Route>

          <Route path="/StaffManagementAdminChangeProduct">
              <StaffManagementAdminChangeProduct />
            </Route>
          
          <Route path="/StaffManagementAdmin">
              <StaffManagementAdmin />
            </Route>
          
          <Route path="/StaffManagementSalary">
              <StaffManagementSalary />
            </Route>
          
          <Route path="/StaffManagementManageStaff">
              <StaffManagementManageStaff />
            </Route>
          
          <Route path="/Staffmanagementdailyworking">
              <Staffmanagementdailyworking />
            </Route>

            <Route path="/TableViewOrder">
              <TableViewOrder />
            </Route>

            <Route path="/StaffReceptionViewServices">
              <StaffReceptionViewServices />
            </Route>

            <Route path="/Serviceview">
              <Serviceview />
            </Route>

            <Route path="/StaffReceptionViewOrders">
              <StaffReceptionViewOrders />
            </Route>

            <Route path="/Staffreceptioncheckoutticket">
              <Staffreceptioncheckoutticket />
            </Route>

            <Route path="/Staffreceptionviewroom">
              <Staffreceptionviewroom />
            </Route>

            <Route path="/Staffreceptionbookingroom">
              <Staffreceptionbookingroom />
            </Route>

            <Route path="/Roomview">
              <Roomview />
            </Route>

            <Route path="/Chooseoptionuserchangepass">
              <Chooseoptionuserchangepass />
            </Route>
            <Route path="/ChooseOptionUserViewInfo">
              <ChooseOptionUserViewInfo />
            </Route>
            <Route path="/navigation">
              <Navigation />
            </Route>
            <PrivateRoute path="/staffreception">
              <Staffreceptionindex />
            </PrivateRoute>

            <PrivateRoute path="/staffservice">
              <StaffServicesIndex />
            </PrivateRoute>

            {/* <PrivateRoute path="/staffmanager">
              <Managerstaffindex />
            </PrivateRoute> */}

            <Route path="/staffmanager">
              <Managerstaffindex />
            </Route>
            
            
            <Route path="/">
              <Login />
            </Route>



          </Switch>
        </div>
      </Router>

    </SnackbarProvider>
  );
}

export default App;
