import React, { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import {
  Container,
  Card,
  Form,
  Button,
  Table,
  Label,
  Menu,
  Icon,
} from "semantic-ui-react";
import { toast } from "react-toastify";

import { checkResponse } from "../util/ResponseUtil";

export default function UserPaginationTable(props) {
  const [search, setSearch] = useState(null);
  const [users, setUsers] = useState({});
  const [currentPage, setCurrentPage] = useState(0);
  const history = useHistory();
  const [deleteItem, setDeleteItem] = useState([]);

  const getUsers = () => {
    let value = "";
    let urlType = "";
    if (search) {
      value = search;
      urlType = `/search`;
    }

    fetch(
      `http://localhost:8080/api/users${urlType}?` +
        (search
          ? new URLSearchParams({
              pageNumber: currentPage,
              username: value,
            })
          : new URLSearchParams({
              pageNumber: currentPage,
            })),
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
      }
    )
      .then((r) => checkResponse(r))
      .then((r) => r.json())
      .then((response) => {
        // console.log(response);
        setUsers(response);
      })
      .catch((e) => {
        toast.error("user fetch failed");
      });
  };

  useEffect(() => {
    getUsers();
  }, [currentPage, search]);

  const changePageTo = (i) => {
    setCurrentPage(i);
  };

  const getButtons = (userID) => {
    return (
      <Table.Cell collapsing>
        <Button
          color="yellow"
          size="small"
          onClick={() => {
            history.push(`/users/${userID}`);
          }}
        >
          <Icon name="edit" className="m-0" />
        </Button>
        <Button
          color="red"
          size="small"
          onClick={() => {
            fetch(`http://localhost:8080/api/users/${userID}`, {
              method: "DELETE",
              headers: {
                "Content-Type": "application/json",
              },
              credentials: "include",
            })
              .then((r) => checkResponse(r))
              .then(() => {
                toast.success("User delete is successful");
                setDeleteItem([...deleteItem, userID]);
              })
              .catch((e) => {
                toast.error("User delete failed");
              });
          }}
        >
          <Icon name="delete" className="m-0" />
        </Button>
      </Table.Cell>
    );
  };

  return (
    <Container>
      <Card fluid className="my-5" raised>
        <Card.Content className=" d-flex">
          <Form className="pl-4 pt-1" style={{ marginLeft: "160px" }}>
            <Form.Field>
              <input
                id="userSearchInput"
                placeholder="Search For Username..."
              />
            </Form.Field>
          </Form>
          <Button
            style={{ height: "35px", marginTop: "6px", marginLeft: "100px" }}
            onClick={(e) => {
              let searchInput = document.getElementById("userSearchInput");
              let searchVal = searchInput.value;
              setCurrentPage(0);
              setSearch(searchVal);
            }}
          >
            Search
          </Button>
        </Card.Content>
      </Card>

      <Card fluid raised>
        <Table striped>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>Id</Table.HeaderCell>
              <Table.HeaderCell textAlign="center">User Name</Table.HeaderCell>
              <Table.HeaderCell></Table.HeaderCell>
            </Table.Row>
          </Table.Header>

          <Table.Body>
            {users &&
              users.content &&
              users.content.map((value, index) => (
                <Table.Row disabled={deleteItem.includes(value.id)}>
                  <Table.Cell>
                    <Label>{users.number * users.size + index + 1}</Label>
                  </Table.Cell>
                  <Table.Cell textAlign="center">{value.username}</Table.Cell>
                  {getButtons(value.id)}
                </Table.Row>
              ))}
          </Table.Body>

          <Table.Footer>
            <Table.Row textAlign="center">
              <Table.HeaderCell colSpan={3}>
                <Menu pagination secondary>
                  <Menu.Item
                    as="a"
                    icon
                    disabled={users.first}
                    onClick={() => {
                      changePageTo(users.number - 1);
                    }}
                  >
                    <Icon name="chevron left" />
                  </Menu.Item>
                  {[...Array(users.totalPages).keys()].map((value, index) => (
                    <Menu.Item
                      as="a"
                      active={users.number === index}
                      onClick={() => {
                        changePageTo(index);
                      }}
                    >
                      {index + 1}
                    </Menu.Item>
                  ))}
                  <Menu.Item
                    as="a"
                    icon
                    disabled={users.last}
                    onClick={() => {
                      changePageTo(users.number + 1);
                    }}
                  >
                    <Icon name="chevron right" />
                  </Menu.Item>
                </Menu>
              </Table.HeaderCell>
            </Table.Row>
          </Table.Footer>
        </Table>
      </Card>
    </Container>
  );
}
