import "normalize.css"
import React from "react";
import AppRoutes from "app/routes";
import {Provider} from 'react-redux';
import {persistStore} from 'redux-persist';
import store from 'store';
import {PersistGate} from "redux-persist/integration/react";

function App(): JSX.Element {

    const persistor = persistStore(store);
    return (
        <Provider store={store}>
            <PersistGate persistor={persistor}>
                <AppRoutes/>
            </PersistGate>
        </Provider>
    );
}

export default App;
