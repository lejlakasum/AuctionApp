import React from "react";
import "../../index.css"

const PageName = props => {
    return (
        <div className="page-name">
            {props.pageName}
        </div>
    )
}

export default PageName;