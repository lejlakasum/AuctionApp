import React from "react";
import GenericField from "../../Components/FormField/GenericField"
import { handleFieldChange } from "../index.jsx"
import axios from "axios"
import { useHistory, useLocation } from "react-router-dom"


const Login = props => {
    const [user, setUser] = React.useState({
        email: "",
        password: ""
    })
    const [errorMessage, setErrorMessage] = React.useState("")

    let url = props.baseUrl + "/login"
    let location = useLocation();
    let history = useHistory();
    let { from } = location.state || {
        from: { pathname: "/" }
    };

    return (
        <div>
            <GenericField id={"email"} name={"email"} label={"Email"} type={"text"} onChange={(e) => handleFieldChange(e, setUser)} />
            <GenericField id={"password"} name={"password"} label={"Password"} type={"text"} onChange={(e) => handleFieldChange(e, setUser)} />
            <label>{errorMessage}</label>
            <button type={"button"} onClick={() => handleLoginClick(user)} >LOGIN</button>
        </div>
    )
    function handleLoginClick(user) {

        axios.post(url, user)
            .then(response => {
                document.cookie = "token=" + response.data.token + "; max-age=600;"
                history.push(from)
            })
            .catch(error => {
                setErrorMessage("Invalid username or password")
            })
    }

}

export default Login