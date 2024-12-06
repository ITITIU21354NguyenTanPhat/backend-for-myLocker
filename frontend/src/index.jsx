import React from 'react';
import './index.css';
import { createRoot } from 'react-dom/client';
import { PublicClientApplication } from '@azure/msal-browser';
import { MsalProvider } from '@azure/msal-react';
import App from './App';

const msalConfig = {
    auth: {
        clientId: "42545bdf-ac52-4cbb-bdcc-07526ec228f7", // Client ID
        authority: "https://login.microsoftonline.com/a7380202-eb54-415a-9b66-4d9806cfab42", // Tenant ID
        redirectUri: "http://localhost:3000",
    },
};

const msalInstance = new PublicClientApplication(msalConfig);

createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <MsalProvider instance={msalInstance}>
                <App />
        </MsalProvider>
    </React.StrictMode>
);
