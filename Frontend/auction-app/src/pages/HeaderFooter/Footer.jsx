import React from "react";
import "../../index.css"

const Footer = props => {
    return (
        <div className="footer">
            <div>
                <div className="footer-text">AUCTION</div>
                <ul>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Tearms and Conditions</a></li>
                    <li><a href="#">Privacy and Policy</a></li>
                </ul>
            </div>
            <div>
                <div className="footer-text">GET IN TOUCH</div>
                <label className="footer-contact">Call Us at +123 797-567-2535</label>
                <br />
                <label className="footer-contact">support@auction.com</label>
            </div>
        </div>
    )
}

export default Footer;