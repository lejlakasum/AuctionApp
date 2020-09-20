import React from 'react'
import {register}  from './Api'


export default class Login extends React.Component {

    constructor(props) {
        super(props)
        this.state = {

        }

        this.registration=this.registration.bind(this)
    }

    registration() {
        //TODO send data from input fields
        const request = {
            firstName: "Simona",
            lastName: "Halep",
            email: "simona@mail.com",
            password: "password",
            roleId: 1
        }
        register(request, (response) => {
            //TODO: Add success message
        }, (error) => {
            //TODO Handle error
        })
    };

    render() {
        return (
            <div>
                <button onClick={this.registration}>Register</button>
            </div>
        )
    }
}