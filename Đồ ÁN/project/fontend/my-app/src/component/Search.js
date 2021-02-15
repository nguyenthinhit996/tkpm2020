import React, { useEffect, useState } from 'react'

export default function Search({ intialQuery, filterClicked }) {

    // neu muon su dung ngay sau khi setQuery thi su dung useEffect
    const [query, setquery] = useState(intialQuery);

    useEffect(() => {
        filterClicked(query);
    }, [query])

    const btnClean_Click = () => {
        setquery(intialQuery);
        //filterClicked(intialQuery); useEffect instead of use direct
    }

    const txtFilter_Change = (e) => {
        setquery(e.target.value);
        console.log(query);
      //  filterClicked(e.target.value); useEffect instead of use direct
    }

    return (
        <div className="center">
            <h3> Search </h3>
            <input type="text" value={query} onChange={txtFilter_Change} />
            <button onClick={btnClean_Click}> Clear </button>
        </div>
    )
}
