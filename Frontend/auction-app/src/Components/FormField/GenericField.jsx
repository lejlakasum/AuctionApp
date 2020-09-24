import React from "react";

const GenericField = ({ label, validationMessage, ...props }) => {
    return (
        <div>
            <label>{label}</label>
            <input {...props} />
            <label>{validationMessage}</label>
        </div>
    );
}

export default GenericField;