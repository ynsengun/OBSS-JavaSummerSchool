import React from "react";
import fetch from "isomorphic-unfetch";
import { toast } from "react-toastify";
import BookPaginationTable from "./BookPaginationTable";

import { getAuthId } from "../util/Authentication";
import { checkResponse } from "../util/Response";
import { handleRelationOperations, getRelations } from "../util/JoinTable";

class Books extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      books: {},
      exitsInFavoriteList: [],
      exitsInReadList: [],
      currentPage: 0,
    };
  }

  getBooks = () => {
    fetch(
      "http://localhost:8080/api/books?" +
        new URLSearchParams({
          pageNumber: this.state.currentPage,
        }),
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    )
      .then((r) => checkResponse(r))
      .then((r) => r.json())
      .then((response) => {
        toast.success(`There are ${response.totalElements} elements in total`);
        this.setState({ books: response });
      })
      .catch((e) => {
        toast.error("book fetch failed");
      })
      .finally(() => {
        this.getFavoriteRelation();
      });
  };

  // fetches the favorite relations between fetched books and authenticated user
  getFavoriteRelation = async () => {
    let existance = await getRelations(this.state.books.content, "favorite");
    this.setState({ exitsInFavoriteList: existance });
  };

  handleFavorite = async (index, bookID) => {
    let newExistance = await handleRelationOperations(
      this.state.exitsInFavoriteList,
      index,
      bookID,
      "favorite"
    );
    this.setState({ exitsInFavoriteList: newExistance });
  };

  handleRead = async (index, bookID) => {
    let newExistance = await handleRelationOperations(
      this.state.exitsInReadList,
      index,
      bookID,
      "read"
    );
    this.setState({ exitsInReadList: newExistance });
  };

  changePageTo = (i) => {
    this.setState({ currentPage: i }, this.getBooks);
  };

  componentDidMount = () => {
    this.getBooks();
  };

  render() {
    const { books } = this.state;
    return (
      <BookPaginationTable
        data={books}
        changePageTo={this.changePageTo}
        exitsInFavoriteList={this.state.exitsInFavoriteList}
        handleFavorite={this.handleFavorite}
      />
    );
  }
}

export default Books;
