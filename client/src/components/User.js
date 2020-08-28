import React, { useEffect, useState } from "react";
import {
  Container,
  Card,
  Placeholder,
  Grid,
  Table,
  Header,
  Button,
  TextArea,
} from "semantic-ui-react";
import { useHistory, useLocation } from "react-router-dom";
import { toast } from "react-toastify";

import { checkResponse } from "../util/ResponseUtil";

export default function Book() {
  const [user, setUser] = useState({});
  const [password, setPassword] = useState("");

  const history = useHistory();
  const path = history.location.pathname;
  const userID = path.substring(7);

  useEffect(() => {
    fetch(`http://localhost:8080/api/users/${userID}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
    })
      .then((r) => checkResponse(r))
      .then((r) => r.json())
      .then((response) => {
        setUser(response);
        setPassword(response.password);
      })
      .catch((e) => {
        toast.error("user detail fetch failed");
      });
  }, []);

  const editButton = () => {
    return (
      <Button
        onClick={() => {
          fetch(`http://localhost:8080/api/users/${userID}`, {
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
              setUser(response);
              setPassword(response.password);
              toast.success("user update is successful");
            })
            .catch((e) => {
              toast.error("user update is failed failed");
            });
        }}
      >
        Submit
      </Button>
    );
  };

  const editSegment = () => {
    return (
      <Table.Row>
        <Table.Cell style={{ fontWeight: "600" }}>Password</Table.Cell>
        <Table.Cell>
          <TextArea
            fluid
            value={password}
            onChange={(e, data) => {
              setPassword(e.currentTarget.value);
            }}
          ></TextArea>
        </Table.Cell>
      </Table.Row>
    );
  };

  return (
    <Container>
      <Header textAlign="center" size="huge" className="mt-5">
        USERS DETAILS
      </Header>
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
                    {tableRow("User Name", user.username)}
                    {editSegment()}
                    {tableRow("First create date", user.createDate)}
                    {tableRow("Last update date", user.updateDate)}
                  </Table.Body>
                </Table>
              </Grid.Column>
            </Grid.Row>
          </Grid>
          {editButton()}
        </Card.Content>
      </Card>
    </Container>
  );
}

const tableRow = (f, s) => {
  return (
    <Table.Row>
      <Table.Cell style={{ fontWeight: "600" }}>{f}</Table.Cell>
      <Table.Cell>{s}</Table.Cell>
    </Table.Row>
  );
};
