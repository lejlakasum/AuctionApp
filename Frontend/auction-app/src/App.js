import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link, BrowserRouter } from "react-router-dom"
import Registration from "./pages/Authentication/Registration"
import Login from "./pages/Authentication/Login"
import './App.css';
import Footer from "./pages/HeaderFooter/Footer"
import Header from "./pages/HeaderFooter/Header"

function App() {

  const BASE_URL = "http://localhost:8081"

  return (
    <div>
      <Header />
      <BrowserRouter>
        <Switch>
          <Route path="/login"> <Login baseUrl={BASE_URL} /> </Route>
          <Route path="/register"> <Registration baseUrl={BASE_URL} /> </Route>
        </Switch>
      </BrowserRouter>
      <Footer />
    </div>

  );
}

export default App;
