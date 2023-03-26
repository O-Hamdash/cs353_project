import React from 'react';

function Button (props) {
    return (
        <button className={props.classname}>{props.text}</button>
    );
}

export default Button;