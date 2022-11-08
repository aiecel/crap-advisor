import {Dispatch} from 'react';

import {AxiosError, AxiosResponse} from 'axios';
import {AnyAction} from 'redux';
import {SagaIterator} from 'redux-saga';
import {all, call, put, takeLatest, takeLeading, StrictEffect, takeEvery} from 'redux-saga/effects';

import {Api} from 'app/api';

import {start, success, error} from 'store/restrooms/slice';
import {ApiEndpoint} from "app/constants";
import {Restroom} from 'app/typings'

export function* getRestroomsSaga(): any {
    try {
        const {data} = yield call(Api.getFromAPI, ApiEndpoint.GetAllRestrooms);
        yield put(success(data))
    } catch (e: unknown) {
        console.error(e as AxiosError);
        const apiResponse = e as AxiosError
        yield put(error(apiResponse.message));
    }
}

export function* watchGetRestroomSaga(dispatch: Dispatch<unknown>): SagaIterator {
    all([yield takeEvery(start.type, getRestroomsSaga)])
}