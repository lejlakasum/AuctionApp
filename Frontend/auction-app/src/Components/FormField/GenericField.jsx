import React from "react";

const GenericField = ({ type, id, label, className, placeholder, onChange, name }) => {
    return (
        <div>
            <label>{label}</label>
            <input type={type} className={className} id={id} placeholder={placeholder} onChange={onChange} name={name} />
        </div>
    );
}

export default GenericField;