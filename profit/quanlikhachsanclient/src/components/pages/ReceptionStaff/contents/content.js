
import React, { useContext } from 'react'
import { Redirect, Switch, useLocation } from 'react-router';
import Appcontext from '../../../../AppContext'
import {STAFF_RECEPTION} from '../../../../constants/ConstApp'
import PrivateRoute from '../../../../core/PrivateRoute';

import StaffReceptionViewOrders from '../child/StaffReceptionViewOrders'
import Staffreceptioncheckoutticket from '../child/StaffReceptionCheckOutTicket'
import Staffreceptionviewroom from '../child/StaffReceptionViewRoom'
import Staffreceptionbookingroom from '../child/StaffReceptionBookingRoom'
import StaffReceptionViewServices from '../child/StaffReceptionViewServices'
import Staffreceptionindex from '../child/StaffReceptionIndex'
 
export default function Content(props) {
    
    const {store} = useContext(Appcontext);
    const locationState = useLocation();
    const {from} = locationState.state || { from:{pathname: '/login'}};


    return (
        <>
            {
                store.role !== STAFF_RECEPTION ?
                <Redirect to={from.pathname}/>
                : 
                <Switch>
                    <PrivateRoute path="/rect/staffReceptionViewOrders" exact>
                        <StaffReceptionViewOrders />
                    </PrivateRoute>

                    <PrivateRoute path="/rect/staffreceptioncheckoutticket" exact>
                        <Staffreceptioncheckoutticket />
                    </PrivateRoute>

                    <PrivateRoute path="/rect/staffreceptionviewroom" exact>
                        <Staffreceptionviewroom />
                    </PrivateRoute>

                    <PrivateRoute path="/rect/staffreceptionbookingroom" exact>
                        <Staffreceptionbookingroom />
                    </PrivateRoute>

                    <PrivateRoute path="/rect/staffReceptionViewServices" exact>
                        <StaffReceptionViewServices />
                    </PrivateRoute>

                    <PrivateRoute path="/rect/staffreception" exact>
                        <Staffreceptionindex />
                    </PrivateRoute>

                </Switch>
            }
        </>
    )
}
