import React from 'react';
import PropTypes from 'prop-types';

const ReviewCard = ({ review }) => {
  const cardStyle = {
    backgroundColor: '#E6F2FF',
    padding: '20px',
    marginBottom: '20px'
  };


  return (
    <div className="property-card" style={cardStyle}>
    <div className='review-card'>
      <div className='review-card-header'>
        <h3>{review.title}</h3>
        <p>by {review.auther}</p>
      </div>
      <div className='review-card-body'>
        <p>{review.comment}</p>
        <p>{review.rating}/5</p>
      </div>
    </div>
    </div>
  );
};


export default ReviewCard;
