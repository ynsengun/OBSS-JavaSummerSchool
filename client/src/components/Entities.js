import React, { useState } from "react";
import {
  Container,
  Card,
  Grid,
  Menu,
  Segment,
  Form,
  Button,
} from "semantic-ui-react";
import Library from "./Library";
import { toast } from "react-toastify";

import { isNumber } from "../util/Math";
import { checkResponse } from "../util/Response";

export default function Entities() {
  const [activeItem, setActiveItem] = useState("books");
  const [newBook, setNewBook] = useState({
    name: "",
    author: "",
    type: "",
    pageNumber: "",
    description: "",
  });

  const handleChange = (e) => {
    const { value, name } = e.currentTarget;

    setNewBook({
      ...newBook,
      [name]: value,
    });
  };

  const formField = (bookName, bookValue) => {
    return (
      <Form.Field>
        <label>{bookName}:</label>
        <Form.Input
          type="text"
          name={bookName}
          required
          value={bookValue}
          onChange={handleChange}
        />
      </Form.Field>
    );
  };

  const bookEntities = () => {
    return (
      <React.Fragment>
        <Card fluid raised>
          <Card.Content>
            <Form
              onSubmit={() => {
                if (!isNumber(newBook.pageNumber) || newBook.pageNumber < 1) {
                  toast.error(
                    "Page number should be a number and bigger than 1"
                  );
                } else {
                  fetch(`http://localhost:8080/api/books/`, {
                    method: "POST",
                    headers: {
                      "Content-Type": "application/json",
                    },
                    body: JSON.stringify(newBook),
                    credentials: "include",
                  })
                    .then((r) => checkResponse(r))
                    .then((r) => r.json())
                    .then((response) => {
                      toast.success("book save is successful");
                      setNewBook({
                        name: "",
                        author: "",
                        type: "",
                        pageNumber: "",
                        description: "",
                      });
                    })
                    .catch((e) => {
                      toast.error("book save is failed");
                    });
                }
              }}
              onReset={(e) => {
                e.preventDefault();
                setNewBook({
                  name: "",
                  author: "",
                  type: "",
                  pageNumber: "",
                  description: "",
                });
              }}
            >
              {formField("name", newBook.name)}
              {formField("author", newBook.author)}
              {formField("type", newBook.type)}
              {formField("pageNumber", newBook.pageNumber)}
              {formField("description", newBook.description)}

              <Button.Group fluid>
                <Button type="reset" color="teal">
                  Reset
                </Button>
                <Button type="submit">Submit</Button>
              </Button.Group>
            </Form>
          </Card.Content>
        </Card>
        <Library exclude={true} type="entities" />
      </React.Fragment>
    );
  };

  const handleItemClick = (e, data) => {
    setActiveItem(data.name);
  };

  return (
    <Container>
      <Card fluid raised className="mt-5">
        <Card.Content>
          <Grid>
            <Grid.Column width={3}>
              <Menu fluid vertical tabular>
                <Menu.Item
                  name="books"
                  active={activeItem === "books"}
                  onClick={handleItemClick}
                />
                <Menu.Item
                  name="users"
                  active={activeItem === "users"}
                  onClick={handleItemClick}
                />
              </Menu>
            </Grid.Column>

            <Grid.Column stretched width={13}>
              <Segment>
                {activeItem === "books" ? bookEntities() : "users"}
              </Segment>
            </Grid.Column>
          </Grid>
        </Card.Content>
      </Card>
    </Container>
  );
}
