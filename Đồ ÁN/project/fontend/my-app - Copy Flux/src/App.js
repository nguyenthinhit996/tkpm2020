import logo from './logo.svg';
import './App.css';
import { useEffect, useReducer, useState } from 'react';
import Todolist from './component/TodoList';
import Addtask from './component/AddTask';
import Search from './component/Search';
import TodoAppContext from "./component/TodoAppContext"
import reducer from './component/reducer'

function App() {

  // use flux intead Of
  // const [items, setItems] = useState([]);
  // const [valueFilter, setvalueFilter] = useState('');

  const initalState = {
    valueFilter: '',
    items: []
  }

  

  const [store, dispatch] = useReducer(reducer, initalState);

  useEffect(() => {
    // setItems([{ id: "1", name: "hix1", finish: true },
    // { id: "2", name: "hix2", finish: true },
    // { id: "34", name: "hixrr", finish: false },
    // { id: "3", name: "hix3", finish: true }]);

    const sendIntial = {
      type: 'inital',
      payLoad: {
        valueFilter: '',
        items: [{ id: "1", name: "hix1", finish: true },
        { id: "2", name: "hix2", finish: true },
        { id: "34", name: "hixrr", finish: false },
        { id: "3", name: "hix3", finish: true }]
      }
    }
    dispatch(sendIntial);
  }, [])


  return (
    <div className="App">
      <TodoAppContext.Provider value={{ store, dispatch }}>
        <Search intialQuery="" />
        <Todolist />
        <Addtask initalValue='' />
      </TodoAppContext.Provider>
    </div>
  );
}

export default App;
