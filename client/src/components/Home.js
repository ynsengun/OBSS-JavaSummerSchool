import React, { useEffect } from "react";
import { Container, Card, Header, Icon, Placeholder } from "semantic-ui-react";
import gsap from "gsap";

import Books from "./Books";

export default function Home() {
  useEffect(() => {
    if (!localStorage.getItem("animPlayed")) {
      let tl = gsap.timeline();
      console.log("here in useEffect");
      tl.from(".anim1", { x: -500, duration: 0.8, ease: "power2" });
      tl.from(".anim2", { x: -500, duration: 0.8, ease: "power2" }, "-=0.4");
      tl.from(".anim3", { x: -500, duration: 0.8, ease: "power2" }, "-=0.4");
      tl.from(".anim4", { opacity: 0, y: 15, duration: 0.8 }, "-=0.3");
      localStorage.setItem("animPlayed", true);
    }
  }, []);

  return (
    <div>
      <div className="home-entry">
        <Container>
          <p id="home-header" className="anim4">
            Read For The Future
          </p>
          <div id="home-icons">
            <Header as="h2" className="anim1">
              <Icon name="plug" />
              <Header.Content>Uptime Guarantee</Header.Content>
            </Header>
            <Header as="h2" className="anim2">
              <Icon name="plug" />
              <Header.Content>Uptime Guarantee</Header.Content>
            </Header>
            <Header as="h2" className="anim3">
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
          <Books type="home" />
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
