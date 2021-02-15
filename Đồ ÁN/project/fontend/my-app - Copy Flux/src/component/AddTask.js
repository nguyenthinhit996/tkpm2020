import React, { useState } from 'react'

export default function Addtask({ initalValue, btnAddFucntion }) {

    const [itemNew, setItemNew] = useState(initalValue);

    const valueInputText = (e) => {
        setItemNew(e.target.value);
    }

    const btnAddClicked = () => {
        let elementItem = { id: Math.floor(Math.random() * 100), name: itemNew, finish: true };
        btnAddFucntion(elementItem);
        setItemNew('');
    }

    return (
        <div className="center">

            <h3>Add</h3>
            <input type="text" value={itemNew} onChange={valueInputText} />
            <button onClick={btnAddClicked}>Add</button>
        </div>
    )
}
