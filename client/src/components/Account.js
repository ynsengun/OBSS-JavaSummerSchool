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
import { checkResponse } from "../util/Response";
import { getAuthId } from "../util/Authentication";

class Account extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      password: "",
      passwordRepeat: "",
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

    const { password, passwordRepeat } = this.state;
    let errorOccured = false;

    this.setState({
      passwordError: null,
      passwordRepeatError: null,
    });

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
      fetch(`http://localhost:8080/api/users/${getAuthId()}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ password }),
        credentials: "include",
      })
        .then((r) => checkResponse(r))
        .then((r) => r.json())
        .then((response) => {
          toast.success(
            "Password is changed successfully.. Redirecting to home page..."
          );
          setTimeout(() => {
            this.props.history.push("/");
          }, 1500);
        })
        .catch((e) => {
          toast.error("Error on password change");
        });
    }
  };

  render() {
    const { passwordError, passwordRepeatError } = this.state;

    return (
      <div>
        <Header textAlign="center" size="huge" className="mt-5">
          Account Setting
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
                          password: "",
                          passwordRepeat: "",
                        });
                      }}
                    >
                      <Form.Field>
                        <label>New Password:</label>
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
                        <label>New Password (repeat):</label>
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

export default withRouter(Account);
