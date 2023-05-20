import React, { useState } from 'react';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faParking, faWifi,faKitchenSet } from "@fortawesome/free-solid-svg-icons";
import './PropertyPage.css';


function PropertyPage({ property }) {
  const [checkinDate, setCheckinDate] = useState('');
  const [checkoutDate, setCheckoutDate] = useState('');
  const [guests, setGuests] = useState(1);
  const [imageIndex, setImageIndex] = useState(0);

  const previousImage = () => {
    setImageIndex((imageIndex + property.images.length - 1) % property.images.length);
  };

  const nextImage = () => {
    setImageIndex((imageIndex + 1) % property.images.length);
  };

  const handleCheckinChange = (e) => {
    setCheckinDate(e.target.value);
  }

  const handleCheckoutChange = (e) => {
    setCheckoutDate(e.target.value);
  }

  const handleGuestsChange = (e) => {
    setGuests(parseInt(e.target.value));
  }

  const calculateCost = () => {
    // Your cost calculation logic here
    return 100;
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const cost = calculateCost();
    alert(`Reservation confirmed for ${guests} guests from ${checkinDate} to ${checkoutDate}. Total cost: $${cost}.`);
  }

  return (
    <div className="containerx">
      <h1>{property.title}</h1>
      <div className="image">
        
      
      </div>
      <div className="image-container">
      {property.images && (
          <>
          <img src={property.images[imageIndex]} alt={property.title} />
          <button className="previous-button" onClick={previousImage}>
          &#8249;
        </button>
        <button className="next-button" onClick={nextImage}>
          &#8250;
        </button>
        </>
           )}

      </div>

      <div className="details">
        <div className="details__description">
          <p>{property.description}</p>
        </div>
        <div className="details__info">
          <div className="details__info-item">
            <span>Bedrooms:</span> {property.bedrooms}
          </div>
          <div className="details__info-item">
            <span>Bathrooms:</span> {property.bathrooms}
          </div>
          <div className="details__info-item">
            <span>Max guests:</span> {property.max_guests}
          </div>
          <div className="details__info-item">
            <span>Price per night:</span> ${property.price_per_night}
          </div>
        </div>
      </div>

        {property.services && (
      <div className="details__services">
        <h3 className="details__services-heading">Services</h3>
        {property.services[0] && (
            <div className="details__services-item">
              <FontAwesomeIcon icon={faWifi} />
              <p>Wi-Fi</p>
            </div>
          )}
          {property.services[1] && (
            <div className="details__services-item">
              <FontAwesomeIcon icon={faParking} />
              <p>Parking</p>
            </div>
          )}
          {property.services[2] && (
            <div className="details__services-item">
              <FontAwesomeIcon icon={faKitchenSet} />
              <p>Kitchen</p>
            </div>
          )}
      </div>
    )}


      <form onSubmit={handleSubmit} className="form">
        <input type="date" name="checkin" id="checkin" value={checkinDate} onChange={handleCheckinChange} required />
        <input type="date" name="checkout" id="checkout" value={checkoutDate} onChange={handleCheckoutChange} required />
        <input type="number" name="guests" id="guests" min="1" value={guests} onChange={handleGuestsChange} required />
        <button type="submit">Reserve</button>
      </form>

      <div className="summary">
        <div className="summary__item">Check-in: <span>{checkinDate}</span></div>
        <div className="summary__item">Check-out: <span>{checkoutDate}</span></div>
        <div className="summary__item">Guests: <span>{guests}</span></div>
      </div>

      <div className="total">Total cost: ${calculateCost()}</div>

    </div>
  );
}

export default PropertyPage;
