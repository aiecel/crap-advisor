import { Dispatch } from 'redux';
import { all, fork } from 'redux-saga/effects';

import { watchGetRestroomSaga } from 'store/restrooms/saga';
import { watchGetReviewsByIdSaga } from 'store/reviews/saga';

export default function* Root(dispatch: Dispatch<any>) {
  yield all([
    fork(watchGetRestroomSaga, dispatch),
    fork(watchGetReviewsByIdSaga, dispatch),
  ]);
}
