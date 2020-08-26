import React from "react";
import { Card, Table, Label, Menu, Icon, Button } from "semantic-ui-react";

import { isAuthenticated, isUser, isAdmin } from "../util/Authentication";

export default function BookPaginationTable(props) {
  const {
    data,
    changePageTo,
    type,
    exitsInFavoriteList,
    handleFavorite,
  } = props;

  const Buttons = (index, bookID) => {
    const getFavoriteColor = () => {
      if (!exitsInFavoriteList || exitsInFavoriteList.length <= index) {
        return "green";
      }
      return exitsInFavoriteList[index] ? "youtube" : "green";
    };

    // if (type === "read-list") {
    //   return <Table.Cell collapsing></Table.Cell>;
    // }

    return (
      // {/* {isAuthenticated() && ( */}
      <Table.Cell collapsing>
        {/* {isUser() && ( */}
        <Button color="teal" size="small">
          <Icon name="info" className="m-0" />
        </Button>
        <Button
          color={getFavoriteColor()}
          size="small"
          onClick={() => {
            handleFavorite(index, bookID);
          }}
        >
          <Icon name="heart" className="m-0" />
        </Button>
        <Button color="green" size="small">
          <Icon name="book" className="m-0" />
        </Button>
        {/* )} */}
        {/* {isAdmin() && ( */}
        <Button color="yellow" size="small">
          <Icon name="edit" className="m-0" />
        </Button>
        <Button color="red" size="small">
          <Icon name="delete" className="m-0" />
        </Button>
        {/* )} */}
      </Table.Cell>
      // {/* )} */}
    );
  };

  return (
    <Card fluid raised>
      <Table striped>
        <Table.Header>
          <Table.Row>
            <Table.HeaderCell>Id</Table.HeaderCell>
            <Table.HeaderCell>Name</Table.HeaderCell>
            <Table.HeaderCell>Author</Table.HeaderCell>
            <Table.HeaderCell>Type</Table.HeaderCell>
            <Table.HeaderCell>Page</Table.HeaderCell>
            <Table.HeaderCell>Description</Table.HeaderCell>
            <Table.HeaderCell></Table.HeaderCell>
          </Table.Row>
        </Table.Header>

        <Table.Body>
          {data &&
            data.content &&
            data.content.map((value, index) => (
              <Table.Row>
                <Table.Cell>
                  <Label>{data.number * data.size + index + 1}</Label>
                </Table.Cell>
                <Table.Cell>{value.name}</Table.Cell>
                <Table.Cell>{value.author}</Table.Cell>
                <Table.Cell>{value.type}</Table.Cell>
                <Table.Cell>{value.pageNumber}</Table.Cell>
                <Table.Cell>{value.description}</Table.Cell>
                {Buttons(index, value.id)}
              </Table.Row>
            ))}
        </Table.Body>

        <Table.Footer>
          <Table.Row textAlign="center">
            <Table.HeaderCell colSpan="7">
              {/* {isAuthenticated() ? "7" : "6"} */}
              <Menu pagination secondary>
                <Menu.Item
                  as="a"
                  icon
                  disabled={data.first}
                  onClick={() => {
                    changePageTo(data.number - 1);
                  }}
                >
                  <Icon name="chevron left" />
                </Menu.Item>
                {[...Array(data.totalPages).keys()].map((value, index) => (
                  <Menu.Item
                    as="a"
                    active={data.number === index}
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
                  disabled={data.last}
                  onClick={() => {
                    changePageTo(data.number + 1);
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
  );
}
