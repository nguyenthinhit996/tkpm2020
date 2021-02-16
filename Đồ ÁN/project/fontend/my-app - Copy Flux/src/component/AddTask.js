import React, { useContext, useState } from 'react'
import TodoAppContext from './TodoAppContext'


export default function Addtask({ initalValue }) {

    const {store, dispatch } = useContext(TodoAppContext);

    const [itemNew, setItemNew] = useState(initalValue);

    const valueInputText = (e) => {
        setItemNew(e.target.value);
    }

    const btnAddClicked = () => {
        let elementItem = { id: Math.floor(Math.random() * 100), name: itemNew, finish: true };

        dispatch({
            type: "add",
            payLoad:{
                items: [...store.items, elementItem]
            }
        })
        setItemNew(initalValue);
    }

    return (
        <div className="center">
            <h3>Add</h3>
            <input type="text" value={itemNew} onChange={valueInputText} />
            <button onClick={btnAddClicked}>Add</button>
        </div>
    )
}
