import React from "react";

import './style.css'
import ProfileCard from "./components/ProfileCard";
import {devtool} from "react-rating-stars-component/webpack.config";
import TopList from "./components/TopList";
import SystemReportSection from "./components/SystemReportSection";
import Info from "./components/Info";


function AdminDashboard() {
    return (
        <div className={"admin-page"}>
            <h1>Admin Dashboard</h1>
            <ProfileCard />
            <div className={"info-section"}>
                <div className={"toplist-border"}>
                    <h2>Top Rated Elements on the website</h2>
                    <div className={"toplist-section"}>
                        <TopList
                            title={"Top 5 Rated Houses"}
                        />

                        <TopList
                            title={"Top 5 Rated House Owners"}
                        />

                        <TopList
                            title={"Top 5 Rated Travellers"}
                        />
                    </div>
                </div>
                <div className={"generate-section"}>
                    <div className={"right-side"}>
                        <SystemReportSection />
                        <Info adsToday={10} adsMonth={47} bookingsToday={12} bookingsMonth={63}/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AdminDashboard;