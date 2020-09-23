import React from "react";

const GenericField = ({ label, ...props }) => {
    return (
        <div>
            <label>{label}</label>
            <input {...props} />
        </div>
    );
}

export default GenericField;