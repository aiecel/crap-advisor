import { useMemo } from 'react';
import { TypedUseSelectorHook, useDispatch, useSelector } from 'react-redux';

import { ActionCreatorsMapObject, Dispatch, bindActionCreators } from 'redux';

import { AppDispatch, RootState } from 'store';

export const useAppDispatch = (): Dispatch => useDispatch<AppDispatch>();

export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;

export type TReducerState = {
  isLoading: boolean;
};

export type TApiStatusState = {
  isSuccess: boolean;
  error: null | string;
};
