import React from 'react'
import {login}  from './Api'


export default class Login extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            
        }

        this.login=this.login.bind(this)
    }

    login() {
        //TODO send data from input fields
        const request = {
            email: "roger@mail.com",
            password: "password"
        }
        login(request, (response) => {
            //TODO: save token to local storage
        }, (error) => {
            //TODO: handle error
        })
    };

    render() {
        return (
            <div>
                <button onClick={this.login}>Login</button>
            </div>
        )
    }
}