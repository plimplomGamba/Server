import * as React from 'react';
import {useEffect, useState} from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Chip from '@mui/material/Chip';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import {styled} from '@mui/material/styles';
import {useSignal} from "@vaadin/hilla-react-signals";
import StatsDTO from "Frontend/generated/de/plimplom/gamba/server/stats/StatsDTO";
import {GambaConfigClient, StatsController} from "Frontend/generated/endpoints";
import StatsWrapper from "Frontend/generated/de/plimplom/gamba/server/stats/StatsWrapper";
import {Collapse, IconButton, Table, TableBody, TableCell, TableHead, TableRow, TableSortLabel} from "@mui/material";
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import GameMode from "Frontend/generated/de/plimplom/gamba/server/stats/GameMode";
import {visuallyHidden} from '@mui/utils';

const StyledCard = styled(Card)(({theme}) => ({
    display: 'flex',
    flexDirection: 'column',
    padding: 0,
    height: '100%',
    // @ts-ignore
    backgroundColor: (theme.vars || theme).palette.background.paper,
    '&:focus-visible': {
        outline: '3px solid',
        outlineColor: 'hsla(210, 98%, 48%, 0.5)',
        outlineOffset: '2px',
    },
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

enum GameModeSelections {
    ALL,
    HIGH_LOW,
    DEATH_ROLL,
}

function formatGamemode(gameMode: GameMode | undefined) {
    if (gameMode == undefined) {
        return ""
    }
    switch (gameMode) {
        case GameMode.DEATH_ROLL:
            return "Deathroll";
        case GameMode.HIGH_LOW:
            return "High/low";
        default:
            return "";
    }
}

function getColorFromValue(amount: number) {
    if (amount > 0) {
        return "forestgreen";
    } else if (amount < 0) {
        return "orangered";
    } else {
        return "white";
    }
}

export default function MainContent() {
    const [guildName, setGuildName] = useState<string>("")
    const [order, setOrder] = React.useState<Order>('desc');
    const [orderBy, setOrderBy] = React.useState<keyof Data>('amount');
    const values = useSignal<StatsWrapper>({
        statsDTO: [],
        totalGoldGambled: 0
    });
    const [selectedGameMode, setSelectedGameMode] = useState(0);
    const [winner, setWinner] = useState<StatsDTO | undefined>();
    const [loser, setLoser] = useState<StatsDTO | undefined>();

    const handleRequestSort = (
        event: React.MouseEvent<unknown>,
        property: keyof Data,
    ) => {
        const isAsc = orderBy === property && order === 'asc';
        console.log(order, orderBy, isAsc, property);

        if (property !== orderBy && property == "amount") {
            setOrder(isAsc ? 'desc' : 'desc');
            setOrderBy(property);
            return;
        }

        if (property === "name") {
            setOrder(isAsc ? 'desc' : 'asc');
        } else {
            setOrder(isAsc ? 'desc' : 'asc');
        }

        setOrder(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };

    const handleClick = (mode: GameModeSelections) => {
        switch (mode) {
            case GameModeSelections.ALL:
                StatsController.getFullData().then(value => values.value = value)
                setSelectedGameMode(0);
                return;
            case GameModeSelections.HIGH_LOW:
                StatsController.getHighLowData().then(value => values.value = value)
                setSelectedGameMode(1);
                return;
            case GameModeSelections.DEATH_ROLL:
                StatsController.getDeathrollData().then(value => values.value = value)
                setSelectedGameMode(2);
                return;
            default:
                StatsController.getFullData().then(value => values.value = value)
                setSelectedGameMode(0);
        }
    };

    useEffect(() => {
        StatsController.getFullData().then(value => values.value = value)
        GambaConfigClient.getGuildName().then(value => setGuildName(value))
    }, []);

    useEffect(() => {
        const sorted = values.value?.statsDTO.sort((a, b) => a.fullAmount < b.fullAmount ? 1 : -1);
        if (sorted) {
            setWinner(sorted[0]);
            setLoser(sorted[sorted.length - 1]);
        }
    }, [values.value]);

    const getFirstBrowserLanguage = function () {
        const nav = window.navigator,
            browserLanguagePropertyKeys = ['language', 'browserLanguage', 'systemLanguage', 'userLanguage'];
        let i: number, language: string | any[];

        // support for HTML 5.1 "navigator.languages"
        if (Array.isArray(nav.languages)) {
            for (i = 0; i < nav.languages.length; i++) {
                language = nav.languages[i];
                if (language && language.length) {
                    return language;
                }
            }
        }

        // support for other well known properties in browsers
        for (i = 0; i < browserLanguagePropertyKeys.length; i++) {
            // @ts-ignore
            language = nav[browserLanguagePropertyKeys[i]];
            if (language && language.length) {
                return language;
            }
        }

        return "en-GB";
    };

    const formatNumber = (number: number): string => {
        return new Intl.NumberFormat(getFirstBrowserLanguage()).format(number);
    }

    type Order = 'asc' | 'desc';

    interface Data {
        amount: number;
        name: number;
    }

    interface EnhancedTableProps {
        onRequestSort: (event: React.MouseEvent<unknown>, property: keyof Data) => void;
        order: Order;
        orderBy: string;
    }

    function EnhancedTableHead(props: EnhancedTableProps) {
        const {order, orderBy, onRequestSort} =
            props;
        const createSortHandler =
            (property: keyof Data) => (event: React.MouseEvent<unknown>) => {
                onRequestSort(event, property);
            };

        return (
            <TableHead>
                <StyledTableRow>
                    <TableCell padding="checkbox">
                    </TableCell>
                    <TableCell
                        sortDirection={orderBy === "name" ? order : false}
                    >
                        <TableSortLabel
                            active={orderBy === "name"}
                            direction={orderBy === "name" ? order : 'asc'}
                            onClick={createSortHandler("name")}
                        >
                            Player Name
                            {orderBy === "name" ? (
                                <Box component="span" sx={visuallyHidden}>
                                    {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
                                </Box>
                            ) : null}
                        </TableSortLabel>
                    </TableCell>
                    <TableCell
                        sortDirection={orderBy === "amount" ? order : false}
                    >
                        <TableSortLabel
                            active={orderBy === "amount"}
                            direction={orderBy === "amount" ? order : 'asc'}
                            onClick={createSortHandler("amount")}
                        >
                            Amount won/lost
                            {orderBy === "amount" ? (
                                <Box component="span" sx={visuallyHidden}>
                                    {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
                                </Box>
                            ) : null}
                        </TableSortLabel>
                    </TableCell>
                </StyledTableRow>
            </TableHead>
        );
    }

    const StyledTableRow = styled(TableRow)(({theme}) => ({
        // @ts-ignore
        backgroundColor: (theme.vars || theme).palette.background.paper,
    }));

    function Row(props: { row: StatsDTO }) {
        const {row} = props;
        const [open, setOpen] = React.useState(false);

        return (
            <React.Fragment>
                <StyledTableRow
                >
                    <TableCell>
                        <IconButton
                            aria-label="expand row"
                            size="small"
                            onClick={() => setOpen(!open)}
                        >
                            {open ? <KeyboardArrowUpIcon/> : <KeyboardArrowDownIcon/>}
                        </IconButton>
                    </TableCell>
                    <TableCell component="th" scope="row">
                        {row.playerName}
                    </TableCell>
                    <TableCell
                        sx={{color: getColorFromValue(row.fullAmount)}}>{formatNumber(row.fullAmount)}</TableCell>
                </StyledTableRow>
                <StyledTableRow>
                    <TableCell style={{paddingBottom: 0, paddingTop: 0}} colSpan={3}>
                        <Collapse in={open} timeout="auto" unmountOnExit sx={{marginBottom: '2em'}}>
                            <Box sx={{margin: 1}}>
                                <Typography variant="h6" gutterBottom component="div">
                                    Detailed stats
                                </Typography>
                                <Table size="small">
                                    <TableHead>
                                        <TableRow>
                                            <TableCell>Player</TableCell>
                                            <TableCell>Gold won/lost</TableCell>
                                            <TableCell>Gamemode</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {row.detailedStats.map((details) => (
                                            <TableRow key={details.playerName + details.gameMode}>
                                                <TableCell component="th" scope="row">
                                                    {details.playerName}
                                                </TableCell>
                                                <TableCell
                                                    sx={{color: getColorFromValue(details.amount)}}>{formatNumber(details.amount)}</TableCell>
                                                <TableCell>{formatGamemode(details.gameMode)}</TableCell>
                                            </TableRow>
                                        ))}
                                    </TableBody>
                                </Table>
                            </Box>
                        </Collapse>
                    </TableCell>
                </StyledTableRow>
            </React.Fragment>
        );
    }

    function doSort(order: "asc" | "desc", orderBy: "amount" | "name") {
        return function (p1: StatsDTO, p2: StatsDTO) {
            let returnVal = 0;
            if (orderBy === "name") {
                returnVal = p1.playerName.localeCompare(p2.playerName);
            } else {
                returnVal = 0 - (p1.fullAmount > p2.fullAmount ? -1 : 1)
            }
            return order === "asc" ? returnVal : -returnVal;
        };
    }

    const visibleRows = React.useMemo(
        () =>
            [...values.value.statsDTO]
                .sort(doSort(order, orderBy)),
        [order, orderBy, values.value.statsDTO],
    );

    return (
        <Box sx={{display: 'flex', flexDirection: 'column', gap: 4, mt: "1em"}}>
            <div>
                <Typography variant="h1" gutterBottom color="text.primary">
                    &lt;{guildName}&gt; Gamba stats
                </Typography>
                <Typography>See who has lost and won the most amount of gold.</Typography>
            </div>
            <Box
                sx={{
                    display: 'flex',
                    flexDirection: {xs: 'column-reverse', md: 'row'},
                    width: '100%',
                    justifyContent: 'space-between',
                    alignItems: {xs: 'start', md: 'center'},
                    gap: 4,
                    overflow: 'auto',
                }}
            >
                <Box
                    sx={{
                        display: 'inline-flex',
                        flexDirection: 'row',
                        gap: 3,
                        overflow: 'auto',
                    }}
                >
                    <Chip onClick={() => handleClick(GameModeSelections.ALL)} size="medium" label="All Game modes"
                          sx={{
                              backgroundColor: selectedGameMode == 0 ? 'default' : 'transparent',
                              border: selectedGameMode == 0 ? '1px solid' : 'none',
                          }}
                    />
                    <Chip
                        onClick={() => handleClick(GameModeSelections.HIGH_LOW)}
                        size="medium"
                        label="High/low"
                        sx={{
                            backgroundColor: selectedGameMode == 1 ? 'default' : 'transparent',
                            border: selectedGameMode == 1 ? '1px solid' : 'none',
                        }}
                    />
                    <Chip
                        onClick={() => handleClick(GameModeSelections.DEATH_ROLL)}
                        size="medium"
                        label="Deathroll"
                        sx={{
                            backgroundColor: selectedGameMode == 2 ? 'default' : 'transparent',
                            border: selectedGameMode == 2 ? '1px solid' : 'none',
                        }}
                    />
                </Box>
                <Box
                    sx={{
                        display: {xs: 'none', sm: 'flex'},
                        flexDirection: 'row',
                        gap: 1,
                        width: {xs: '100%', md: 'fit-content'},
                        overflow: 'auto',
                    }}
                >
                </Box>
            </Box>
            <Grid container spacing={2} columns={12}>
                <Grid size={{xs: 12, md: 12}}>
                    <StyledCard
                        variant="outlined"
                        tabIndex={0}
                    >
                        <StyledCardContent>
                            <Typography gutterBottom variant="h3" component="div">
                                Total gold gambled
                            </Typography>
                            <Typography gutterBottom variant="h1" component="div" sx={{color: 'gamba.gold'}}>
                                {formatNumber(values.value.totalGoldGambled)}
                            </Typography>
                        </StyledCardContent>
                    </StyledCard>
                </Grid>
                {winner ? <Grid size={{xs: 12, md: 12}}>
                    <StyledCard
                        variant="outlined"
                        tabIndex={0}
                    >
                        <StyledCardContent>
                            <Typography gutterBottom variant="h3" component="div">
                                Biggest winner
                            </Typography>
                            <Typography gutterBottom variant="h4" component="div">
                                {winner.playerName}
                            </Typography>
                            <StyledTypography variant="h1" gutterBottom sx={{color: 'forestgreen'}}>
                                {formatNumber(winner.fullAmount)}
                            </StyledTypography>
                        </StyledCardContent>
                    </StyledCard>
                </Grid> : <></>}
                {loser ? <Grid size={{xs: 12, md: 12}}>
                    <StyledCard
                        variant="outlined"
                        tabIndex={0}
                    >
                        <StyledCardContent>
                            <Typography gutterBottom variant="h3" component="div">
                                Biggest loser
                            </Typography>
                            <Typography gutterBottom variant="h4" component="div">
                                {loser.playerName}
                            </Typography>
                            <StyledTypography variant="h1" gutterBottom sx={{color: 'orangered'}}>
                                {formatNumber(loser.fullAmount)}
                            </StyledTypography>
                        </StyledCardContent>
                    </StyledCard>
                </Grid> : <></>}
                <Grid size={{xs: 12, md: 12}}>
                    <StyledCard
                        variant="outlined"
                        tabIndex={0}
                    >
                        <StyledCardContent>
                            <Typography gutterBottom variant="h3" component="div">
                                Detailed stats
                            </Typography>
                            <Box>
                                <Table stickyHeader>
                                    <EnhancedTableHead onRequestSort={handleRequestSort} order={order}
                                                       orderBy={orderBy}/>
                                    <TableBody>
                                        {visibleRows.map((row) => (
                                            <Row key={row.playerName} row={row}/>
                                        ))}
                                    </TableBody>
                                </Table>
                            </Box>
                        </StyledCardContent>
                    </StyledCard>
                </Grid>
            </Grid>
        </Box>
    );
}
