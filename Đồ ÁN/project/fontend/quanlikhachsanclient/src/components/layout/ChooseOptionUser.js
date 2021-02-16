import { Button, Menu, MenuItem } from '@material-ui/core'
import React, { useState } from 'react'

export default function Chooseoptionuser(props) {

    const [anchorEl, setAnchorEl] = useState(null);

    

    const handleClick = (event) => {
      setAnchorEl(event.currentTarget);
      console.log(event.currentTarget);
    };
  
    const handleClose = () => {
      setAnchorEl(null);
    };
  

    return (
        <div>
            <Button aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
                Open Menu
            </Button>
            <Menu
                id="simple-menu"
                anchorEl={anchorEl}
                keepMounted
                open={Boolean(anchorEl)}
                onClose={handleClose}
            >
                <MenuItem onClick={handleClose}>Profile</MenuItem>
                <MenuItem onClick={handleClose}>My account</MenuItem>
                <MenuItem onClick={handleClose}>Logout</MenuItem>
            </Menu>
        </div>
    )
}
