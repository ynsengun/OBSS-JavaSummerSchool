import React, { useState } from "react";
import {
  Container,
  Grid,
  Divider,
  Form,
  Button,
  Card,
  Header,
} from "semantic-ui-react";
import { useHistory, Link } from "react-router-dom";
import fetch from "isomorphic-unfetch";
import { toast } from "react-toastify";

import { saveAuth } from "../util/Authentication";
import { checkResponse } from "../util/Response";

const Login = (props) => {
  const history = useHistory();

  const [usernamePassword, setUsernamePassword] = useState({
    username: "",
    password: "",
  });

  const [usernamePasswordError, setUsernamePasswordError] = useState({
    username: null,
    password: null,
  });

  const handleChange = (e) => {
    const { value, name } = e.currentTarget;

    setUsernamePassword({
      ...usernamePassword,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const { username, password } = usernamePassword;

    if (username.length < 5 || username.length > 255) {
      setUsernamePasswordError({
        ...usernamePasswordError,
        username: "Please check username",
      });
      return;
    }

    if (password.length < 4 || password.length > 255) {
      setUsernamePasswordError({
        ...usernamePasswordError,
        password: "Please check password",
      });
      return;
    }

    const formData = new URLSearchParams();
    formData.append("username", username);
    formData.append("password", password);
    fetch("http://localhost:8080/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: formData,
      credentials: "include",
    })
      .then((r) => checkResponse(r))
      .then(() =>
        fetch("http://localhost:8080/api/users/auth", {
          method: "GET",
          credentials: "include",
        })
          .then((r) => checkResponse(r))
          .then((r) => r.json())
          .then((response) => {
            saveAuth(response);
            toast.success("Login is successful... Redirecting to home page...");
            setTimeout(() => {
              history.push("/");
            }, 1500);
          })
      )
      .catch((e) => {
        toast.error(e.message);
      });
  };

  return (
    <div>
      <Header textAlign="center" size="huge" className="mt-5">
        Login To System
      </Header>
      <Container>
        <Grid>
          <Grid.Row columns="equal" centered>
            <Grid.Column width={8}>
              <Card fluid className="mt-5">
                <Card.Content>
                  <Form
                    onSubmit={handleSubmit}
                    onReset={(e) => {
                      e.preventDefault();
                      setUsernamePassword({ username: "", password: "" });
                    }}
                  >
                    <Form.Field>
                      <label>User Name:</label>
                      <Form.Input
                        type="email"
                        name="username"
                        required
                        value={usernamePassword.username}
                        onChange={handleChange}
                        error={usernamePasswordError.username}
                      />
                    </Form.Field>

                    <Form.Field>
                      <label>Password:</label>
                      <Form.Input
                        type="password"
                        name="password"
                        required
                        value={usernamePassword.password}
                        onChange={handleChange}
                        error={usernamePasswordError.password}
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
                  <Link to="/register">Don't have an account? Register</Link>
                </Card.Content>
              </Card>
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Container>
    </div>
  );
};

export default Login;
