import { AppBar, Badge, Button, IconButton, makeStyles, Menu, MenuItem, Toolbar, Typography, withStyles } from '@material-ui/core'
import AccountCircleRoundedIcon from '@material-ui/icons/AccountCircleRounded';
import NotificationsNoneTwoToneIcon from '@material-ui/icons/NotificationsNoneTwoTone';
import React, { useState } from 'react'
import './Navigation.css'

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    title: {
        flexGrow: 1,
    },
}));

const StyledMenu = withStyles({
    paper: {
      border: '1px solid #d3d4d5',
      backgroundColor: '#67D265',
      marginTop:'-10px',
       
    },
  })((props) => (
    <Menu
      elevation={0}
      getContentAnchorEl={null}
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'center',
      }}
      transformOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      {...props}
    />
  ));


export default function Navigation({ pros }) {

    const classes = useStyles();

    const [notification, setnotification] = useState(10);

    const [anchorElAccount, setAnchorElAccount] = useState(null);

    const [anchorElNotidication, setAnchorElNotidication] = useState(null);

    const handleAccountClick = (event) => {
        setAnchorElAccount(event.currentTarget);
        console.log(event)
      
    };

    const handleNotificationClick = (event) => {
        setAnchorElNotidication(event.currentTarget);
        console.log(event)
      
    };

    const handleClose = (event) => {
        // alert(event.target.value)
        setAnchorElAccount(null);
        setAnchorElNotidication(null);
    };


    return (
        <div className={classes.root}>
            <AppBar position="static" className="navigation--mod">
                <Toolbar>
                    <Typography variant="h4" className="font--Quanlikhachsan">
                        <a className="link--tagA--mod" href="http://localhost:3000/">Welcom MinaHotel</a>
                    </Typography>
                    <Typography variant="h5" className={classes.title}></Typography>

                    <IconButton edge="start" aria-label="menu" onClick={handleNotificationClick}>
                        <Badge badgeContent={notification} color="secondary">
                            <NotificationsNoneTwoToneIcon fontSize="large" className="bell-icon"/>
                        </Badge>
                    </IconButton>

                    <IconButton edge="end" aria-label="menu" onClick={handleAccountClick}>
                        <AccountCircleRoundedIcon fontSize="large" className="acount-icon" />
                    </IconButton>
                </Toolbar>
            </AppBar>


            <StyledMenu
                id="simple-menu-account"
                anchorEl={anchorElAccount}
                keepMounted
                open={Boolean(anchorElAccount)}
                onClose={handleClose}
            >
                <MenuItem value="Profile" onClick={handleClose}>Profile</MenuItem>
                <MenuItem value="200" onClick={handleClose}>My account</MenuItem>
                <MenuItem value="300" onClick={handleClose}>Logout</MenuItem>
            </StyledMenu>

            <StyledMenu
                id="simple-menu-notification"
                anchorEl={anchorElNotidication}
                keepMounted
                open={Boolean(anchorElNotidication)}
                onClose={handleClose}
            >
                <MenuItem className="noti-text-overflow-ellipsis noti-text-overflow-ellipsis__not__viewed" onClick={handleClose}>Profile Profile Profile</MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleClose}>My account sfsdfsdafasfsfdsfsfsdfsdfsfdaccount </MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis noti-text-overflow-ellipsis__not__viewed" onClick={handleClose}>Logout</MenuItem>

                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleClose}>Profile Profile Profile</MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleClose}>My account sfsdfsdafasfsfdsfsfsdfsdfsfdaccount </MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleClose}>Logout</MenuItem>

                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleClose}>Profile Profile Profile</MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleClose}>My account sfsdfsdafasfsfdsfsfsdfsdfsfdaccount </MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleClose}>Logout</MenuItem>

                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleClose}>Profile Profile Profile</MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleClose}>My account sfsdfsdafasfsfdsfsfsdfsdfsfdaccount </MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleClose}>Logout</MenuItem>      

            </StyledMenu>

        </div>
    );
}
