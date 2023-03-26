import ReactStars from "react-rating-stars-component/dist/react-stars";
import React from 'react';

function StaticReviewStars(props){

    return (
        <div>
            <ReactStars
                count={5}
                value={props.value}
                size={24}
                edit={false}
                half={true}
                activeColor="#ffd700"
            />
        </div>
    );
}

export default StaticReviewStars;