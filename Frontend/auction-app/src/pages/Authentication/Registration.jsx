import React from "react";
import GenericField from "../../Components/FormField/GenericField"
import { handleFieldChange } from "../index.jsx"
import axios from "axios"


const Registration = props => {

    const [user, setUser] = React.useState({
        firstName: "",
        lastName: "",
        email: "",
        password: ""
    })

    const [validation, setValidation] = React.useState({
        firstName: "",
        lastName: "",
        email: "",
        password: ""
    })

    const [message, setMessage] = React.useState("")

    let url = props.baseUrl + "/user"

    return (
        <div>
            <div>
                {message}
            </div>
            <GenericField id={"firstName"} name={"firstName"} label={"First Name"} type={"text"} onChange={(e) => handleFieldChange(e, setUser)} validationMessage={validation.firstName} />
            <GenericField id={"lastName"} name={"lastName"} label={"Last Name"} type={"text"} onChange={(e) => handleFieldChange(e, setUser)} validationMessage={validation.lastName} />
            <GenericField id={"email"} name={"email"} label={"Email"} type={"text"} onChange={(e) => handleFieldChange(e, setUser)} validationMessage={validation.email} />
            <GenericField id={"password"} name={"password"} label={"Password"} type={"password"} onChange={(e) => handleFieldChange(e, setUser)} validationMessage={validation.password} />
            <button type={"button"} onClick={(e) => handleRegisterClick(e, user)} >REGISTER</button>
        </div>
    )

    function handleRegisterClick(e, user) {

        if (validate(user)) {
            axios.post(url, user)
                .then(response => {
                    setMessage("You are registered")
                })
                .catch(error => {
                    //TODO handle error
                    setMessage("User with given email already exists")
                })
        }
    }

    function validate(user) {

        var isValid = true;
        var validationMessage = {
            firstName: "",
            lastName: "",
            email: "",
            password: ""
        }

        if (!user.firstName) {
            validationMessage.firstName = "First Name is required"
            isValid = false;
        }

        if (!user.lastName) {
            validationMessage.lastName = "Last Name is required"
            isValid = false;
        }

        if (!user.email || !user.email.includes("@")) {
            validationMessage.email = "Invalid email"
            isValid = false;
        }

        if (!user.password || user.password.length < 5) {
            validationMessage.password = "Password must be longer than 5 letters"
            isValid = false;
        }
        console.log(validationMessage)
        setValidation(validationMessage)
        console.log(validation)

        return isValid
    }

}

export default Registration