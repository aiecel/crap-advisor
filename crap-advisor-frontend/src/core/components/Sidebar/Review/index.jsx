import React from 'react';
import "./index.css"

const Review = ({review}) => {
    return (
        <div className="sidebar-review-container">
            <div className="sidebar-review-rating">{review.rating}</div>
            <div className="sidebar-review-description">{review.comment}</div>
        </div>
    );
};

export default Review;