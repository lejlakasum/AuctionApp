import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link, BrowserRouter } from "react-router-dom"
import Registration from "./pages/Authentication/Registration"
import Login from "./pages/Authentication/Login"
import './App.css';
import PrivateRoute from './Util/PrivateRoute';

function App() {

  const BASE_URL = "http://localhost:8081"

  return (
    <BrowserRouter>
      <Switch>
        <Route path="/login"> <Login baseUrl={BASE_URL} /> </Route>
        <Route path="/register"> <Registration baseUrl={BASE_URL} /> </Route>
      </Switch>
    </BrowserRouter>
  );
}

export default App;
