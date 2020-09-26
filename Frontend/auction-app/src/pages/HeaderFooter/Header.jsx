import React from "react";
import "../../index.css"

const Header = props => {
    return (
        <div>
            <div className="header header-top">
                <div className="social-networks"></div>
                <div className="login-acccount">
                    <a className="header-text" href="/login">Login</a>
                    <label className="header-or">or</label>
                    <a className="header-text" href="/register">Create an Account</a>
                </div>
            </div>
            <div className="header header-middle">
                <div className="header-title">AUCTION</div>
            </div>
        </div>
    )
}

export default Header;