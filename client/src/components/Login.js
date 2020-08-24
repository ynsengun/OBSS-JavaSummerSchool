import React from "react";
import { Container, Grid, Divider } from "semantic-ui-react";
import { Link } from "react-router-dom";

class Login extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    const { showRegisterLink } = this.props;

    return (
      <div>
        <Container>
          <Grid>
            <Grid.Row columns="equal" centered>
              <Grid.Column width={8}>
                <p>Login</p>
                <Divider />
                {showRegisterLink && <Link to="/">Register</Link>}
              </Grid.Column>
            </Grid.Row>
          </Grid>
        </Container>
      </div>
    );
  }
}

export default Login;
