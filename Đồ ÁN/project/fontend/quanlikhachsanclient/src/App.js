import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useRouteMatch,
  useParams
} from "react-router-dom";
import Login from './components/pages/Login';
import Navigation from './components/layout/Navigation';
import Staffreceptionindex from './components/pages/StaffReceptionIndex';
import Chooseoptionuser from './components/layout/ChooseOptionUser';


function App() {
  return (
    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>
    <Router>
      <div>
        <Switch>
        <Route path="/Chooseoptionuser">
            <Chooseoptionuser />
          </Route>
          <Route path="/navigation">
            <Navigation />
          </Route>
          <Route path="/Staff">
            <Staffreceptionindex />
          </Route>
          <Route path="/">
            <Login />
          </Route>

        

        </Switch>
      </div>
    </Router>
  );
}

export default App;
