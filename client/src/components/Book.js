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

import { checkResponse } from "../util/Response";

export default function Book(props) {
  const [book, setBook] = useState({});
  const [description, setDescription] = useState("");

  const location = useLocation();
  const edit = location.state;

  const history = useHistory();
  const path = history.location.pathname;
  const bookID = path.substring(9);

  useEffect(() => {
    fetch(`http://localhost:8080/api/books/${bookID}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
    })
      .then((r) => checkResponse(r))
      .then((r) => r.json())
      .then((response) => {
        setBook(response);
        setDescription(response.description);
      })
      .catch((e) => {
        toast.error("book detail fetch failed");
      });
  }, []);

  const editButton = () => {
    return (
      <Button
        onClick={() => {
          fetch(`http://localhost:8080/api/books/${bookID}`, {
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({ description }),
            credentials: "include",
          })
            .then((r) => checkResponse(r))
            .then((r) => r.json())
            .then((response) => {
              setBook(response);
              setDescription(response.description);
              toast.success("book update is successful");
            })
            .catch((e) => {
              toast.error("book update is failed failed");
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
        <Table.Cell style={{ fontWeight: "600" }}>Description</Table.Cell>
        <Table.Cell>
          <TextArea
            fluid
            value={description}
            onChange={(e, data) => {
              setDescription(e.currentTarget.value);
            }}
          ></TextArea>
        </Table.Cell>
      </Table.Row>
    );
  };

  return (
    <Container>
      <Header textAlign="center" size="huge" className="mt-5">
        BOOK DETAILS
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
                    {tableRow("Author", book.author)}
                    {tableRow("Name", book.name)}
                    {tableRow("Page Number", book.pageNumber)}
                    {tableRow("Type", book.type)}
                    {edit
                      ? editSegment()
                      : tableRow("Description", book.description)}
                    {tableRow("First create date", book.createDate)}
                    {tableRow("Last update date", book.updateDate)}
                  </Table.Body>
                </Table>
              </Grid.Column>
            </Grid.Row>
          </Grid>
          {edit ? editButton() : null}
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
