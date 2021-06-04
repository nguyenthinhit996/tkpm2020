
import React, { useContext } from 'react'
import { Switch, Redirect, useLocation } from "react-router-dom";
import Appcontext from '../../../../AppContext'
import PrivateRoute from '../../../../core/PrivateRoute';
import Managerstaffindex from '../child/ManagerStaffIndex'
import StaffManagementAdminChangeProduct  from '../child/StaffManagementAdminChangeProduct'
import StaffManagementAdmin  from '../child/StaffManagementAdmin'
import StaffManagementSalary  from '../child/StaffManagementSalary'
import StaffManagementManageStaff  from '../child/StaffManagementManageStaff'
import Staffmanagementdailyworking  from '../child/StaffManagementDailyWorking'
import {STAFF_MANAGER,STAFF_MANAGER_ADMIN} from '../../../../constants/ConstApp'

export default function Content(props) {
    
    const {store} = useContext(Appcontext);
    const location = useLocation();
    const { from } = location.state || { from: { pathname: '/login' } };

    return (
        <>
            { (store.role !== STAFF_MANAGER && store.role !== STAFF_MANAGER_ADMIN) ? 
            <Redirect to={from.pathname} /> 
            :
            <Switch>
              <PrivateRoute path="/admin/staffManagementAdminChangeProduct" >
                <StaffManagementAdminChangeProduct />
              </PrivateRoute>

              <PrivateRoute path="/admin/staffManagementAdmin" >
                <StaffManagementAdmin />
              </PrivateRoute>

              <PrivateRoute path="/admin/staffManagementSalary" >
                <StaffManagementSalary />
              </PrivateRoute>

              <PrivateRoute path="/admin/staffManagementManageStaff" >
                <StaffManagementManageStaff />
              </PrivateRoute>

              <PrivateRoute path="/admin/staffmanagementdailyworking" >
                <Staffmanagementdailyworking />
              </PrivateRoute>

              <PrivateRoute path="/admin/staffmanager" >
                <Managerstaffindex />
              </PrivateRoute>
            </Switch>
            }
        </>
    )
}
