import {combineReducers} from "redux";
import { persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';

import restroomReducer from 'store/restrooms/slice';
import reviewsReducer from 'store/reviews/slice'

const persistConfig = {
    key: 'root',
    version: 1,
    storage,
};
const rootReducer = combineReducers({
    RESTROOM: restroomReducer,
    REVIEWS: reviewsReducer
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

export default persistedReducer;