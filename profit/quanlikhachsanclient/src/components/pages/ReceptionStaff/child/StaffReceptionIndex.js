import { Button, InputLabel, makeStyles, NativeSelect, TextField } from '@material-ui/core'
import React, { useEffect, useRef, useState } from 'react'
import { useSnackbar } from 'notistack';
import './staffreceptionindex.css'
import Roomview from '../../../plugins/RoomView'
import { useHistory } from 'react-router-dom'
import { detailAllRoom } from '../../../../core/room'
import { HandleError } from '../../../../core/handleDataFromDB'

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
        '& > *': {
            margin: theme.spacing(2),
            width: theme.spacing(36),
            height: theme.spacing(16),
        },
        justifyContent: 'space-around',
        flexDirection: 'row',

    },
}));


export default function Staffreceptionindex(props) {

    const history = useHistory();

    const classes = useStyles();

    //-----------------toast start
    const [messageToast, setmessageToast] = useState({ message: '', variant: '' });

    useEffect(() => {
        if (messageToast.message.length !== 0) {
            handlerMessageToast(messageToast.message, messageToast.variant);
        }
    }, [messageToast])

    const [countRoomState, setcountRoomState] = useState({
        countALl: 0,
        countEmplty: 0,
        countFull: 0,
        countClean: 0
    })

    const [dataFetchIsServer, setdataFetchIsServer] = useState([]);
    useEffect(async () => {
        let data = await detailAllRoom();
        if (HandleError(data)) {
            let mess = data.data.content_error;
            let variant = 'error';
            setmessageToast({ message: mess, variant: variant })
            history.push("/");
        }else{
            setdataFetchIsServer(data);
        }
    }, [])

    const { enqueueSnackbar } = useSnackbar();


    const handlerMessageToast = (mess, variant) => {
        // variant could be success, error, warning, info, or default
        enqueueSnackbar(mess, { variant });
    };

    const testToast = () => {
        let mess = "dsfsadf " + Math.random();
        let a = 'warning';
        setmessageToast({ message: mess, variant: a })
    }

    //--------------------toast end

    const [buttonControl, setbuttonControl] = useState({ all: true, empty: false, full: false, clean: false });

    // contron button start
    const handlerButtonControl = (value, event) => {
        console.log(value);
        resetFilter("button");
        switch (value) {
            case "AllRoom":
                setbuttonControl({ ...buttonControl, all: true, empty: false, full: false, clean: false });
                setlistDataIsFilter([...dataFetchIsServer]);
                break;
            case "Empty":
                setbuttonControl({ ...buttonControl, all: false, empty: true, full: false, clean: false });
                var isEmptyRoom = dataFetchIsServer.filter(value => value.isEmpty);
                setlistDataIsFilter([...isEmptyRoom]);
                break;
            case "Full":
                setbuttonControl({ ...buttonControl, all: false, empty: false, full: true, clean: false });
                var isFullRoom = dataFetchIsServer.filter(value => value.isFull);
                setlistDataIsFilter([...isFullRoom]);
                break;
            case "Clean":
                setbuttonControl({ ...buttonControl, all: false, empty: false, full: false, clean: true });
                var isCleanRoom = dataFetchIsServer.filter(value => value.isClean);
                setlistDataIsFilter([...isCleanRoom]);
                break;
            default:
                setbuttonControl({ ...buttonControl, all: true, empty: false, full: false, clean: false });
                break;
        }
    }
    // contron button end

    const clickRoom = (numberRoom, idticketbooking) => {

        var getStatusOfRoom = dataFetchIsServer.find(value => value.numberRoom === numberRoom);

        console.log(getStatusOfRoom)
        if (getStatusOfRoom.isEmpty) {
            history.push({
                pathname: '/rect/staffReceptionBookingRoom',
                state: { numberRoom: numberRoom }
            });
        }
        else if (getStatusOfRoom.isFull) {
            history.push({
                pathname: '/rect/staffreceptionviewroom',
                state: {
                    numberRoom: numberRoom,
                    idticketbooking: idticketbooking
                }
            });
        }

    }


    // Data fetch from server
    // control button end
    // const isEmpty = true;
    // const isFull = true;
    // const isClean = true;
    // const numberRoom = "123";
    // const typeRoom = "Single";
    // const timeStartRent = "2020/12/12 12:00"
    // const nameRent = "Nguyen van a"
    // const nameCLean = "Nguyen van a"
    // const timeRent = "02:00:00"
    // const timeClean = "00:03:00"

    // const dataFetchIsServer = [{
    //     isEmpty: true,
    //     isFull: false,
    //     isClean: false,
    //     numberRoom: "123",
    //     typeRoom: "Single",
    //     timeStartRent: "2020/12/12 12:00",
    //     timeStartClean:"2020/12/12 12:00",
    //     nameRent: "Nguyen van a",
    //     nameCLean: "Nguyen van a",
    //     timeRent: "02:00:00",
    //     timeClean: "00:03:00"
    // }
    //     ,
    // {
    //     isEmpty: false,
    //     isFull: true,
    //     isClean: false,
    //     numberRoom: "1244",
    //     typeRoom: "Single",
    //     timeStartRent: "2020/12/12 12:00",
    //     timeStartClean:"2020/12/12 12:00",
    //     nameRent: "Nguyen van a",
    //     nameCLean: "Nguyen van a",
    //     timeRent: "02:00:00",
    //     timeClean: "00:03:00"
    // },
    // {
    //     isEmpty: false,
    //     isFull: false,
    //     isClean: true,
    //     numberRoom: "13",
    //     typeRoom: "Single",
    //     timeStartRent: "2020/12/12 12:00",
    //     timeStartClean:"2020/12/12 12:00",
    //     nameRent: "Nguyen van a",
    //     nameCLean: "Nguyen van a",
    //     timeRent: "02:00:00",
    //     timeClean: "00:03:00"
    // },
    // {
    //     isEmpty: true,
    //     isFull: false,
    //     isClean: false,
    //     numberRoom: "723",
    //     typeRoom: "Double",
    //     timeStartRent: "2020/12/12 12:00",
    //     timeStartClean:"2020/12/12 12:00",
    //     nameRent: "Nguyen van a",
    //     nameCLean: "Nguyen van a",
    //     timeRent: "02:00:00",
    //     timeClean: "00:03:00"
    // }
    //     ,
    // {
    //     isEmpty: false,
    //     isFull: true,
    //     isClean: false,
    //     numberRoom: "4",
    //     typeRoom: "Single",
    //     timeStartRent: "2020/12/12 12:00",
    //     timeStartClean:"2020/12/12 12:00",
    //     nameRent: "Nguyen van a",
    //     nameCLean: "Nguyen van a",
    //     timeRent: "02:00:00",
    //     timeClean: "00:03:00"
    // },
    // {
    //     isEmpty: false,
    //     isFull: false,
    //     isClean: true,
    //     numberRoom: "1333",
    //     typeRoom: "Double",
    //     timeStartRent: "2020/12/12 12:00",
    //     timeStartClean:"2020/12/12 12:00",
    //     nameRent: "Nguyen van a",
    //     nameCLean: "Nguyen van a",
    //     timeRent: "02:00:00",
    //     timeClean: "00:03:00"
    // }
    // ,
    // {
    //     isEmpty: false,
    //     isFull: true,
    //     isClean: false,
    //     numberRoom: "3334",
    //     typeRoom: "Vip",
    //     timeStartRent: "2020/12/12 12:00",
    //     timeStartClean:"2020/12/12 12:00",
    //     nameRent: "Nguyen van a",
    //     nameCLean: "Nguyen van a",
    //     timeRent: "02:00:00",
    //     timeClean: "00:03:00"
    // },
    // {
    //     isEmpty: false,
    //     isFull: false,
    //     isClean: true,
    //     numberRoom: "1366633",
    //     typeRoom: "Vip",
    //     timeStartRent: "2020/12/12 12:00",
    //     timeStartClean:"2020/12/12 12:00",
    //     nameRent: "Nguyen van a",
    //     nameCLean: "Nguyen van a",
    //     timeRent: "02:00:00",
    //     timeClean: "00:03:00"
    // }]

    useEffect(() => {
        setlistDataIsFilter(dataFetchIsServer);
        setcountRoomState({
            ...countRoomState,
            countALl: dataFetchIsServer.length
            , countEmplty: dataFetchIsServer.filter(value => value.isEmpty).length
            , countFull: dataFetchIsServer.filter(value => value.isFull).length
            , countClean: dataFetchIsServer.filter(value => value.isClean).length
        })
    }, [dataFetchIsServer])

    const [listDataIsFilter, setlistDataIsFilter] = useState(dataFetchIsServer)

    // filter number room
    const cleanText = useRef(null);
    const handlerInputNumberRoom = (event) => {
        resetFilter("numberRoom");
        console.log(event.target.value)
        if (event.target.value !== undefined && event.target.value != 0) {
            var isListFilter = dataFetchIsServer.filter(value => value.numberRoom == event.target.value)
            setlistDataIsFilter([...isListFilter]);
        } else {
            setlistDataIsFilter([...dataFetchIsServer]);
        }
    }

    //Type Room
    const defaultAllSelect = useRef(null);
    const handleChangeTypeRoomFilter = (event) => {
        resetFilter("typeRoom");
        console.log(event);
        if (event.target.value !== undefined) {
            if ("all".includes(event.target.value)) {
                setlistDataIsFilter([...dataFetchIsServer]);
            } else {
                var isListFilter = dataFetchIsServer.filter(value =>
                    value.typeRoom.toLowerCase().includes(event.target.value.toLowerCase()))
                setlistDataIsFilter([...isListFilter]);
            }
        }
    }

    //reset filter 
    const resetFilter = (action) => {
        if ("button".includes(action)) {
            cleanText.current.firstElementChild.firstElementChild.value = "";
            defaultAllSelect.current.firstElementChild.value = "all";
        }
        if ("numberRoom".includes(action)) {
            defaultAllSelect.current.firstElementChild.value = "all";
        }
        if ("typeRoom".includes(action)) {
            cleanText.current.firstElementChild.firstElementChild.value = "";
        }
    }

    return (
        <div className="staffreceptionindex">
            <div className="controlRoomOfReceptionUser">
                <div>
                    <label> Filter Number Room:  </label>
                    <TextField ref={cleanText} onChange={handlerInputNumberRoom} name="filterRoomByNumber" type="number" />
                </div>
                <div className="controlButtonOfReceptionUser">
                    <Button onClick={() => handlerButtonControl("AllRoom")} className="btn--quanlikhachsan btn--quanlikhachsan--AllRoom"
                        variant={buttonControl.all ? "contained" : "outlined"}  >All({countRoomState.countALl})</Button>
                    <Button onClick={() => handlerButtonControl("Empty")} className="btn--quanlikhachsan btn--quanlikhachsan--Empty"
                        variant={buttonControl.empty ? "contained" : "outlined"} >Empty ({countRoomState.countEmplty})</Button>
                    <Button onClick={() => handlerButtonControl("Full")} className="btn--quanlikhachsan btn--quanlikhachsan--Full"
                        variant={buttonControl.full ? "contained" : "outlined"} >Full ({countRoomState.countFull})</Button>
                    <Button onClick={() => handlerButtonControl("Clean")} className="btn--quanlikhachsan btn--quanlikhachsan--Clean"
                        variant={buttonControl.clean ? "contained" : "outlined"} >Cleaning ({countRoomState.countClean})</Button>
                    <div className="controlSelectTypeRoom">
                        <InputLabel >Type Room</InputLabel>
                        <NativeSelect
                            ref={defaultAllSelect}
                            onChange={handleChangeTypeRoomFilter}
                            inputProps={{
                                name: 'age',
                                id: 'age-native-helper',
                            }}
                        >
                            <option value={"all"}  >all</option>
                            <option value={"single"} >single</option>
                            <option value={"double"}>double</option>
                            <option value={"vip"}>vip</option>
                        </NativeSelect>
                    </div>
                </div>
            </div>

            <div className={classes.root}>
                {
                    listDataIsFilter.map(value => {
                        return <Roomview key={value.numberRoom} data={value} clickRoom={clickRoom} />
                    })
                }
            </div>
        </div>
    )
}
