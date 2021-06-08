
import { Button, Icon, IconButton } from '@material-ui/core';
import React from 'react'
import { useHistory } from 'react-router-dom'
import { getHomePage } from "../../constants/ConstApp"
import './NotFound.css'
import HomeIcon from '@material-ui/icons/Home';

export default function Notfound(props) {

    const history = useHistory();

    const goHome = () => {
        let path = getHomePage();
        history.push(path);
    }

    return (
        <div className="NotFoundContainer">
            <div className="NotFoundContainer__body">
                <div className="NotFoundContainer__Icon" onClick={goHome}>
                    <IconButton color="inherit">
                        <HomeIcon fontSize="large" />
                    </IconButton>
                    <h1> Not Found Page </h1>
                </div>
                <img src="https://doyouconvert.com/wp-content/uploads/2018/04/404_Error.jpg" />
            </div>
        </div>
    )
}
