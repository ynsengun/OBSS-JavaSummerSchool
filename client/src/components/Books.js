/* eslint-disable react/prop-types */
import React from "react";
import fetch from "isomorphic-unfetch";
import { toast } from "react-toastify";
import BookPaginationTable from "./BookPaginationTable";

import { isAuthenticated } from "../util/AuthenticationUtil";
import { checkResponse } from "../util/ResponseUtil";
import { handleRelationOperations, getRelations } from "../util/JoinTableUtil";

class Books extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      books: {},
      existInFavoriteList: [],
      existInReadList: [],
      currentPage: 0,
    };
  }

  getBooks = (search) => {
    // console.log("search parameters ", search);
    let type = "";
    let value = "";
    let urlType = "";
    if (search) {
      type = search.type;
      value = search.value;
      urlType = `/search-${type}`;
    }

    fetch(
      `http://localhost:8080/api/books${urlType}?` +
        (search
          ? new URLSearchParams({
              pageNumber: this.state.currentPage,
              [type]: value,
            })
          : new URLSearchParams({
              pageNumber: this.state.currentPage,
            })),
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: search ? "include" : "omit",
      }
    )
      .then((r) => checkResponse(r))
      .then((r) => r.json())
      .then((response) => {
        this.setState({ books: response }, () => {
          if (isAuthenticated()) {
            this.getFavoriteRelation();
            this.getReadRelation();
          }
        });
      })
      .catch(() => {
        toast.error("book fetch failed");
      });
  };

  getFavoriteRelation = async () => {
    let existance = await getRelations(this.state.books.content, "favorite");
    this.setState({ existInFavoriteList: existance });
  };

  getReadRelation = async () => {
    let existance = await getRelations(this.state.books.content, "read");
    this.setState({ existInReadList: existance });
  };

  handleFavorite = async (index, bookID) => {
    let newExistance = await handleRelationOperations(
      this.state.existInFavoriteList,
      index,
      bookID,
      "favorite"
    );
    this.setState({ existInFavoriteList: newExistance });
  };

  handleRead = async (index, bookID) => {
    let newExistance = await handleRelationOperations(
      this.state.existInReadList,
      index,
      bookID,
      "read"
    );
    this.setState({ existInReadList: newExistance });
  };

  changePageTo = (i) => {
    this.setState({ currentPage: i }, () => {
      this.getBooks(this.props.search);
    });
  };

  componentDidMount = () => {
    this.getBooks();
  };

  // eslint-disable-next-line react/no-deprecated
  componentWillReceiveProps = (nextProps) => {
    this.setState({ currentPage: 0 }, () => {
      this.getBooks(nextProps.search);
    });
  };

  render() {
    const { books } = this.state;
    return (
      <BookPaginationTable
        type={
          this.props.type
            ? this.props.type === "home"
              ? "home"
              : "entities"
            : "regular"
        }
        data={books}
        changePageTo={this.changePageTo}
        existInFavoriteList={this.state.existInFavoriteList}
        handleFavorite={this.handleFavorite}
        existInReadList={this.state.existInReadList}
        handleRead={this.handleRead}
      />
    );
  }
}

export default Books;
