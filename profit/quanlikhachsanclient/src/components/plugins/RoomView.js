import { Paper } from '@material-ui/core'
import React from 'react'
import './Roomview.css'
import '../layout/Body.css'



export default function Roomview({data, clickRoom }) {

    const isEmpty = data.isEmpty;
    const isFull = data.isFull;
    const isClean = data.isClean;
    const numberRoom = data.numberRoom;
    const typeRoom = data.typeRoom;
    const timeStartRent = data.timeStartRent;
    const timeStartClean = data.timeStartClean;
    const nameRent = data.nameRent;
    const nameCLean = data.nameCLean;
    const timeRent = data.timeRent;
    const timeClean = data.timeClean;
    const idticketbooking=data.idticketbooking

    const actionChooseRoom = () => {
        clickRoom(numberRoom,idticketbooking);
    }

    return (
        <div onClick={actionChooseRoom} >
            {isEmpty && <div className="roomview">
                <div className="emptyHead">
                    <p className="container--removespace">Room</p>
                    <p>{numberRoom}</p>
                    <p className="container--removespace">{typeRoom}</p>
                </div>
                <div className="emptyTail">
                    <h3 style={{ margin: '53px 30px' }}>Empty</h3>
                </div>
            </div>}

            {isFull && <div className="roomview">
                <div className="fullHead">
                    <p className="container--removespace">Room</p>
                    <p>{numberRoom}</p>
                    <p className="container--removespace">{typeRoom}</p>
                </div>
                <div className="fullTail">
                    <div>
                        <p>{timeStartRent}</p>
                        <p>{nameRent}</p>
                        <p>{timeRent}</p>
                    </div>
                </div>
            </div>}

            {isClean && <div className="roomview">
                <div className="cleanHead">
                    <p className="container--removespace">Room</p>
                    <p>{numberRoom}</p>
                    <p className="container--removespace">{typeRoom}</p>
                </div>
                <div className="cleanTail">
                    <div>
                        <p>{timeStartClean}</p>
                        <p>{nameCLean}</p>
                        <p>{timeClean}</p>
                    </div>
                </div>
            </div>
            }
        </div>
    )
}