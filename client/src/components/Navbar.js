import React, { useState } from "react";
import { Menu, Container, Icon, Segment, Label } from "semantic-ui-react";
import { useHistory } from "react-router-dom";
import { toast } from "react-toastify";

function Navbar() {
  const [activeItem, setActiveItem] = useState("home");

  const history = useHistory();

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
          toast.success("Logout is successful");
          return;
        }
        if (r.status === 401 || r.status === 403 || r.status === 500) {
          return Promise.reject(new Error("An error occured"));
        }
      })
      .catch((e) => {
        toast.error("Logout is failed");
      });
  };

  return (
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
          <Menu.Menu position="right">
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
            <Menu.Item
              name="entities"
              active={activeItem === "entities"}
              onClick={handleItemClick}
            />
            <Menu.Item
              name="account"
              active={activeItem === "account"}
              onClick={handleItemClick}
            />
            <Menu.Item name="logout" onClick={handleLogout} />
            <Menu.Item>{`Welcome ...`}</Menu.Item>
          </Menu.Menu>
        </Container>
      </Menu>
    </Segment>
  );
}

export default Navbar;
