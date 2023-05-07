import React from "react";

import './style.css'
import Info from "../AdminDashboard/components/Info";
import LChart from "./components/LChart";
import PChart from "./components/PChart";

let data = [
    {name: "Jan", ads: 30, bookings: 40},
    {name: "Feb",ads: 52, bookings: 46},
    {name: "Mar" , ads: 77, bookings: 91},
    {name: "Apr", ads: 85, bookings: 93},
    {name: "May", ads: 64, bookings: 83},
    {name: "Jun", ads: 73, bookings: 134},
    {name: "Jul", ads: 27, bookings: 116},
    {name: "Aug", ads: 84, bookings: 123},
    {name: "Sep", ads: 88, bookings: 98},
    {name: "Oct", ads: 100, bookings: 90},
    {name: "Nov", ads: 123, bookings: 88},
    {name: "Dec", ads: 85, bookings: 99}
]

let userData = [
    {name: "Jan", users: 30},
    {name: "Feb",users: 52},
    {name: "Mar" , users: 77},
    {name: "Apr", users: 85},
    {name: "May", users: 90},
    {name: "Jun", users: 96},
    {name: "Jul", users: 100},
    {name: "Aug", users: 103},
    {name: "Sep", users: 109},
    {name: "Oct", users: 114},
    {name: "Nov", users: 120},
    {name: "Dec", users: 125}
]

let reviewData = [
    {name: "Jan", review: 3},
    {name: "Feb",review: 4},
    {name: "Mar" , review: 3.5},
    {name: "Apr", review: 3.6},
    {name: "May", review: 4.1},
    {name: "Jun", review: 4.2},
    {name: "Jul", review: 4.2},
    {name: "Aug", review: 4.1},
    {name: "Sep", review: 4},
    {name: "Oct", review: 4.3},
    {name: "Nov", review: 4.5},
    {name: "Dec", review: 4.4}
]

let tlData = [
    {name: "Jan", amount: 100000},
    {name: "Feb",amount: 103000},
    {name: "Mar" , amount: 98000},
    {name: "Apr", amount: 90000},
    {name: "May", amount: 95000},
    {name: "Jun", amount: 99000},
    {name: "Jul", amount: 105000},
    {name: "Aug", amount: 107000},
    {name: "Sep", amount: 115000},
    {name: "Oct", amount: 130000},
    {name: "Nov", amount: 125000},
    {name: "Dec", amount: 135000}
]

let pchartData = [
    {name: "Istanbul", value: 33},
    {name: "Ankara", value: 53},
    {name: "Bursa", value: 43},
    {name: "Adana", value: 10},
    {name: "Others", value: 5}
]

let pchartData1 = [
    {name: "Istanbul", value: 60},
    {name: "Ankara", value: 21},
    {name: "Bursa", value: 43},
    {name: "Adana", value: 10},
    {name: "Others", value: 5}
]

function SystemReport(){
    return(
        <div className={"report-page"}>
            <LChart
                data={data}
                title={"Number of new ads and bookings for the last year"}
                line1={"ads"}
                line2={"bookings"}
                width={550}
                length={400}
            />

            <LChart
                title={"Number of users for the last year"}
                data={userData}
                line1={"users"}
                oneline={true}
                width={550}
                length={400}
            />

            <PChart data={pchartData} title={"Most Booked Cities"}/>

            <LChart
                title={"Review average for the last year"}
                data={reviewData}
                line1={"review"}
                oneline={true}
                width={550}
                length={400}
                max={5}
            />

            <LChart
                title={"Total TL transactions"}
                data={tlData}
                line1={"amount"}
                oneline={true}
                width={550}
                length={400}
                max={5}
            />

            <PChart data={pchartData1} title={"User Location"}/>
            <Info adsToday={10} adsMonth={47} bookingsToday={12} bookingsMonth={63}/>
        </div>
    );
}

export default SystemReport;