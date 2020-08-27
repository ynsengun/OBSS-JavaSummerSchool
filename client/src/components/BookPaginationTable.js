import React from "react";
import { useHistory } from "react-router-dom";
import { Card, Table, Label, Menu, Icon, Button } from "semantic-ui-react";

import { isAuthenticated, isUser } from "../util/Authentication";

export default function BookPaginationTable(props) {
  const {
    data,
    changePageTo,
    type,
    existInFavoriteList,
    handleFavorite,
    existInReadList,
    handleRead,
  } = props;

  const history = useHistory();

  // console.log("paginationnnnn data  ", data);
  // console.log("paginationnnnn fav  ", existInFavoriteList);
  // console.log("paginationnnnn read  ", existInReadList);

  const getButtons = (index, bookID) => {
    if (!isAuthenticated()) {
      return null;
    }

    const getFavoriteColor = () => {
      if (!existInFavoriteList || existInFavoriteList.length <= index) {
        return "green";
      }
      return existInFavoriteList[index] ? "youtube" : "green";
    };

    const getReadColor = () => {
      if (!existInReadList || existInReadList.length <= index) {
        return "green";
      }
      return existInReadList[index] ? "youtube" : "green";
    };

    const infoButton = (
      <Button
        color="teal"
        size="small"
        onClick={() => {
          history.push(`/library/${bookID}`);
        }}
      >
        <Icon name="info" className="m-0" />
      </Button>
    );

    const favButton = (
      <Button
        color={getFavoriteColor()}
        size="small"
        onClick={() => {
          handleFavorite(index, bookID);
        }}
      >
        <Icon name="heart" className="m-0" />
      </Button>
    );

    const readButton = (
      <Button
        color={getReadColor()}
        size="small"
        onClick={() => {
          handleRead(index, bookID);
        }}
      >
        <Icon name="book" className="m-0" />
      </Button>
    );

    const editButton = (
      <Button color="yellow" size="small">
        <Icon name="edit" className="m-0" />
      </Button>
    );

    const deleteButton = (
      <Button color="red" size="small">
        <Icon name="delete" className="m-0" />
      </Button>
    );

    if (type === "read") {
      return <Table.Cell collapsing>{readButton}</Table.Cell>;
    } else if (type === "favorite") {
      return <Table.Cell collapsing>{favButton}</Table.Cell>;
    } else if (type === "regular" && isUser()) {
      return (
        <Table.Cell collapsing>
          {infoButton}
          {favButton}
          {readButton}
        </Table.Cell>
      );
    } else if (type === "entities") {
      return (
        <Table.Cell collapsing>
          {editButton}
          {deleteButton}
        </Table.Cell>
      );
    }
  };

  const getColumnNumber = () => {
    if (isAuthenticated()) {
      if (type === "read" || type === "favorite") {
        return "8";
      }
      return 7;
    }
    return 6;
  };

  const getDateColumn = (index) => {
    if (type !== "read" && type !== "favorite") {
      return null;
    }
    return data.content[index].date;
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
            {type === "read" || type === "favorite" ? (
              <Table.HeaderCell>{`${type} date`}</Table.HeaderCell>
            ) : null}
            {isAuthenticated() ? <Table.HeaderCell></Table.HeaderCell> : null}
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
                {getDateColumn(index)}
                {getButtons(index, value.id)}
              </Table.Row>
            ))}
        </Table.Body>

        <Table.Footer>
          <Table.Row textAlign="center">
            <Table.HeaderCell colSpan={getColumnNumber()}>
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
