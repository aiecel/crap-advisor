import { PayloadAction, createSlice } from '@reduxjs/toolkit';

import { IReview, Restroom } from 'app/typings';
import { TApiStatusState, TReducerState } from 'app/typings/redux';

const reviewsSliceName = 'REVIEWS';

type TReviewsState = TReducerState &
  TApiStatusState & {
    result: IReview[];
    currentRestroom: Restroom | null;
  };

const initialState: TReviewsState = {
  isLoading: false,
  error: null,
  isSuccess: false,
  currentRestroom: null,
  result: [],
};

export const reviewsSlice = createSlice({
  name: reviewsSliceName,
  initialState,
  reducers: {
    start: (state, action) => {
      state.isLoading = true;
    },
    setCurrentRestroom: (state, action: PayloadAction<Restroom>) => {
      state.currentRestroom = action.payload;
    },

    clearCurrentRestroom: (state) => {
      state.currentRestroom = null;
    },
    success: (state, action: PayloadAction<IReview[]>) => {
      state.isSuccess = true;
      state.isLoading = false;
      state.error = null;
      state.result = action.payload;
    },
    error: (state, action: PayloadAction<string>) => {
      state.isSuccess = false;
      state.isLoading = false;
      state.error = action.payload;
    },
  },
});

export const { start, error, success, setCurrentRestroom, clearCurrentRestroom } =
  reviewsSlice.actions;

export default reviewsSlice.reducer;
