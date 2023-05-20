import "./Home.css";
import React from "react";
import Footer from "./components/Footer";
import Navbar from "./components/Navbar";
import PlaceList from "./components/PlaceList";

function Home() {
  return (
    <div className="Home">
      <Navbar/>      
      <PlaceList heading="Most Popular Places"/>
      <Footer/>
    </div>
  );
}
export default Home;
