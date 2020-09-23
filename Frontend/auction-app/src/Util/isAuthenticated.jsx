import getToken from "./getToken"

function isAuthenticated() {

    var token = getToken("token")

    if (token != null) {
        return true;
    }

    return false;
}

export default isAuthenticated;