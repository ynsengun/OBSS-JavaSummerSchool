import React from "react";
import {
  Container,
  Form,
  Button,
  Grid,
  Divider,
  Card,
  Header,
} from "semantic-ui-react";
import { Link, withRouter } from "react-router-dom";
import { toast } from "react-toastify";
import fetch from "isomorphic-unfetch";

import { checkResponse } from "../util/ResponseUtil";

class Register extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      username: "",
      password: "",
      passwordRepeat: "",
      usernameError: null,
      passwordError: null,
      passwordRepeatError: null,
    };
  }

  handleChange = (e) => {
    const { value, name } = e.currentTarget;

    this.setState({ [name]: value });
  };

  handleSubmit = (e) => {
    e.preventDefault();

    const { username, password, passwordRepeat } = this.state;
    let errorOccured = false;

    this.setState({
      usernameError: null,
      passwordError: null,
      passwordRepeatError: null,
    });

    if (username.length < 5 || username.length > 255) {
      this.setState({
        usernameError: "Please check username",
      });
      errorOccured = true;
    }

    if (password.length < 4 || password.length > 255) {
      this.setState({
        passwordError: "Please check password",
      });
      errorOccured = true;
    } else if (password !== passwordRepeat) {
      this.setState({
        passwordError: "Passwords does not match",
        passwordRepeatError: "Passwords does not match",
      });
      errorOccured = true;
    }

    if (!errorOccured) {
      fetch("http://localhost:8080/api/users", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      })
        .then((r) => checkResponse(r))
        .then((r) => r.json())
        .then((response) => {
          toast.success(
            "Registration is successful.. Redirecting to login page..."
          );
          setTimeout(() => {
            this.props.history.push("/login");
          }, 1500);
        })
        .catch((e) => {
          toast.error("This username is already exist");
        });
    }
  };

  render() {
    const { usernameError, passwordError, passwordRepeatError } = this.state;

    return (
      <div>
        <Header textAlign="center" size="huge" className="mt-5">
          Register To System
        </Header>
        <Container>
          <Grid>
            <Grid.Row columns="equal" centered>
              <Grid.Column width={8}>
                <Card fluid className="mt-5">
                  <Card.Content>
                    <Form
                      onSubmit={this.handleSubmit}
                      onReset={(e) => {
                        e.preventDefault();
                        this.setState({
                          username: "",
                          password: "",
                          passwordRepeat: "",
                        });
                      }}
                    >
                      <Form.Field>
                        <label>User Name:</label>
                        <Form.Input
                          type="email"
                          name="username"
                          required
                          value={this.state.username}
                          onChange={this.handleChange}
                          error={usernameError}
                        />
                      </Form.Field>

                      <Form.Field>
                        <label>Password:</label>
                        <Form.Input
                          type="password"
                          name="password"
                          required
                          value={this.state.password}
                          onChange={this.handleChange}
                          error={passwordError}
                        />
                      </Form.Field>

                      <Form.Field>
                        <label>Password (repeat):</label>
                        <Form.Input
                          type="password"
                          name="passwordRepeat"
                          required
                          value={this.state.passwordRepeat}
                          onChange={this.handleChange}
                          error={passwordRepeatError}
                        />
                      </Form.Field>

                      <Button.Group fluid>
                        <Button type="reset" color="teal">
                          Reset
                        </Button>
                        <Button type="submit">Submit</Button>
                      </Button.Group>
                    </Form>
                    <Divider />
                    <Link to="/login">Have an account? Login</Link>
                  </Card.Content>
                </Card>
              </Grid.Column>
            </Grid.Row>
          </Grid>
        </Container>
      </div>
    );
  }
}

export default withRouter(Register);
