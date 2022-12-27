import { Dispatch } from 'react';

import { AxiosError, AxiosResponse } from 'axios';
import { SagaIterator } from 'redux-saga';
import {
  StrictEffect,
  all,
  call,
  put,
  takeEvery,
  takeLatest,
  takeLeading,
} from 'redux-saga/effects';

import { Api } from 'app/api';
import { ApiEndpoint } from 'app/constants';
import { Restroom } from 'app/typings';

import { error, start, success } from 'store/restrooms/slice';

export function* getRestroomsSaga(): Generator<
  StrictEffect,
  void,
  AxiosResponse<Restroom[]>
> {
  try {
    const { data } = yield call(Api.getFromAPI, ApiEndpoint.GetAllRestrooms);
    yield put(success(data));
  } catch (e: unknown) {
    console.error(e as AxiosError);
    const apiResponse = e as AxiosError;
    yield put(error(apiResponse.message));
  }
}

export function* watchGetRestroomSaga(
  dispatch: Dispatch<unknown>
): SagaIterator {
  all([yield takeEvery(start.type, getRestroomsSaga)]);
}
