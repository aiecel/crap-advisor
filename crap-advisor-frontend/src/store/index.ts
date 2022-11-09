import { configureStore } from '@reduxjs/toolkit';

import { sagaMiddleware } from 'app/singletones';

import rootReducer from 'store/root/reducer';
import rootSaga from 'store/root/saga';

const middlewares = [sagaMiddleware];

const store = configureStore({
  reducer: rootReducer,
  middleware: (getDefaultMiddleware) => [
    ...getDefaultMiddleware({
      thunk: false,
      serializableCheck: false,
    }),
    ...middlewares,
  ],
});

sagaMiddleware.run(rootSaga, store.dispatch);

export type RootState = ReturnType<typeof rootReducer>;
export type AppDispatch = typeof store.dispatch;

export default store;
