import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Register from './Register';
import Login from './Login'

export default function BasicExample() {
  return (
    <Router>
      <div>
        <Switch>
          <Route exact path="/">
            <Register />
          </Route>
          <Route path="/login">
            <Login showRegisterLink='true'/>
          </Route>
        </Switch>
      </div>
    </Router>
  );
}
