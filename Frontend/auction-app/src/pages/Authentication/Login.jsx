import React from "react";
import GenericField from "../../Components/FormField/GenericField"
import { handleFieldChange } from "../index.jsx"
import axios from "axios"
import { useHistory, useLocation } from "react-router-dom"
import "../../index.css"
import PageName from "../HeaderFooter/PageName"


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
            <PageName pageName="login" />
            <div className={"form-box"}>
                <div className={"form"}>
                    <div className={"form-title"}>
                        LOGIN
                </div>
                    <GenericField id={"email"} name={"email"} label={"Email"} className={"input-field"} type={"text"} onChange={(e) => handleFieldChange(e, setUser)} />
                    <GenericField id={"password"} name={"password"} label={"Password"} className={"input-field"} type={"password"} onChange={(e) => handleFieldChange(e, setUser)} />
                    <small>
                        <label className={"validation-error"}>{errorMessage}</label>
                    </small>
                    <button type={"button"} className={"btn-login"} onClick={() => handleLoginClick(user)} >LOGIN</button>
                </div>
            </div>
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