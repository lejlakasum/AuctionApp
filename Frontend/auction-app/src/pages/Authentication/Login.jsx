import React from "react";
import GenericField from "../../Components/FormField/GenericField"
import { handleFieldChange } from "../index.jsx"
import routes from "../../Util/routes"
import axios from "axios"


const Login = props => {
    const [user, setUser] = React.useState({
        email: "",
        password: ""
    })

    return (
        <div>
            <GenericField id={"email"} name={"email"} label={"Email"} type={"text"} onChange={(e) => handleFieldChange(e, user, setUser)} />
            <GenericField id={"password"} name={"password"} label={"Password"} type={"text"} onChange={(e) => handleFieldChange(e, user, setUser)} />
            <button type={"button"} onClick={() => handleLoginClick(user)} >LOGIN</button>
        </div>
    )
    function handleLoginClick(user) {

        axios.post(routes.base.path + routes.login.path, user)
            .then(response => {
                //TODO Handle response, save jwt
                console.log(response)
            })
            .catch(error => {
                //TODO handle error
                console.log(error)
            })
    }

}

export default Login