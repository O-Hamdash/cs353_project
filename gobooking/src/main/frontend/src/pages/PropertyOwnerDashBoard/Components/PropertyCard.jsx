import React from 'react';

const PropertyCard = ({ property, handleDelete }) => {
  const cardStyle = {
    backgroundColor: '#E6F2FF',
    padding: '20px',
    marginBottom: '20px'
  };

  return (
    <div className="property-card" style={cardStyle}>
      <div className="property-image">
        <img src={property.image} alt={property.title} />
      </div>
      <div className="property-details">
        <h2>{property.title}</h2>
        <p>{property.location}</p>
        <p>{property.price}</p>
        <button onClick={() => handleDelete(property.id)}>Delete</button>
      </div>
    </div>
  );
};

export default PropertyCard;
