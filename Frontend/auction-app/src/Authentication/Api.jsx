import axios from 'axios'
import routes from '../Util/routes'

export function login(requestBody, callback, errorcallback) {

    axios.post(routes.base + routes.login,
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

    axios.post(routes.base + routes.user,
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