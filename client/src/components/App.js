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
import ReadList from "./ReadList";
import FavoriteList from "./FavoriteList";
import Discover from "./Discover";
import Entities from "./Entities";
import Account from "./Account";
import User from "./User";

import "../css/App.css";

export default function App() {
  return (
    <Router>
      <div className="app">
        <Navbar />

        {/* prettier-ignore */}
        <Switch>
          <Route exact path="/"><Home /></Route>
          <Route path="/library"><Library /></Route>
          <Route path="/library/:id"><Book /></Route>
          <Route path="/read-list"><ReadList /></Route>
          <Route path="/favorite-list"><FavoriteList /></Route>
          <Route path="/discover"><Discover /></Route>
          <Route path="/entities"><Entities /></Route>
          <Route path="/users/:id"><User /></Route>
          <Route path="/account"><Account /></Route>
          <Route path="/login"><Login /></Route>
          <Route path="/register"><Register /></Route>
          <Route path="*"><NotFound /></Route>
        </Switch>

        <Footer />
      </div>
    </Router>
  );
}
