
import React, { useContext } from 'react'
import { Redirect,Switch, useLocation } from 'react-router-dom';
import Appcontext from '../../../../AppContext';
import { STAFF_SERVICE } from '../../../../constants/ConstApp';
import PrivateRoute from '../../../../core/PrivateRoute';
import Notfound from '../../../layout/NotFound';
import StaffServicesIndex from '../child/StaffServicesIndex';

export default function Content(props) {
    
    const {store} = useContext(Appcontext);
    const locationState = useLocation();
    const {from} = locationState.state || { from:{pathname: '/login'}};

    return ( 
        <>
        {
            store.role !== STAFF_SERVICE 
            ? <Redirect to={from} />
            : 
            <Switch>
                <PrivateRoute path="/sv/staffservice" exact>
                    <StaffServicesIndex/>
                </PrivateRoute>

                <PrivateRoute component={Notfound} />
            </Switch>
        }
        </>
    )
}
