import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

const ROOT_NODE = document.getElementById('root')!;
const root = ReactDOM.createRoot(ROOT_NODE);
root.render(
  // <React.StrictMode>
    <App />
  // </React.StrictMode>
);
