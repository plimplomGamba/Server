import * as React from 'react';
import {alpha, styled} from '@mui/material/styles';
import Box from '@mui/material/Box';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import Container from '@mui/material/Container';
import MenuItem from '@mui/material/MenuItem';
import Drawer from '@mui/material/Drawer';
import MenuIcon from '@mui/icons-material/Menu';
import CloseRoundedIcon from '@mui/icons-material/CloseRounded';
import ColorModeIconDropdown from '.././theme/ColorModeIconDropdown';
import {useNavigate} from "react-router";
// @ts-ignore
import logo from '../../../icons/plimplomgamba_appbar_no space.png';
import Stack from "@mui/material/Stack";

const StyledToolbar = styled(Toolbar)(({theme}) => ({
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    flexShrink: 0,
    borderRadius: `calc(${theme.shape.borderRadius}px + 8px)`,
    backdropFilter: 'blur(24px)',
    border: '1px solid',
    // @ts-ignore
    borderColor: (theme.vars || theme).palette.divider,
    // @ts-ignore
    backgroundColor: theme.vars
        // @ts-ignore
        ? `rgba(${theme.vars.palette.background.defaultChannel} / 0.4)`
        : alpha(theme.palette.background.default, 0.4),
    // @ts-ignore
    boxShadow: (theme.vars || theme).shadows[1],
    padding: '8px 12px',
}));

export default function AppAppBar() {
    const navigate = useNavigate();
    const [open, setOpen] = React.useState(false);

    const toggleDrawer = (newOpen: boolean) => () => {
        setOpen(newOpen);
    };

    return (
        <AppBar
            position="fixed"
            enableColorOnDark
            sx={{
                boxShadow: 0,
                bgcolor: 'transparent',
                backgroundImage: 'none',
                mt: 'calc(var(--template-frame-height, 0px) + 28px)',
            }}
        >
            <Container maxWidth="lg">
                <StyledToolbar variant="dense" disableGutters>
                    <Box sx={{flexGrow: 1, display: 'flex', alignItems: 'center', px: 0}}>
                        <img src={logo} alt="Logo" style={{height: '62px', width: 'auto'}}/>
                        <Box sx={{flexGrow: 1, display: 'flex', alignItems: 'center', px: 0, justifyContent: 'center'}}>
                            <Stack direction="row" spacing={4} sx={{display: {xs: 'none', md: 'flex'}}}>
                                <Button variant="text" color="info" size="large" onClick={() => navigate("/")}>
                                    Stats
                                </Button>
                                <Button variant="text" color="info" size="large" onClick={() => navigate("/")}>
                                    Downloads
                                </Button>
                            </Stack>
                        </Box>
                    </Box>
                    <Box
                        sx={{
                            display: {xs: 'none', md: 'flex'},
                            gap: 1,
                            alignItems: 'center',
                        }}
                    >
                        <ColorModeIconDropdown/>
                    </Box>
                    <Box sx={{display: {xs: 'flex', md: 'none'}, gap: 1}}>
                        <ColorModeIconDropdown size="medium"/>
                        <IconButton aria-label="Menu button" onClick={toggleDrawer(true)}>
                            <MenuIcon/>
                        </IconButton>
                        <Drawer
                            anchor="top"
                            open={open}
                            onClose={toggleDrawer(false)}
                            PaperProps={{
                                sx: {
                                    top: 'var(--template-frame-height, 0px)',
                                },
                            }}
                        >
                            <Box sx={{p: 2, backgroundColor: 'background.default'}}>
                                <Box
                                    sx={{
                                        display: 'flex',
                                        justifyContent: 'flex-end',
                                    }}
                                >
                                    <IconButton onClick={toggleDrawer(false)}>
                                        <CloseRoundedIcon/>
                                    </IconButton>
                                </Box>
                                <MenuItem onClick={() => navigate("/")}>Stats</MenuItem>
                            </Box>
                        </Drawer>
                    </Box>
                </StyledToolbar>
            </Container>
        </AppBar>
    );
}
