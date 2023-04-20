import React from 'react';
import './PropertiesPage.css';
import PropertyCard from './Components/PropertyCard';
import ReviewCard from './Components/ReviewCard.js';

function PropertiesPage() {
  const propertyData = [
    {
      id: 1,
      title: 'Luxury Apartment in the Heart of the City',
      location: 'New York',
      price: '$150/night',
      image: 'https://unsplash.com/photos/178j8tJrNlc/download?force=true&w=300&h=400',
    },
    {
      id: 2,
      title: 'Spacious House with a Beautiful Garden',
      location: 'Los Angeles',
      price: '$200/night',
      image: 'https://unsplash.com/photos/TiVPTYCG_3E/download?force=true&w=300&h=400',
    },
    {
      id: 3,
      title: 'Cozy Cottage in the Countryside',
      location: 'London',
      price: '$100/night',
      image: 'https://via.placeholder.com/400x300',
    },
  ];

  const reviewData = [
    {
      id: 1,
      auther: 'John Doe',
      comment: 'Great property, highly recommend!',
      image: 'https://via.placeholder.com/150x150',
      rating: 5,
    },
    {
      id: 2,
      auther: 'Jane Smith',
      comment: 'Lovely stay, would definitely come back.',
      image: 'https://via.placeholder.com/150x150',
      rating: 5,
    },
    {
      id: 3,
      auther: 'Bob Johnson',
      comment: 'Excellent service and amenities.',
      image: 'https://via.placeholder.com/150x150',
      rating: 5,
    },
  ];

  return (
    <div className="App">
      <div className="container">
        <div className="property-list">
        <h1>Property Listings</h1>
          {propertyData.map((property) => (
            <PropertyCard key={property.id} property={property} />
          ))}
        </div>
        <div className="review-list">
        <h1>Review Listings</h1>
          {reviewData.map((review) => (
            <ReviewCard key={review.id} review={review} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default PropertiesPage;