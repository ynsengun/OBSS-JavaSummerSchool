/* eslint-disable react/prop-types */
import React, { useState } from "react";
import {
  Container,
  Card,
  Header,
  Dropdown,
  Form,
  Button,
} from "semantic-ui-react";

import Books from "./Books";

export default function Library(props) {
  const [search, setSearch] = useState(null);

  return (
    <Container>
      {props.exclude ? null : (
        <Card fluid className="my-5" raised>
          <Card.Content>
            <Header size="huge" textAlign="center">
              library
            </Header>
          </Card.Content>
        </Card>
      )}

      <Card fluid className="my-5" raised>
        <Card.Content className=" d-flex">
          <p
            style={{
              paddingTop: "10px",
              fontSize: "20px",
              fontWeight: "600",
              display: "inline-block",
              marginRight: "20px",
              marginLeft: props.exclude ? "35px" : "170px",
            }}
          >
            Filter By:
          </p>
          <Dropdown
            options={[
              { key: 1, text: "Name", value: "name" },
              { key: 2, text: "Author", value: "author" },
              { key: 3, text: "Type", value: "type" },
            ]}
            selection
            onChange={(e, data) => {
              let searchInput = document.getElementById("bookSearchDropdown");
              searchInput.value = data.value;
            }}
          />
          <input id="bookSearchDropdown" style={{ display: "none" }}></input>
          <Form className="pl-4 pt-1">
            <Form.Field>
              <input id="bookSearchInput" placeholder="Search For..." />
            </Form.Field>
          </Form>
          <Button
            style={{ height: "35px", marginTop: "6px", marginLeft: "100px" }}
            onClick={() => {
              let searchDropdown = document.getElementById(
                "bookSearchDropdown"
              );
              let dropVal = searchDropdown.value;
              let searchInput = document.getElementById("bookSearchInput");
              let inputVal = searchInput.value;
              setSearch({ type: dropVal, value: inputVal });
            }}
          >
            Search
          </Button>
        </Card.Content>
      </Card>
      <Books search={search} type={props.type} />
    </Container>
  );
}
