import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import Todolist from './component/TodoList';
import Addtask from './component/AddTask';
import Search from './component/Search';

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


  const btnFilterClicked = (valueFilter) => {
    setvalueFilter(valueFilter);
  }

  const btnDetele_Clicked = (id) => {
    // var itemsDel = items.filter((value) => 
    //   value.id !== id
    // );

    var itemsDel = items.map(value => value.id === id ? { ...value, finish: false } : value);
    setItems(itemsDel);
  }

  return (
    <div className="App">
      <Search intialQuery="" filterClicked={btnFilterClicked} />
      <Todolist list={items} valueFilter={valueFilter} btnDetele_Clicked={btnDetele_Clicked} />
      <Addtask btnAddFucntion={btnAddClicked} />
    </div>
  );
}

export default App;
