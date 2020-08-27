import { toast } from "react-toastify";
import { getAuthId } from "./Authentication";
import { checkResponse } from "./Response";

// fetches the favorite/read relations between fetched books and authenticated user
export const getRelations = async (books, listName) => {
  let arr = [];
  for (const element of books) {
    try {
      let r = await fetch(
        `http://localhost:8080/api/${listName}-list?` +
          new URLSearchParams({
            userID: getAuthId(),
            bookID: element.id,
          }),
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        }
      );
      let rChecked = await checkResponse(r);
      let json = await rChecked.json();
      arr.push(json.exist);
    } catch (e) {
      toast.error(`${listName} relation fetch failed`);
    }
  }
  return arr;
};

// add/delete the book to/from the list
export const handleRelationOperations = async (
  existanceList,
  index,
  bookID,
  listName
) => {
  if (!existanceList || existanceList.length <= index) {
    return;
  }

  let fetchMethod = existanceList[index] ? "DELETE" : "POST";
  try {
    let r = await fetch(`http://localhost:8080/api/${listName}-list`, {
      method: fetchMethod,
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userID: getAuthId(),
        bookID: bookID,
      }),
      credentials: "include",
    });
    await checkResponse(r);
  } catch (e) {
    if (existanceList[index]) {
      toast.error(`${listName} relation delete failed`);
    } else {
      toast.error(`${listName} relation add failed`);
    }
  }
  let arr = [...existanceList];
  arr[index] = !arr[index];
  return arr;
};
