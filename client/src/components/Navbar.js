import React from "react";
import { Menu, Container, Icon, Segment } from "semantic-ui-react";
import { NavLink } from "react-router-dom";

class Navbar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      activeItem: "home",
    };
  }
  handleItemClick = (e, { name }) => this.setState({ activeItem: name });
  render() {
    const { activeItem } = this.state;

    return (
      <Segment inverted>
        <Menu inverted pointing secondary size="huge">
          <Container>
            <Menu.Item
              name="home"
              active={activeItem === "home"}
              onClick={this.handleItemClick}
            >
              <Icon name="book" /> Home
            </Menu.Item>
            <Menu.Item
              name="messages"
              active={activeItem === "messages"}
              onClick={this.handleItemClick}
            />
            <Menu.Item
              name="friends"
              active={activeItem === "friends"}
              onClick={this.handleItemClick}
            />
            <Menu.Menu position="right">
              <Menu.Item
                name="login"
                active={activeItem === "login"}
                onClick={this.handleItemClick}
              >
                <NavLink to="/login">
                  Login
                </NavLink>
              </Menu.Item>
              <Menu.Item
                name="register"
                active={activeItem === "register"}
                onClick={this.handleItemClick}
              >
                <NavLink to="/register">
                  Register
                </NavLink>
              </Menu.Item>
            </Menu.Menu>
          </Container>
        </Menu>
      </Segment>
    );
  }
}

export default Navbar;
