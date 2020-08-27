import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Register from "./Register";
import Login from "./Login";
import Home from "./Home";
import Library from "./Library";
import Navbar from "./Navbar";
import Footer from "./Footer";
import NotFound from "./NotFound";
import Book from "./Book";
import Discover from "./Discover";
import Entities from "./Entities";
import Account from "./Account";
import User from "./User";
import SpecialList from "./SpecialList";

import "../css/App.css";

export default function App() {
  return (
    <Router>
      <div className="app">
        <Navbar />

        {/* prettier-ignore */}
        <Switch>
          <Route exact path="/"><Home /></Route>
          <Route exact path="/library"><Library /></Route>
          <Route exact path="/library/:id"><Book /></Route>
          <Route exact path="/read-list"><SpecialList type="read" /></Route>
          <Route exact path="/favorite-list"><SpecialList type="favorite" /></Route>
          <Route exact path="/discover"><Discover /></Route>
          <Route exact path="/entities"><Entities /></Route>
          <Route exact path="/users/:id"><User /></Route>
          <Route exact path="/account"><Account /></Route>
          <Route exact path="/login"><Login /></Route>
          <Route exact path="/register"><Register /></Route>
          <Route path="*"><NotFound /></Route>
        </Switch>

        <Footer />
      </div>
    </Router>
  );
}
