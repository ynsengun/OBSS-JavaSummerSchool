import React, { useState, useEffect } from "react";
import fetch from "isomorphic-unfetch";
import { toast } from "react-toastify";
import PaginationTable from "./BookPaginationTable";

import { checkResponse } from "../util/Response";
import { isAuthenticated, getAuthId } from "../util/Authentication";
import { Container } from "semantic-ui-react";

export default function FavoriteList(props) {
  const [books, setBooks] = useState({});
  const [currentPage, setCurrentPage] = useState(0);

  const getBooks = () => {
    if (!isAuthenticated()) {
      return {};
    }
    fetch(
      `http://localhost:8080/api/favorite-list/${getAuthId()}?` +
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
        toast.success(`There are ${response.totalElements} elements in total`);
        const books = response.content.map((value) => ({
          ...value.book,
          date: value.date,
        }));
        response.content = books;
        setBooks(response);
      })
      .catch((e) => {
        toast.error("favorite book fetch failed");
      });
  };

  const changePageTo = (i) => {
    setCurrentPage(i);
  };

  useEffect(() => {
    console.log("inside useEffect");
    getBooks();
  }, [currentPage]);

  return (
    <Container>
      <PaginationTable data={books} changePageTo={changePageTo} />
    </Container>
  );
}
