import React from "react";
import "./index.css";
import {IReview} from "../../../../app/typings";

interface IReviewProps {
    review: IReview;
}

const Review = ({review}: IReviewProps): JSX.Element => {
    return (
        <div className="sidebar-review-container">
            <div className="sidebar-review-rating">{review.rating}</div>
            <div className="sidebar-review-description">{review.comment}</div>
        </div>
    );
};

export default Review;
