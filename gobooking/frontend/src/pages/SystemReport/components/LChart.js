import React from "react";
import {CartesianGrid, Legend, Line, LineChart, Tooltip, XAxis, YAxis} from "recharts";

function LChart(props) {

    let secondLine = <Line type="monotone" dataKey={props.line2} stroke="#82ca9d"  />;
    if (props.oneline){
        secondLine = null;
    }

    return (
        <div className={"chart"}>
            <h3>{props.title}</h3>
            <LineChart
                width={props.width}
                height={props.length}
                data={props.data}
                //margin={{ top: 5, right: 20, left: 10, bottom: 5 }}
            >
                <XAxis dataKey="name" />
                <YAxis max={props.max}/>
                <Tooltip />
                <Legend />
                <CartesianGrid stroke="#f5f5f5" />
                <Line type="monotone" dataKey={props.line1} stroke="#8884d8"  />
                {secondLine}
            </LineChart>

        </div>
    );
}

export default LChart;