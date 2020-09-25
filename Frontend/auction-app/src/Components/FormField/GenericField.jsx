import React from "react";
import "../../index.css"

const GenericField = ({ label, validationMessage, ...props }) => {
    return (
        <div className={"generic-field"}>
            <label>{label}</label>
            <input {...props} />
            <small>
                <label className={"validation-error"}>{validationMessage}</label>
            </small>

        </div>
    );
}

export default GenericField;