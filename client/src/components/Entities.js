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
import { toast } from "react-toastify";

import Library from "./Library";
import UserPaginationTable from "./UserPaginationTable";

import { isPositiveInteger } from "../util/MathUtil";
import { checkResponse } from "../util/ResponseUtil";

export default function Entities() {
  const [activeItem, setActiveItem] = useState("books");

  const [newBook, setNewBook] = useState({
    name: "",
    author: "",
    type: "",
    pageNumber: "",
    description: "",
  });

  const [newUser, setNewUser] = useState({
    username: "",
    password: "",
  });

  const bookFormField = (bookName, bookValue) => {
    return (
      <Form.Field>
        <label>{bookName}:</label>
        <Form.Input
          type="text"
          name={bookName}
          required
          value={bookValue}
          onChange={(e) => {
            const { value, name } = e.currentTarget;

            setNewBook({
              ...newBook,
              [name]: value,
            });
          }}
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
                if (!isPositiveInteger(newBook.pageNumber)) {
                  toast.error("Page number should be an integer bigger than 1");
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
              {bookFormField("name", newBook.name)}
              {bookFormField("author", newBook.author)}
              {bookFormField("type", newBook.type)}
              {bookFormField("pageNumber", newBook.pageNumber)}
              {bookFormField("description", newBook.description)}

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

  const userFormField = (userName, userValue, type) => {
    return (
      <Form.Field>
        <label>{userName}:</label>
        <Form.Input
          type={type}
          name={userName}
          required
          value={userValue}
          onChange={(e) => {
            const { value, name } = e.currentTarget;

            setNewUser({
              ...newUser,
              [name]: value,
            });
          }}
        />
      </Form.Field>
    );
  };

  const userEntities = () => {
    return (
      <React.Fragment>
        <Card fluid raised>
          <Card.Content>
            <Form
              onSubmit={() => {
                console.log(newUser);
                if (
                  newUser.username.length < 4 ||
                  newUser.password.length < 4
                ) {
                  toast.error(
                    "Username and password should be bigger than 3 characters"
                  );
                } else {
                  fetch(`http://localhost:8080/api/users`, {
                    method: "POST",
                    headers: {
                      "Content-Type": "application/json",
                    },
                    body: JSON.stringify(newUser),
                  })
                    .then((r) => checkResponse(r))
                    .then((r) => r.json())
                    .then((response) => {
                      toast.success("user save is successful");
                      setNewUser({
                        username: "",
                        password: "",
                      });
                    })
                    .catch((e) => {
                      toast.error("User save is failed");
                    });
                }
              }}
              onReset={(e) => {
                e.preventDefault();
                setNewUser({
                  username: "",
                  password: "",
                });
              }}
            >
              {userFormField("username", newUser.username, "email")}
              {userFormField("password", newUser.password, "password")}

              <Button.Group fluid>
                <Button type="reset" color="teal">
                  Reset
                </Button>
                <Button type="submit">Submit</Button>
              </Button.Group>
            </Form>
          </Card.Content>
        </Card>
        <UserPaginationTable />
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
                {activeItem === "books" ? bookEntities() : userEntities()}
              </Segment>
            </Grid.Column>
          </Grid>
        </Card.Content>
      </Card>
    </Container>
  );
}
