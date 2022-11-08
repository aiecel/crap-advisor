import {Dispatch} from 'react';

import {AxiosError, AxiosResponse} from 'axios';
import {SagaIterator} from 'redux-saga';
import {all, call, put, takeLatest, take, StrictEffect, select} from 'redux-saga/effects';

import {Api} from 'app/api';

import {start, success, error, setCurrentRestroom} from 'store/reviews/slice';
import {success as restroomSuccess} from 'store/restrooms/slice';
import {ApiEndpoint} from "app/constants";
import {Restroom} from 'app/typings'
import {PayloadAction} from "@reduxjs/toolkit";

function* getReviewsByIdSaga(action: PayloadAction<string>): Generator<StrictEffect, void, any> {
    try {
        let currentRestroom = {};
        const {RESTROOM: {result}} = yield select();
        if (result.length === 0) {
            const result = yield take(restroomSuccess.type);
            currentRestroom = result.payload.find((restroom: any) => String(restroom.id) === action.payload);
        }
        const {data} = yield call(Api.getFromAPI, ApiEndpoint.GetAllReviewsByRestroomId, {
            restroomId: action.payload
        });
        const currentRestroomByState = result.find((restroom: any) => String(restroom.id) === action.payload)
        yield put(success(data));
        yield put(setCurrentRestroom(Object.values(currentRestroom).length === 0 ? currentRestroomByState : currentRestroom as Restroom));
    } catch (e: unknown) {
        console.error(e as AxiosError);
        const apiResponse = e as AxiosError
        yield put(error(apiResponse.message));
    }
}

export function* watchGetReviewsByIdSaga(dispatch: Dispatch<unknown>): SagaIterator {
    all([yield takeLatest(start.type, getReviewsByIdSaga)])
}