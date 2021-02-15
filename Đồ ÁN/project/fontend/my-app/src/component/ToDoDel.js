import React from 'react'

export default function Tododel({ value, toDoListbtnDelete_Clicked }) {

    const toDoDelbtnDelete_Clicked = () => {
        toDoListbtnDelete_Clicked(value.id);
    }

    return (
        <li className={value.finish ? "highlight" : ""}>
            {value.name}
            {value.finish ? <button onClick={toDoDelbtnDelete_Clicked}> Delete</button> : <button disabled>Delete</button>}
        </li>
    )
}
