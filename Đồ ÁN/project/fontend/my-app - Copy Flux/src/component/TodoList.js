import React, { useContext } from 'react'
import Tododel from './ToDoDel';
import TodoAppContext from './TodoAppContext'


export default function Todolist(pros) {

    const AppContext = useContext(TodoAppContext);

    return (
        <div className="center">
            <h3> To Do List</h3>
            <ul>
                {
                    AppContext.items.filter(value => value.name.toLowerCase().includes(AppContext.valueFilter.toLowerCase()))
                        .map(value => <Tododel key={value.id} value={value} />)
                }
            </ul>
        </div>
    )
}
