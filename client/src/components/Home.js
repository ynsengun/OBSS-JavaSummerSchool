import React from "react";

import Navbar from "./Navbar";
import { Container, Grid, Image } from "semantic-ui-react";

class Home extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <div>
        <Navbar />
        <Container>
          <Grid>
            <Grid.Row>
              <Grid.Column width={4}>
                <Image src="1.png" size="big" />
              </Grid.Column>
            </Grid.Row>
          </Grid>
        </Container>
      </div>
    );
  }
}

export default Home;
