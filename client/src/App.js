import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Register from "./components/Register";
import Login from "./components/Login";
import Home from "./components/Home";

export default function BasicExample() {
  return (
    <Router>
      <div>
        <Switch>
          <Route exact path="/">
            <Home />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/login">
            <Login showRegisterLink="true" />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}
