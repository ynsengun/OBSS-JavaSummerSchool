export function checkResponse(response) {
  if (response.ok) {
    return response;
  }
  if (
    response.status === 401 ||
    response.status === 403 ||
    response.status === 500
  ) {
    return Promise.reject(new Error("An error occured"));
  }
}
