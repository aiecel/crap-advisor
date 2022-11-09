import React from 'react';

import { IReview } from 'app/typings';

import 'style/main.css';

import './index.css';

interface IReviewProps {
  review: IReview;
}

const Review = ({ review }: IReviewProps): JSX.Element => {
  return (
    <div className="review">
      <div className="review-rating">{review.rating}</div>
      <div className="review-right">
        <div className="review-created">
          {new Date(review.created).toLocaleString()}
        </div>
        {review.comment && (
          <div className="review-description">{review.comment}</div>
        )}
      </div>
    </div>
  );
};

export default Review;
