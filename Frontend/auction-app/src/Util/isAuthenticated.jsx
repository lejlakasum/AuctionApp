import getToken from "./getToken"

function isAuthenticated() {

    return getToken("token") != null
}

export default isAuthenticated;