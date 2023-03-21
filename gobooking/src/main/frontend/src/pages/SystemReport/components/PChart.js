import React from "react";
import {Cell, Legend, Pie, PieChart, Tooltip} from "recharts";

function PChart(props) {
    const COLORS = ["#8884d8", "#82ca9d", "#FFBB28", "#FF8042",/* "#AF19FF",*/ "#808080"];

    var customTooltip = ({ active, payload, label }) => {
        if (active) {
            return (
                <div
                    className="custom-tooltip"
                    style={{
                        backgroundColor: "#ffff",
                        padding: "5px",
                        border: "1px solid #cccc"
                    }}
                >
                    <label>{`${payload[0].name} : ${payload[0].value}%`}</label>
                </div>
            );
        }
    }

    return (
        <div className={"pchart"}>
            <h3>{props.title}</h3>
            <div className={"pchart--chart"}>
                {/*<PieChart width={730} height={250}>
              <Legend />
              <Pie data={data} dataKey="value" nameKey="name" cx="50%" cy="50%" innerRadius={60} outerRadius={80} fill="#82ca9d" label />
          </PieChart>*/}
                <PieChart width={300} height={300}>
                    <Pie
                        data={props.data}
                        color="#000000"
                        dataKey="value"
                        nameKey="name"
                        cx="50%"
                        cy="50%"
                        outerRadius={120}
                        fill="#8884d8"
                    >
                        {props.data.map((entry, index) => (
                            <Cell
                                key={`cell-${index}`}
                                fill={COLORS[index % COLORS.length]}
                            />
                        ))}
                    </Pie>
                    <Tooltip content={customTooltip} />
                    <Legend className={"pchart--legend"}/>
                </PieChart>
            </div>
        </div>
    );
}

export default PChart;