import React from "react";
import pp from "../images/pp.jpg"
function ProfileCard () {
    return (
        <div className={"pcard-container"}>
            <div className={"pcard"}>
                <div className={"pp-container"}>
                    <img src={pp} className={"pp"}/>
                </div>
                <h2>
                    Mustafa Yetgin
                </h2>
                <p className={"pcard--role"}>
                    General Admin
                </p>
            </div>
        </div>
    );
}

export default ProfileCard;