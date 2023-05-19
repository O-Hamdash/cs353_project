import "./PlaceSmall.css";
import React from "react";

function PlaceSmall(props) {
  var days = 3;
  return (
    <div className="placeSmall">
      <div className="placeImage">
        <img src={props.image} alt="image1" />
      </div>
      <div className="placeInfo">
        <div className="price flex">
          <h4 className="priceTotal">&#8378;{props.pricePerNight * days}</h4>
          <h4 className="rate">
            <i class="fa-sharp fa-solid fa-star"></i>
            {props.rate}
          </h4>
        </div>
        <p className="unitPrice">
          <i class="fa-solid fa-money-bill-wave"></i> &#8378;
          {props.pricePerNight} per night
        </p>
        <p>
          <i class="fa-sharp fa-solid fa-building"></i> {props.placeType} / {props.rentalType}
        </p>
        <p>
          <i class="fa-sharp fa-solid fa-location-dot"></i> {props.location}
        </p>
      </div>
    </div>
  );
}
export default PlaceSmall;
