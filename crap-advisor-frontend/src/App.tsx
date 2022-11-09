import React from 'react';
import { Provider } from 'react-redux';

import 'normalize.css';
import { persistStore } from 'redux-persist';
import { PersistGate } from 'redux-persist/integration/react';

import AppRoutes from 'app/routes';

import store from 'store';

function App(): JSX.Element {
  const persistor = persistStore(store);
  return (
    <Provider store={store}>
      <PersistGate persistor={persistor}>
        <AppRoutes />
      </PersistGate>
    </Provider>
  );
}

export default App;
