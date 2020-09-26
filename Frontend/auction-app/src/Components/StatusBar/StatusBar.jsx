import React from "react";
import "../../index.css"

const StatusBar = ({ statusMessage, ...props }) => {
    return (
        <div {...props}>
            <label>
                {statusMessage}
            </label>
        </div>
    );
}

export default StatusBar;