import React from "react";
import { Menu, Container, Icon } from "semantic-ui-react";

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
      <div>
        <Menu pointing secondary size="huge">
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
              />
              <Menu.Item
                name="sign up"
                active={activeItem === "sign up"}
                onClick={this.handleItemClick}
              />
            </Menu.Menu>
          </Container>
        </Menu>
      </div>
    );
  }
}

export default Navbar;
