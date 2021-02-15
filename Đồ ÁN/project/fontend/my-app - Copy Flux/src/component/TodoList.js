import React, { useState } from 'react'
import Tododel from './ToDoDel';

export default function Todolist({ list, valueFilter, btnDetele_Clicked }) {

    // const item = [{ id: "1", name: "hix1", finish: true },
    // { id: "2", name: "hix2", finish: true },
    // { id: "34", name: "hixrr", finish: false },
    // { id: "3", name: "hix3", finish: true }]


    return (
        <div className="center">
            <h3> To Do List</h3>
            <ul>
                {
                    list.filter(value => value.name.toLowerCase().includes(valueFilter.toLowerCase()))
                        .map(value => <Tododel key={value.id} value={value} toDoListbtnDelete_Clicked={btnDetele_Clicked} />)
                }
                {/* <li>Item 1</li>
                <li>Item 2</li>
                <li>Item 3</li> */}
            </ul>
            {/* <button onClick={btnAddClicked}>Button Add</button> */}
        </div>
    )
}
