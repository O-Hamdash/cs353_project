import React from "react";
import Button from "./Button";

function SystemReportSection(){
    return(
        <div>
            <h2>System Report</h2>
            <div className={"generate-report"}>
                <h4>Click the button below to generate a system report containing the most important statistics on this platform.</h4>
                <Button text={"Generate System Report"} classname={"button--generate"}/>
            </div>
        </div>
    );
}

export default SystemReportSection;