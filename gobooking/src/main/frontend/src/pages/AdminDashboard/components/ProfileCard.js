import React from "react";
import pp from "../images/pp.jpg"
function ProfileCard () {
    return (
        <div className={"pcard-container"}>
            <body className={"pcard"}>
            <img src={pp} className={"pp"}/>
            <h2>
                Mustafa Yetgin
            </h2>
            <p className={"pcard--role"}>
                General Admin
            </p>
            </body>
        </div>
    );
}

export default ProfileCard;