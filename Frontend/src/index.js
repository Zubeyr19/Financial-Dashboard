import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import {GlobalProvider} from './Services/context/globalContext';
import {GlobalStyle} from './Utilities/styles/GlobalStyle';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <GlobalStyle />
    <GlobalProvider>
      <App />
    </GlobalProvider>
  </React.StrictMode>
);