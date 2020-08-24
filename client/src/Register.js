import React from "react";
import { Container, Form, Button, Grid, Divider } from "semantic-ui-react";
import {Link} from 'react-router-dom';

class Register extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      counter: 1,
      username: "",
      password: "",
      passwordRepeat: "",
      usernameError: null,
      passwordError: null,
      passwordRepeatError: null
    };
  }

  handleChange = (e) => {
    const { value, name } = e.currentTarget;

    this.setState({ [name]: value });
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.setState({ counter: this.state.counter + 1 });

    const {username, password, passwordRepeat} = this.state;

    if(username.length < 5 || username.length > 255){
      this.setState({
        usernameError: "Please check username"
      })
      return;
    }
    
    if(password.length < 5 || password.length > 255){
      this.setState({
        passwordError: "Please check password"
      })
      return;
    }
    
    if(password !== passwordRepeat){
      this.setState({
        passwordError: "Please check passwords",
        passwordRepeatError: "Please check passwords"
      })
      return;
    }
  };

  render() {
    const {
      counter,
      usernameError,
      passwordError,
      passwordRepeatError,
    } = this.state;

    return (
      <div>
        <Container>
          <Grid>
            <Grid.Row columns="equal" centered>
              <Grid.Column width={8}>
                <p>Counter: {counter}</p>

                <Form
                  onSubmit={this.handleSubmit}
                  onReset={(e) => {
                    e.preventDefault();
                    this.setState({
                      counter: 0,
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
                <Divider/>
                <Link to='/login'>Login</Link>
              </Grid.Column>
            </Grid.Row>
          </Grid>
        </Container>
      </div>
    );
  }
}

export default Register;
