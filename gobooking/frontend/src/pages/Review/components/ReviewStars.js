import ReactStars from "react-rating-stars-component/dist/react-stars";
import React from 'react';

function ReviewStars(){
    const ratingChanged = (newRating) => {
        console.log(newRating);
    };

    return (
        <div>
            <ReactStars
                count={5}
                onChange={ratingChanged}
                size={24}
                activeColor="#ffd700"
            />
        </div>
    );
}

export default ReviewStars;