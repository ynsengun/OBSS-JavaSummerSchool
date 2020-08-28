import React, { useState, useEffect } from "react";
import fetch from "isomorphic-unfetch";
import { toast } from "react-toastify";
import { Container, Header, Card } from "semantic-ui-react";

import PaginationTable from "./BookPaginationTable";

import { checkResponse } from "../util/ResponseUtil";
import { isAuthenticated, getAuthId } from "../util/AuthenticationUtil";
import { handleRelationOperations } from "../util/JoinTableUtil";

export default function SpecialList(props) {
  const [type, setType] = useState(props.type); // todo
  const [books, setBooks] = useState({});
  const [currentPage, setCurrentPage] = useState(0);
  const [existInFavoriteList, setExistInFavoriteList] = useState([]);
  const [existInReadList, setExistInReadList] = useState([]);

  const getBooks = () => {
    if (!isAuthenticated()) {
      return {};
    }
    fetch(
      `http://localhost:8080/api/${type}-list/${getAuthId()}?` +
        new URLSearchParams({ pageNumber: currentPage }),
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
        // toast.success(`There are ${response.totalElements} elements in total`);
        const newBooks = response.content.map((value) => ({
          ...value.book,
          date: value.date,
        }));
        response.content = newBooks;
        setBooks(response);
      })
      .catch((e) => {
        toast.error(`${type} book fetch failed`);
      });
  };

  const handleFavorite = async (index, bookID) => {
    let newExistance = await handleRelationOperations(
      existInFavoriteList,
      index,
      bookID,
      "favorite"
    );
    setExistInFavoriteList(newExistance);
  };

  const handleRead = async (index, bookID) => {
    let newExistance = await handleRelationOperations(
      existInReadList,
      index,
      bookID,
      "read"
    );
    setExistInReadList(newExistance);
  };

  const getExistanceArray = () => {
    if (!books || !books.content) return null;
    let arr = books.content.map(() => true);
    return arr;
  };

  const changePageTo = (i) => {
    setCurrentPage(i);
  };

  useEffect(() => {
    getBooks();
  }, [currentPage, type]);

  useEffect(() => {
    if (type === "favorite") {
      setExistInFavoriteList(getExistanceArray());
    } else {
      setExistInReadList(getExistanceArray());
    }
  }, [books]);

  useEffect(() => {
    setType(props.type);
  }, [props.type]);

  return (
    <Container>
      <Card fluid className="my-5" raised>
        <Card.Content>
          <Header size="huge" textAlign="center">
            your {type} list
          </Header>
        </Card.Content>
      </Card>
      {type === "read" ? (
        <PaginationTable
          data={books}
          changePageTo={changePageTo}
          type={type}
          existInReadList={existInReadList}
          handleRead={handleRead}
        />
      ) : (
        <PaginationTable
          data={books}
          changePageTo={changePageTo}
          type={type}
          existInFavoriteList={existInFavoriteList}
          handleFavorite={handleFavorite}
        />
      )}
    </Container>
  );
}
