import axios from 'axios'

export function login(requestBody, callback, errorcallback) {

    axios.post("http://localhost:8081/login",
        {
            email: requestBody.email,
            password: requestBody.password
        })
        .then(response => {
            if (callback != null) {
                callback(response);
            }
        })
        .catch(error => {
            if (errorcallback != null) {
                errorcallback(error);
            }
        })
}

export function register(requestBody, callback, errorcallback) {

    axios.post("http://localhost:8081/user",
        {
            firstName: requestBody.firstName,
            lastName: requestBody.lastName,
            email: requestBody.email,
            password: requestBody.password,
            roleId: requestBody.roleId

        })
        .then(response => {
            if (callback != null) {
                callback(response);
            }
        })
        .catch(error => {
            if (errorcallback != null) {
                errorcallback(error);
            }
        })
}