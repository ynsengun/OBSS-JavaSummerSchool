/* eslint-disable react/prop-types */
import React, { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import { Card, Table, Label, Menu, Icon, Button } from "semantic-ui-react";
import { toast } from "react-toastify";

import {
  isAuthenticated,
  isUser,
  notActivatedForThisSession,
} from "../util/AuthenticationUtil";
import { checkResponse } from "../util/ResponseUtil";
import { truncateWithDots } from "../util/StringUtil";

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
  const [deleteItem, setDeleteItem] = useState([]);
  const [modal, setModal] = useState("d-none");

  const getButtons = (index, bookID) => {
    if (!isAuthenticated() || type === "home") {
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
      <Button
        color="yellow"
        size="small"
        onClick={() => {
          history.push(`/library/${bookID}`, { edit: true });
        }}
      >
        <Icon name="edit" className="m-0" />
      </Button>
    );

    const deleteButton = (
      <Button
        color="red"
        size="small"
        onClick={() => {
          fetch(`http://localhost:8080/api/books/${bookID}`, {
            method: "DELETE",
            headers: {
              "Content-Type": "application/json",
            },
            credentials: "include",
          })
            .then((r) => checkResponse(r))
            .then(() => {
              toast.success("Book delete is successful");
              setDeleteItem([...deleteItem, bookID]);
            })
            .catch(() => {
              toast.error("Book delete failed");
            });
        }}
      >
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
    if (
      type !== "home" &&
      isAuthenticated() &&
      (isUser() || type === "entities")
    ) {
      if (type === "read" || type === "favorite") {
        return "8";
      }
      return 7;
    }
    return 6;
  };

  const getDateColumn = (index, isActive) => {
    if (type !== "read" && type !== "favorite") {
      return null;
    }
    return (
      <Table.Cell disabled={!isActive}>{data.content[index].date}</Table.Cell>
    );
  };

  const warningModal = () => {
    return (
      <div className={modal}>
        <div
          style={{
            height: "100vh",
            width: "1519px",
            position: "fixed",
            top: "0px",
            left: "0px",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            background: "rgba(0,0,0,0.7)",
          }}
        >
          <div
            style={{
              height: "350px",
              width: "800px",
              color: "white",
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
              borderRadius: "5px",
              fontSize: "25px",
            }}
            className={"shadow bg-warning"}
          >
            <p>Some books in your list are deleted</p>
            <p>You can still have them in your list</p>
            <p>
              But if you delete them you will not be able to reach them again
            </p>
          </div>
          <button
            style={{
              color: "white",
              fontSize: "20px",
              position: "relative",
              top: "-140px",
              left: "-40px",
              border: "0px",
              backgroundColor: "black",
            }}
            onClick={() => {
              setModal("d-none");
            }}
          >
            x
          </button>
        </div>
      </div>
    );
  };

  useEffect(() => {
    setModal("d-none");
    if (data && data.content) {
      let isInActive = data.content.find((book) => !book.active);
      if (isInActive && notActivatedForThisSession()) {
        setModal("d-block");
      }
    }
  }, [props.data]);

  return (
    <React.Fragment>
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
              {isAuthenticated() &&
              (isUser() || type === "entities") &&
              type !== "home" ? (
                <Table.HeaderCell></Table.HeaderCell>
              ) : null}
            </Table.Row>
          </Table.Header>

          <Table.Body>
            {data &&
              data.content &&
              data.content.map((value, index) => (
                <Table.Row
                  disabled={deleteItem.includes(value.id)}
                  key={value.id}
                >
                  <Table.Cell disabled={!value.active}>
                    <Label>{data.number * data.size + index + 1}</Label>
                  </Table.Cell>
                  <Table.Cell disabled={!value.active}>{value.name}</Table.Cell>
                  <Table.Cell disabled={!value.active}>
                    {value.author}
                  </Table.Cell>
                  <Table.Cell disabled={!value.active}>{value.type}</Table.Cell>
                  <Table.Cell disabled={!value.active}>
                    {value.pageNumber}
                  </Table.Cell>
                  <Table.Cell disabled={!value.active}>
                    {truncateWithDots(value.description)}
                  </Table.Cell>
                  {getDateColumn(index, value.active)}
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
                      key={index}
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
      {warningModal()}
    </React.Fragment>
  );
}
