import React, { useState, useEffect } from "react";
import { Menu, Container, Icon, Segment, Sticky } from "semantic-ui-react";
import { useHistory } from "react-router-dom";
import { toast } from "react-toastify";

import {
  cleanAuth,
  isAuthenticated,
  isUser,
  isAdmin,
  getAuthName,
} from "../util/AuthenticationUtil";

function Navbar() {
  const [activeItem, setActiveItem] = useState("home");
  const [auth, setAuth] = useState(isAuthenticated());

  const history = useHistory();

  useEffect(() => {
    const unListen = history.listen(() => {
      setAuth(isAuthenticated());
    });
    return () => {
      unListen();
    };
  }, []);

  const handleItemClick = (e, { name }) => {
    if (name === "home") {
      history.push("/");
    } else {
      history.push(`/${name.replace(" ", "-")}`);
    }
    setActiveItem(name);
  };

  const handleLogout = () => {
    fetch("http://localhost:8080/logout", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
    })
      .then((r) => {
        if (r.ok) {
          cleanAuth();
          toast.success("Logout is successful... Redirecting to home page...");
          setTimeout(() => {
            history.push("/");
          }, 1500);
          return r;
        } else {
          return Promise.reject(new Error("An error occured"));
        }
      })
      .catch((e) => {
        toast.error("Logout is failed");
      });
  };

  return (
    <div className="fixed-top">
      <Segment inverted>
        <Menu inverted pointing secondary size="large">
          <Container>
            <Menu.Item
              name="home"
              active={activeItem === "home"}
              onClick={handleItemClick}
            >
              <Icon name="book" /> Home
            </Menu.Item>
            {isAuthenticated() && isUser() ? (
              <React.Fragment>
                <Menu.Item
                  name="library"
                  active={activeItem === "library"}
                  onClick={handleItemClick}
                />
                <Menu.Item
                  name="read list"
                  active={activeItem === "read list"}
                  onClick={handleItemClick}
                />
                <Menu.Item
                  name="favorite list"
                  active={activeItem === "favorite list"}
                  onClick={handleItemClick}
                />
                <Menu.Item
                  name="discover"
                  active={activeItem === "discover"}
                  onClick={handleItemClick}
                />
              </React.Fragment>
            ) : null}
            <Menu.Menu position="right">
              {!isAuthenticated() ? (
                <React.Fragment>
                  <Menu.Item
                    name="login"
                    active={activeItem === "login"}
                    onClick={handleItemClick}
                  />
                  <Menu.Item
                    name="register"
                    active={activeItem === "register"}
                    onClick={handleItemClick}
                  />
                </React.Fragment>
              ) : null}
              {isAuthenticated() && isAdmin() ? (
                <Menu.Item
                  name="entities"
                  active={activeItem === "entities"}
                  onClick={handleItemClick}
                />
              ) : null}
              {isAuthenticated() ? (
                <React.Fragment>
                  <Menu.Item
                    name="account"
                    active={activeItem === "account"}
                    onClick={handleItemClick}
                  />
                  <Menu.Item name="logout" onClick={handleLogout} />
                  <Menu.Item>{`Welcome ${getAuthName()}`}</Menu.Item>
                </React.Fragment>
              ) : null}
            </Menu.Menu>
          </Container>
        </Menu>
      </Segment>
    </div>
  );
}

export default Navbar;
