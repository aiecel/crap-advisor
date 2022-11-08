import {ActionCreatorsMapObject, AnyAction, bindActionCreators, Dispatch} from "redux";
import {TypedUseSelectorHook, useDispatch, useSelector} from "react-redux";
import {AppDispatch, RootState} from "store";
import {useMemo} from "react";

export const useAppDispatch = (): Dispatch<AnyAction> =>
    useDispatch<AppDispatch>();

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
}

export type TApiStatusState = {
    isSuccess: boolean;
    error: null | string;
}

