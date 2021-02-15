import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import Todolist from './component/TodoList';
import Addtask from './component/AddTask';
import Search from './component/Search';
import TodoAppContext from "./component/TodoAppContext"

function App() {


  const [items, setItems] = useState([]);


  useEffect(() => {
    setItems([{ id: "1", name: "hix1", finish: true },
    { id: "2", name: "hix2", finish: true },
    { id: "34", name: "hixrr", finish: false },
    { id: "3", name: "hix3", finish: true }]);
  }, [])

  const [valueFilter, setvalueFilter] = useState('');

  const btnAddClicked = (elementItem) => {
    setItems([...items, elementItem])
  }

  return (
    <div className="App">
      <TodoAppContext.Provider value = { {items, setItems, valueFilter, setvalueFilter} }>
        <Search intialQuery="" />
        <Todolist/>
        <Addtask initalValue = ''/>
      </TodoAppContext.Provider>
    </div>
  );
}

export default App;
