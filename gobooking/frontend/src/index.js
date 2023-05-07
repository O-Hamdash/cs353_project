import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';

import SystemReport from './pages/SystemReport/SystemReport';
import Review from "./pages/Review/Review";
import AdminDashboard from "./pages/AdminDashboard/AdminDashboard";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <div>
        <AdminDashboard />
        <SystemReport />
        <Review />
    </div>
);

/*
<SystemReport />
<Review />
<AdminDashboard />
 */

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
