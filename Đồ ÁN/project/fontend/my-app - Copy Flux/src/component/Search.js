import React, { useContext, useEffect, useState } from 'react'
import TodoAppContext from './TodoAppContext'

export default function Search({ intialQuery }) {

    const {store, dispatch} = useContext(TodoAppContext)

    // neu muon su dung ngay sau khi setQuery thi su dung useEffect
    const [term, setterm] = useState(intialQuery);

    useEffect(() => {
        dispatch({
            type: 'filter',
            payLoad: {
              valueFilter: term
            }
        })
    }, [term])

    const btnClean_Click = () => {
        setterm(intialQuery);
        //filterClicked(intialQuery); useEffect instead of use direct
    }

    const txtFilter_Change = (e) => {
        setterm(e.target.value);
        console.log(term);
      //  filterClicked(e.target.value); useEffect instead of use direct
    }

    return (
        <div className="center">
            <h3> Search </h3>
            <input type="text" value={term} onChange={txtFilter_Change} />
            <button onClick={btnClean_Click}> Clear </button>
        </div>
    )
}
