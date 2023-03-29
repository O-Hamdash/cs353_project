import "./Footer.css";
import React from "react";

function Footer() {
  return (
    <div className="footer">
      <div className="top">
        <div>
          <h1 className="footerLogo">
            <a href="/">
              <img src="./logo_white.png" className="logo" alt="GoBooking-logo" />
              Gobooking
            </a>
          </h1>
          <p>Find the best place for you.</p>
        </div>
      </div>
      <div className="bottom">
        <div>
          <h4>Project</h4>
          <a href="/">Changelog</a>
          <a href="/">Status</a>
          <a href="/">License</a>
          <a href="/">All Versions</a>
        </div>
        <div>
          <h4>Community</h4>
          <a href="/">GitHub</a>
          <a href="/">Issues</a>
          <a href="/">Project</a>
        </div>
        <div>
          <h4>Help</h4>
          <a href="/">Support</a>
          <a href="/">Troubleshooting</a>
          <a href="/">Contact Us</a>
        </div>
        <div>
          <h4>Others</h4>
          <a href="/">Terms of Service</a>
          <a href="/">Privacy Policy</a>
        </div>
      </div>
    </div>
  );
}
export default Footer;
