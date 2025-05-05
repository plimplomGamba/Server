import {ProgressBar} from '@vaadin/react-components/ProgressBar.js';
import * as React from 'react';
import {Suspense} from 'react';
import {Outlet} from 'react-router';
import AppTheme from "Frontend/views/gamba/theme/AppTheme";
import CssBaseline from "@mui/material/CssBaseline";
import AppAppBar from "Frontend/views/gamba/components/AppAppBar";
import Container from "@mui/material/Container";
import Footer from "Frontend/views/gamba/components/Footer";

export default function MainLayout(props: { disableCustomTheme?: boolean }) {
    return (
        <>
            <AppTheme {...props}>
                <CssBaseline enableColorScheme/>

                <AppAppBar/>
                <Container
                    maxWidth="lg"
                    component="main"
                    sx={{display: 'flex', flexDirection: 'column', my: 16, gap: 4}}
                >
                    <Suspense fallback={<ProgressBar indeterminate={true} className="m-0" style={{height: "auto"}}/>}>
                        <Outlet/>
                    </Suspense>
                </Container>
                <Footer/>
            </AppTheme>
        </>
    );
}