import { Dispatch } from 'react';

import { PayloadAction } from '@reduxjs/toolkit';
import { AxiosError, AxiosResponse } from 'axios';
import { SagaIterator } from 'redux-saga';
import {
  StrictEffect,
  all,
  call,
  put,
  select,
  take,
  takeLatest,
} from 'redux-saga/effects';

import { Api } from 'app/api';
import { ApiEndpoint } from 'app/constants';
import { IReview, Restroom } from 'app/typings';

import { success as restroomSuccess } from 'store/restrooms/slice';
import { error, setCurrentRestroom, start, success } from 'store/reviews/slice';

function* getReviewsByIdSaga(
  action: PayloadAction<string>
): Generator<StrictEffect, void> {
  try {
    let currentRestroom: any = {};
    const result = (yield select((state) => state.RESTROOM)) as Restroom[];
    if (result.length === 0) {
      const result = (yield take(restroomSuccess.type)) as PayloadAction<Restroom[]>;
      currentRestroom = result.payload.find(
        (restroom: any) => String(restroom.id) === action.payload
      );
    }
    const { data } = (yield call(Api.getFromAPI, ApiEndpoint.GetAllReviewsByRestroomId, {
      restroomId: action.payload,
    })) as AxiosResponse<IReview[]>;

    const currentRestroomByState = result.find(
      (restroom) => String(restroom.id) === action.payload
    );
    yield put(success(data));
    yield put(
      setCurrentRestroom(
        Object.values(currentRestroom).length === 0
          ? currentRestroomByState!
          : (currentRestroom as Restroom)
      )
    );
  } catch (e: unknown) {
    console.error(e as AxiosError);
    const apiResponse = e as AxiosError;
    yield put(error(apiResponse.message));
  }
}

export function* watchGetReviewsByIdSaga(dispatch: Dispatch<unknown>): SagaIterator {
  all([yield takeLatest(start.type, getReviewsByIdSaga)]);
}
