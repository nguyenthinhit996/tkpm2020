import React, { useContext } from 'react';
import { Route, Redirect } from "react-router-dom";

// import AppContext from '../../AppContext';

const PrivateRoute = ({ children, ...rest }) => {

var isLogged = localStorage.onlineAcademy_authenticated
  const renderChildren = ({ location }) => {
    return isLogged
      ? children
      : <Redirect
        to={{
          pathname: '/',
          state: { from: location }
        }}
      />
  }

  return (
    <Route {...rest} render={renderChildren} />
  );
}

export default PrivateRoute;