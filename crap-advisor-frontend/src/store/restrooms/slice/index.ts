import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {TApiStatusState, TReducerState} from "app/typings/redux";
import {Restroom} from "app/typings";

const restroomsSliceName = 'RESTROOMS';

type TRestroomState = TReducerState & TApiStatusState & {
    result: Restroom[];
}

const initialState: TRestroomState = {
    isLoading: false,
    error: null,
    isSuccess: false,
    result: []
}

export const restroomSlice = createSlice({
    name: restroomsSliceName,
    initialState,
    reducers: {
        start: (state) => {
            state.isLoading = true;
        },
        success: (state, action: PayloadAction<Restroom[]>) => {
            state.isSuccess = true;
            state.isLoading = false;
            state.error = null;
            state.result = action.payload;
        },
        error: (state,action: PayloadAction<string>) => {
            state.isSuccess = false;
            state.isLoading  = false;
            state.error = action.payload;
        }
    }
})

export const {start, error, success} = restroomSlice.actions;

export default restroomSlice.reducer;

