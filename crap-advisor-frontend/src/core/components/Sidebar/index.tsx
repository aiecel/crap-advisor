import React from 'react';

import { Review } from 'core/components';

import { useAppDispatch, useAppSelector } from 'app/typings/redux';

import { clearCurrentRestroom, start } from 'store/reviews/slice';

import { getRestroomIdFromURL } from 'utils';

import 'style/main.css';

import './index.css';

const Sidebar = (): JSX.Element => {
  const { result: reviews, currentRestroom } = useAppSelector(
    (state) => state.REVIEWS
  );
  const [restroomFromURL] = React.useState(getRestroomIdFromURL());

  const dispatch = useAppDispatch();

  React.useEffect(() => {
    if (!restroomFromURL) {
      dispatch(clearCurrentRestroom());
    } else dispatch(start(restroomFromURL));
  }, []);

  return (
    <div className="sidebar">
      <p className="logo">Crap Advisor</p>
      {!currentRestroom && (
        <>
          <p className="welcome">
            Добро пожаловать!
            <br />
            Выберите уборную...
          </p>
        </>
      )}
      {currentRestroom && (
        <>
          <section className="section-restroom">
            <p className="restroom-created">
              {new Date(currentRestroom.created).toLocaleDateString()}
            </p>
            <p className="restroom-name">{currentRestroom.name}</p>
            {currentRestroom.rating && (
              <p className="restroom-rating">{currentRestroom.rating}</p>
            )}
            <p className="restroom-reviews-count">
              {reviews.length === 0
                ? 'Нет визитов'
                : 'Визитов: ' + reviews.length}
            </p>
          </section>
          <section className="section-reviews">
            <button className="button-add-review">Добавить отзыв</button>
            {reviews.map((review) => {
              return <Review key={review.id} review={review} />;
            })}
          </section>
        </>
      )}
    </div>
  );
};

export default Sidebar;
