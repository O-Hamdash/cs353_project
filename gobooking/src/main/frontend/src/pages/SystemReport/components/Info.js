import React from "react";
import '../style.css';

function Info(props){
    return (
        <div className={"info--wrap"}>
            <div className={"info"}>
                <h3>Most important stats</h3>
                <table>
                    <tr>
                        <th className={"cell1"}></th>
                        <th>Today</th>
                        <th>Last Month</th>
                    </tr>
                    <tr>
                        <th>Ads</th>
                        <td>{props.adsToday}</td>
                        <td>{props.adsMonth}</td>
                    </tr>
                    <tr>
                        <th>Bookings</th>
                        <td>{props.bookingsToday}</td>
                        <td>{props.bookingsMonth}</td>
                    </tr>
                </table>
            </div>
        </div>
    );
}

export default Info;