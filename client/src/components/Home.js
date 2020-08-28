import React from "react";
import { Container, Card, Header, Icon, Placeholder } from "semantic-ui-react";

import Books from "./Books";

export default function Home() {
  return (
    <div>
      <div className="home-entry">
        <Container>
          <p id="home-header">Read For The Future</p>
          <div id="home-icons">
            <Header as="h2">
              <Icon name="plug" />
              <Header.Content>Uptime Guarantee</Header.Content>
            </Header>
            <Header as="h2">
              <Icon name="plug" />
              <Header.Content>Uptime Guarantee</Header.Content>
            </Header>
            <Header as="h2">
              <Icon name="plug" />
              <Header.Content>Uptime Guarantee</Header.Content>
            </Header>
          </div>
        </Container>
      </div>
      <Container className="mt-5">
        <Header textAlign="center" className="py-4" size="large">
          Boost Your Reading
        </Header>

        <Card.Group itemsPerRow={2}>
          <PlaceholderCard title="Use Book Lists" />
          <PlaceholderCard title="Discover New Books" />
        </Card.Group>

        <div className="mt-5">
          <Header textAlign="center" size="large" className="py-4">
            Start Looking For Your Favorite Book
          </Header>
          <Books />
        </div>
      </Container>
    </div>
  );
}

const PlaceholderCard = (props) => {
  return (
    <Card raised>
      <Card.Content>
        <Placeholder fluid className="mb-3">
          <Placeholder.Image rectangular />
        </Placeholder>
        {/* eslint-disable-next-line react/prop-types */}
        <Card.Header>{props.title}</Card.Header>
        <Card.Description>
          <Placeholder>
            <Placeholder.Paragraph>
              <Placeholder.Line />
              <Placeholder.Line />
              <Placeholder.Line />
              <Placeholder.Line />
              <Placeholder.Line />
            </Placeholder.Paragraph>
            <Placeholder.Paragraph>
              <Placeholder.Line />
              <Placeholder.Line />
              <Placeholder.Line />
            </Placeholder.Paragraph>
          </Placeholder>
        </Card.Description>
      </Card.Content>
    </Card>
  );
};
