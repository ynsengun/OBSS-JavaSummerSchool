import React from "react";
import { Container, Grid, Table, Label, Menu, Icon } from "semantic-ui-react";
import fetch from "isomorphic-unfetch";
import { toast } from "react-toastify";

class Dashboard extends React.Component {
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
          pageSize: 4,
        }),
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
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
      <div>
        <Container>
          <Grid>
            <Grid.Row columns="equal" centered>
              <Grid.Column width={12}>
                <Table celled>
                  <Table.Header>
                    <Table.Row>
                      <Table.HeaderCell>Id</Table.HeaderCell>
                      <Table.HeaderCell>Name</Table.HeaderCell>
                      <Table.HeaderCell>Author</Table.HeaderCell>
                      <Table.HeaderCell>Type</Table.HeaderCell>
                      <Table.HeaderCell>Description</Table.HeaderCell>
                    </Table.Row>
                  </Table.Header>

                  <Table.Body>
                    {books &&
                      books.content &&
                      books.content.map((value, index) => (
                        <Table.Row>
                          <Table.Cell>
                            <Label ribbon>
                              {books.number * books.size + index + 1}
                            </Label>
                          </Table.Cell>
                          <Table.Cell>{value.name}</Table.Cell>
                          <Table.Cell>{value.author}</Table.Cell>
                          <Table.Cell>{value.type}</Table.Cell>
                          <Table.Cell>{value.description}</Table.Cell>
                        </Table.Row>
                      ))}
                  </Table.Body>

                  <Table.Footer>
                    <Table.Row>
                      <Table.HeaderCell colSpan="5">
                        <Menu floated="right" pagination>
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
                          {[...Array(books.totalPages).keys()].map(
                            (value, index) => (
                              <Menu.Item
                                as="a"
                                active={books.number === index}
                                onClick={() => {
                                  this.changePageTo(index);
                                }}
                              >
                                {index + 1}
                              </Menu.Item>
                            )
                          )}
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
              </Grid.Column>
            </Grid.Row>
          </Grid>
        </Container>
      </div>
    );
  }
}

export default Dashboard;
