import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';
import React from "react";
import StatsView from "Frontend/views/stats";
import {StyledEngineProvider} from "@mui/material";

export default function HomeView() {
    return (
        <React.StrictMode>
            <StyledEngineProvider injectFirst>
                <StatsView/>
            </StyledEngineProvider>
        </React.StrictMode>
    );
}

