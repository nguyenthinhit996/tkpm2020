import { AppBar, Badge, Button, IconButton, makeStyles, Menu, MenuItem, Toolbar, Typography, withStyles } from '@material-ui/core'
import AccountCircleRoundedIcon from '@material-ui/icons/AccountCircleRounded';
import NotificationsNoneTwoToneIcon from '@material-ui/icons/NotificationsNoneTwoTone';
import React, { useState } from 'react'
import Chooseoptionuserviewinfo from './ChooseOptionUserViewInfo';
import './Navigation.css';
import NavigationAppContext from '../../stores/NavigationAppContext'
import Chooseoptionuserchangepass from './ChooseOptionUserChangePass';
import Chooseoptionuserlogout from './ChooseOptionUserLogout';
import { getHomePage } from '../../constants/ConstApp';
import { useHistory } from 'react-router-dom';

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
        marginTop: '-10px',

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

    const history = useHistory();

    const [notification, setnotification] = useState(10);

    const [anchorElAccount, setAnchorElAccount] = useState(null);

    const [anchorElNotidication, setAnchorElNotidication] = useState(null);

    const handleAccountClick = (event) => {
        setAnchorElAccount(event.currentTarget);
    };

    const handleNotificationClick = (event) => {
        setAnchorElNotidication(event.currentTarget);
    };

    const [openViewInfor, setopenViewInfor] = useState(false);
    const [openChangePass, setopenChangePass] = useState(false);
    const [openLogout, setopenLogout] = useState(false);
     

    const handleCloseAcountMenu = (event) => {
        const valueOfMenu = event.target.dataset !== undefined ? event.target.dataset.myValue : null;
        if (valueOfMenu !== null) {
            switch (valueOfMenu) {
                case "OpenViewInfo":
                    setopenViewInfor(true);
                    break;
                case "OpenChangePass":
                    setopenChangePass(true);
                    break;
                case "OpenLogout":
                    setopenLogout(true);
                    break;
                default:
                    break;
            }
        }

        setAnchorElAccount(null);
    };

    const handleCloseNotificationMenu = (event) => {
        setAnchorElNotidication(null);
    };

    var linkHome = () => {
        history.push({
            pathname: getHomePage(),
        });
        return 
    }

    return (
        <div className={classes.root}>
            <AppBar position="static" className="navigationAppBar">
                <Toolbar>
                    <Typography onClick={linkHome} variant="h4" className="font--Quanlikhachsan navigationAppBar--home">
                        <a className="link--tagA--mod">Welcom MinaHotel</a>
                    </Typography>
                    <Typography variant="h5" className={classes.title}></Typography>

                    <IconButton edge="start" aria-label="menu" onClick={handleNotificationClick}>
                        <Badge badgeContent={notification} color="secondary">
                            <NotificationsNoneTwoToneIcon fontSize="large" className="bell-icon" />
                        </Badge>
                    </IconButton>

                    <IconButton edge="end" aria-label="menu" onClick={handleAccountClick}>
                        <AccountCircleRoundedIcon fontSize="large" className="acount-icon" />
                    </IconButton>
                </Toolbar>
            </AppBar>


            <StyledMenu
                id="simple-menu-account"
                className="navigationStyledMenu"
                anchorEl={anchorElAccount}
                keepMounted
                open={Boolean(anchorElAccount)}
                onClose={handleCloseAcountMenu}
            >
                <MenuItem data-my-value="OpenViewInfo" onClick={handleCloseAcountMenu}>View Info</MenuItem>
                <MenuItem data-my-value="OpenChangePass" onClick={handleCloseAcountMenu}>Change Pass</MenuItem>
                <MenuItem data-my-value="OpenLogout" onClick={handleCloseAcountMenu}>Logout</MenuItem>
            </StyledMenu>

            <StyledMenu
                id="simple-menu-notification"
                className="navigationStyledMenu"
                anchorEl={anchorElNotidication}
                keepMounted
                open={Boolean(anchorElNotidication)}
                onClose={handleCloseNotificationMenu}
            >
                <MenuItem className="noti-text-overflow-ellipsis noti-text-overflow-ellipsis__not__viewed" onClick={handleCloseNotificationMenu}>Profile Profile Profile</MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleCloseNotificationMenu}>My account sfsdfsdafasfsfdsfsfsdfsdfsfdaccount </MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis noti-text-overflow-ellipsis__not__viewed" onClick={handleCloseNotificationMenu}>Logout</MenuItem>

                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleCloseNotificationMenu}>Profile Profile Profile</MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleCloseNotificationMenu}>My account sfsdfsdafasfsfdsfsfsdfsdfsfdaccount </MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleCloseNotificationMenu}>Logout</MenuItem>

                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleCloseNotificationMenu}>Profile Profile Profile</MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleCloseNotificationMenu}>My account sfsdfsdafasfsfdsfsfsdfsdfsfdaccount </MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleCloseNotificationMenu}>Logout</MenuItem>

                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleCloseNotificationMenu}>Profile Profile Profile</MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleCloseNotificationMenu}>My account sfsdfsdafasfsfdsfsfsdfsdfsfdaccount </MenuItem>
                <MenuItem className="noti-text-overflow-ellipsis" onClick={handleCloseNotificationMenu}>Logout</MenuItem>

            </StyledMenu>

            {/* Componet children */}
            <NavigationAppContext.Provider value = {{openViewInfor, setopenViewInfor,openChangePass, setopenChangePass,openLogout, setopenLogout}}>
                {openViewInfor && <Chooseoptionuserviewinfo />}
                {openChangePass && <Chooseoptionuserchangepass />}
                {openLogout && <Chooseoptionuserlogout />}
            </NavigationAppContext.Provider>
        </div>
    );
}
