import "./PlaceList.css";
import React from "react";
import PlaceSmall from "./PlaceSmall";
import { PlacesData } from "./PlacesData";

function PlaceList(props) {
  return (
    <div className="placeList">
      <h1>{props.heading}</h1>      
      <div className="places">
        {PlacesData.map((item, index) => {
          return (
            <PlaceSmall
              image={item.image}
              pricePerNight={item.pricePerNight}
              rate={item.rate}
              location={item.location}
              placeType={item.placeType}
              rentalPart={item.rentalPart}
            />
          );
        })}
      </div>
    </div>
  );
}
export default PlaceList;
