export function saveAuth(info) {
  const { id, username } = info;

  let isUser = false,
    isAdmin = false;
  if (
    info.roles[0].name === "ROLE_USER" ||
    (info.roles.length === 2 && info.roles[1].name === "ROLE_USER")
  ) {
    isUser = true;
  }
  if (
    info.roles[0].name === "ROLE_ADMIN" ||
    (info.roles.length === 2 && info.roles[1].name === "ROLE_ADMIN")
  ) {
    isAdmin = true;
  }

  const authJson = { id, username, isUser, isAdmin };

  localStorage.setItem("authInfo", JSON.stringify(authJson));
}

export function cleanAuth() {
  localStorage.removeItem("authInfo");
}

export function isAuthenticated() {
  let auth = localStorage.getItem("authInfo");
  return auth != null;
}

export function getAuthId() {
  let auth = localStorage.getItem("authInfo");
  let authJson = JSON.parse(auth);
  return authJson.id;
}

export function getAuthName() {
  let auth = localStorage.getItem("authInfo");
  let authJson = JSON.parse(auth);
  return authJson.username;
}

export function isAdmin() {
  let auth = localStorage.getItem("authInfo");
  let authJson = JSON.parse(auth);
  return authJson.isAdmin;
}

export function isUser() {
  let auth = localStorage.getItem("authInfo");
  let authJson = JSON.parse(auth);
  return authJson.isUser;
}
