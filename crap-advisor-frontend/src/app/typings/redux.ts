import { useMemo } from 'react';
import { TypedUseSelectorHook, useDispatch, useSelector } from 'react-redux';

import { ActionCreatorsMapObject, Dispatch, bindActionCreators } from 'redux';

import { AppDispatch, RootState } from 'store';

export const useAppDispatch = (): Dispatch => useDispatch<AppDispatch>();

export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;

export const useActions = <A, M extends ActionCreatorsMapObject<A>>(
  actions: M
): M => {
  const dispatch = useDispatch();
  return useMemo(
    () => bindActionCreators(actions, dispatch),
    [actions, dispatch]
  );
};

export type TReducerState = {
  isLoading: boolean;
};

export type TApiStatusState = {
  isSuccess: boolean;
  error: null | string;
};
