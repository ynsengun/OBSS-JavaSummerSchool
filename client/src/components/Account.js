import React from "react";
import {
  Container,
  Form,
  Button,
  Grid,
  Card,
  Table,
  Placeholder,
} from "semantic-ui-react";
import { withRouter } from "react-router-dom";
import { toast } from "react-toastify";
import fetch from "isomorphic-unfetch";

import { checkResponse } from "../util/ResponseUtil";
import { getAuthId } from "../util/AuthenticationUtil";

class Account extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      user: {},
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
        .then(() => {
          toast.success(
            "Password is changed successfully.. Redirecting to home page..."
          );
          setTimeout(() => {
            // eslint-disable-next-line react/prop-types
            this.props.history.push("/");
          }, 1500);
        })
        .catch(() => {
          toast.error("Error on password change");
        });
    }
  };

  componentDidMount = () => {
    fetch(`http://localhost:8080/api/users/${getAuthId()}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
    })
      .then((r) => checkResponse(r))
      .then((r) => r.json())
      .then((response) => {
        this.setState({ user: response });
      })
      .catch(() => {
        toast.error("user detail fetch failed");
      });
  };

  render() {
    const { passwordError, passwordRepeatError } = this.state;
    const { username, createDate, updateDate } = this.state.user;

    return (
      <div>
        <Container>
          <Card fluid raised className="mt-5">
            <Card.Content textAlign="center">
              <Grid>
                <Grid.Row
                  columns="equal"
                  centered
                  style={{ paddingTop: "80px", paddingBottom: "80px" }}
                >
                  <Grid.Column width={6}>
                    <Placeholder style={{ height: 400 }}>
                      <Placeholder.Image />
                    </Placeholder>
                  </Grid.Column>
                  <Grid.Column width={1} />
                  <Grid.Column width={6} verticalAlign="middle">
                    <Table basic>
                      <Table.Body>
                        {tableRow("User Name", username)}
                        {tableRow("Register Date", createDate)}
                        {tableRow("Last Password Change", updateDate)}
                      </Table.Body>
                    </Table>
                  </Grid.Column>
                </Grid.Row>
              </Grid>
            </Card.Content>
          </Card>
          <Card fluid raised className="mt-5">
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
        </Container>
      </div>
    );
  }
}

export default withRouter(Account);

const tableRow = (f, s) => {
  return (
    <Table.Row>
      <Table.Cell style={{ fontWeight: "600" }}>{f}</Table.Cell>
      <Table.Cell>{s}</Table.Cell>
    </Table.Row>
  );
};
