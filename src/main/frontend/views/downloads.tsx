import * as React from 'react';
import {useCallback, useEffect, useState} from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Chip from '@mui/material/Chip';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import {styled} from '@mui/material/styles';
import {useSignal} from "@vaadin/hilla-react-signals";
import StatsDTO from "Frontend/generated/de/plimplom/gamba/server/stats/StatsDTO";
import {StatsController} from "Frontend/generated/endpoints";
import DetailedStats from "Frontend/generated/de/plimplom/gamba/server/stats/DetailedStats";
import {Button, Grid as HillaGrid, GridColumn, GridSortColumn, Icon} from "@vaadin/react-components";


const StyledCard = styled(Card)(({theme}) => ({
    // display: 'flex',
    // flexDirection: 'column',
    // padding: 0,
    // height: '100%',
    // // @ts-ignore
    // backgroundColor: (theme.vars || theme).palette.background.paper,
    // // '&:hover': {
    // //     backgroundColor: 'transparent',
    // //     // cursor: 'pointer',
    // // },
    // '&:focus-visible': {
    //     outline: '3px solid',
    //     outlineColor: 'hsla(210, 98%, 48%, 0.5)',
    //     outlineOffset: '2px',
    // },
}));

const StyledCardContent = styled(CardContent)({
    display: 'flex',
    flexDirection: 'column',
    gap: 4,
    padding: 16,
    flexGrow: 1,
    '&:last-child': {
        paddingBottom: 16,
    },
});

const StyledTypography = styled(Typography)({
    display: '-webkit-box',
    WebkitBoxOrient: 'vertical',
    WebkitLineClamp: 2,
    overflow: 'hidden',
    textOverflow: 'ellipsis',
});

export default function DownloadsView() {
    return (
        <Grid container spacing={2} columns={12}>
            {/*{winner ? <Grid size={{xs: 12, md: 12}}>*/}
            {/*    <StyledCard*/}
            {/*        variant="outlined"*/}
            {/*        tabIndex={0}*/}
            {/*    >*/}
            {/*        <StyledCardContent>*/}
            {/*            <Typography gutterBottom variant="h3" component="div">*/}
            {/*                Biggest winner*/}
            {/*            </Typography>*/}
            {/*            <Typography gutterBottom variant="h4" component="div">*/}
            {/*                {winner.playerName}*/}
            {/*            </Typography>*/}
            {/*            <StyledTypography variant="h1" gutterBottom sx={{color: 'forestgreen'}}>*/}
            {/*                {winner.fullAmount}*/}
            {/*            </StyledTypography>*/}
            {/*        </StyledCardContent>*/}
            {/*    </StyledCard>*/}
            {/*</Grid> : <></>}*/}
            {/*{loser ? <Grid size={{xs: 12, md: 12}}>*/}
            {/*    <StyledCard*/}
            {/*        variant="outlined"*/}
            {/*        tabIndex={0}*/}
            {/*    >*/}
            {/*        <StyledCardContent>*/}
            {/*            <Typography gutterBottom variant="h3" component="div">*/}
            {/*                Biggest loser*/}
            {/*            </Typography>*/}
            {/*            <Typography gutterBottom variant="h4" component="div">*/}
            {/*                {loser.playerName}*/}
            {/*            </Typography>*/}
            {/*            <StyledTypography variant="h1" gutterBottom sx={{color: 'orangered'}}>*/}
            {/*                {loser.fullAmount}*/}
            {/*            </StyledTypography>*/}
            {/*        </StyledCardContent>*/}
            {/*    </StyledCard>*/}
            {/*</Grid> : <></>}*/}
            <Grid size={{xs: 12, md: 12}} sx={{ mt: "1em"}}>
                <StyledCard
                    variant="outlined"
                    tabIndex={0}
                >
                    <StyledCardContent>
                        <Typography gutterBottom variant="h3" component="div">
                            Addon: plimplomGamba
                        </Typography>
                        <Typography gutterBottom variant="body2" component="div">
                            Start high/low gambles, track and join deathrolls, all in one addon!
                        </Typography>
                        <Typography gutterBottom variant="body2" component="div">
                            Download the latest version of <a href="/files/addon" target="_blank">plimplomGamba here!</a>
                        </Typography>
                    </StyledCardContent>
                </StyledCard>
            </Grid>
            <Grid size={{xs: 12, md: 12}} sx={{ mt: "1em"}}>
                <StyledCard
                    variant="outlined"
                    tabIndex={0}
                >
                    <StyledCardContent>
                        <Typography gutterBottom variant="h3" component="div">
                            Client
                        </Typography>
                        <Typography gutterBottom variant="body2" component="div">
                            Download the client and run it to automatically sync your gamble stats with the server.
                        </Typography>
                        <Typography gutterBottom variant="body2" component="div">
                            Download the latest version of the <a href="/files/client" target="_blank">Client here!</a>
                        </Typography>
                    </StyledCardContent>
                </StyledCard>
            </Grid>
        </Grid>
    );
}