import React from "react";
import {
  Card,
  Table,
  Label,
  Menu,
  Icon,
  Placeholder,
  Button,
  Tab,
} from "semantic-ui-react";
import fetch from "isomorphic-unfetch";
import { toast } from "react-toastify";

class Books extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      books: {},
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
      .then((r) => {
        if (r.ok) {
          return r;
        }
        if (r.status === 401 || r.status === 403 || r.status === 500) {
          return Promise.reject(new Error("An error occured"));
        }
      })
      .then((r) => r.json())
      .then((response) => {
        toast.success(`There are ${response.totalElements} elements in total`);
        this.setState({ books: response });
      })
      .catch((e) => {
        toast.error("book fetch failed");
      });
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
            {books &&
              books.content &&
              books.content.map((value, index) => (
                <Table.Row>
                  <Table.Cell>
                    <Label>{books.number * books.size + index + 1}</Label>
                  </Table.Cell>
                  <Table.Cell>{value.name}</Table.Cell>
                  <Table.Cell>{value.author}</Table.Cell>
                  <Table.Cell>{value.type}</Table.Cell>
                  <Table.Cell>{value.pageNumber}</Table.Cell>
                  <Table.Cell>{value.description}</Table.Cell>
                  <Table.Cell collapsing>
                    <Button color="teal" size="small">
                      <Icon name="info" className="m-0" />
                    </Button>
                    <Button color="youtube" size="small">
                      <Icon name="heart" className="m-0" />
                    </Button>
                    <Button color="green" size="small">
                      <Icon name="book" className="m-0" />
                    </Button>
                    <Button color="yellow" size="small">
                      <Icon name="edit" className="m-0" />
                    </Button>
                    <Button color="red" size="small">
                      <Icon name="delete" className="m-0" />
                    </Button>
                  </Table.Cell>
                </Table.Row>
              ))}
          </Table.Body>

          <Table.Footer>
            <Table.Row textAlign="center">
              <Table.HeaderCell colSpan="7">
                <Menu pagination secondary>
                  <Menu.Item
                    as="a"
                    icon
                    disabled={books.first}
                    onClick={() => {
                      this.changePageTo(this.state.currentPage - 1);
                    }}
                  >
                    <Icon name="chevron left" />
                  </Menu.Item>
                  {[...Array(books.totalPages).keys()].map((value, index) => (
                    <Menu.Item
                      as="a"
                      active={books.number === index}
                      onClick={() => {
                        this.changePageTo(index);
                      }}
                    >
                      {index + 1}
                    </Menu.Item>
                  ))}
                  <Menu.Item
                    as="a"
                    icon
                    disabled={books.last}
                    onClick={() => {
                      this.changePageTo(this.state.currentPage + 1);
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
}

export default Books;
