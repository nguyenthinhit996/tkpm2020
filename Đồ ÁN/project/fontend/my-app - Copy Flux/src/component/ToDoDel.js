import React, { useContext } from 'react'
import TodoAppContext from './TodoAppContext'

export default function Tododel({ value }) {

    const {store, dispatch} = useContext(TodoAppContext);

    const btnDetele_Clicked = () => {
        var itemsDel = store.items.map(item => item.id === value.id ? { ...item, finish: false } : item);
        dispatch({
            type: "del",
            payLoad:{
                items: itemsDel
            }
        })
        
    }

    return (
        <li className={value.finish ? "highlight" : ""}>
            {value.name}
            {value.finish ? <button onClick={btnDetele_Clicked}> Delete</button> : <button disabled>Delete</button>}
        </li>
    )
}
