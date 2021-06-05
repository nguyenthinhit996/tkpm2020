import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import './ServiceView.css'
import AddIcon from '@material-ui/icons/Add';
import { IconButton } from '@material-ui/core';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import CurrencyFormat from 'react-currency-format';
 

const useStyles = makeStyles({
    root: {
        maxWidth: 200,
    },
    media: {
        height: 0,
        paddingTop: '56.25%', // 16:9
    },
});

export default function Serviceview({value,addProductHandler}) {

    // const {value} = props;

    const classes = useStyles();

    // const value = {
    //     imageSrc: images,
    //     nameProduct: "Name Product",
    //     cardContent: " this is contect of product",
    //     moneyProduct:"5000000"
    // }


    return (
        <div className="serviceview">
            <Card className={classes.root}>
                <CardActionArea>
                    <CardMedia
                        className={classes.media}
                        image={value.imageProduct}
                        title={value.nameProduct}
                    />
                    <CardContent className="group-content-services">
                        <Typography gutterBottom variant="h6" component="h3">
                            {value.nameProduct}
                        </Typography>
                        <Typography variant="body2" color="textSecondary" component="p">
                            {value.extensionProduct}
                        </Typography>
                    </CardContent>
                </CardActionArea>
                <CardActions className="btn-controlAddServices">
                    <h3>
                    <CurrencyFormat value={value.productRate} displayType={'text'} thousandSeparator={true} suffix={'đ'} renderText={value => <div>{value}</div>} />
                    </h3>
                    <IconButton onClick={() => addProductHandler(value) } color="primary" aria-label="upload picture" component="span">
                        <AddIcon fontSize="large" />
                    </IconButton>
                </CardActions>
            </Card>
        </div>
    );
}
