import React, { useState } from "react";
import "./AddPropertyPage.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

function AddPropertyPage() {
  const [address, setAddress] = useState("");
  const [price, setPrice] = useState("");
  const [maxCount, setMaxCount] = useState("");
  const [description, setDescription] = useState("");
  const [availableDateRange, setAvailableDateRange] = useState([null, null]);
  const [propertyType, setPropertyType] = useState("apartment");

  const handleAddProperty = () => {
    const newProperty = {
      address,
      price,
      description,
      availableDateRange,
    };
    console.log(newProperty); // you can remove this line, just to check if the values are being stored correctly.
  };

  return (
    <div className="container">
      <div className="input-container">
        <label htmlFor="property-type">Property Type:</label>
        <select
          id="property-type"
          value={propertyType}
          onChange={(e) => setPropertyType(e.target.value)}
        >
          <option value="apartment">Apartment</option>
          <option value="villa">Villa</option>
          <option value="room">Room</option>
        </select>
      </div>

      <div className="input-container">
        <label htmlFor="address">Address:</label>
        <input
          type="text"
          id="address"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
        />
      </div>
      <div className="input-container">
        <label htmlFor="price">Price:</label>
        <input
          type="number"
          id="price"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
        />
      </div>
      <div className="input-container">
        <label htmlFor="maxCount">Max People to Stay:</label>
        <input
          type="number"
          id="maxCount"
          value={maxCount}
          onChange={(e) => setMaxCount(e.target.value)}
        />
      </div>
      <div className="input-container">
        <label htmlFor="description">Description:</label>
        <textarea
          id="description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </div>
      <div className="input-container">
        <label htmlFor="available-date-range">Available Date Range:</label>
        <DatePicker
          selected={availableDateRange[0]}
          startDate={availableDateRange[0]}
          endDate={availableDateRange[1]}
          selectsRange
          onChange={(dates) => setAvailableDateRange(dates)}
          dateFormat="MM/dd/yyyy"
          placeholderText="Select Date Range"
        />
      </div>



      <button onClick={handleAddProperty}>Add Property</button>
    </div>
  );
}

export default AddPropertyPage;
