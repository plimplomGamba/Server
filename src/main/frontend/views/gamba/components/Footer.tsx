import * as React from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Stack from '@mui/material/Stack';
import GitHubIcon from '@mui/icons-material/GitHub';

export default function Footer() {
    return (
        <React.Fragment>
            <Divider/>
            <Container
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    py: {xs: 2, sm: 4},
                    textAlign: {sm: 'center', md: 'left'},
                }}
            >
                <Box
                    sx={{
                        display: 'flex',
                        justifyContent: 'space-between',
                        width: '100%',
                    }}
                >
                    <Stack
                        direction="row"
                        spacing={1}
                        useFlexGap
                        sx={{justifyContent: 'left', color: 'text.secondary', alignItems: 'center'}}
                    >
                        Contribute on Github
                        <IconButton
                            color="inherit"
                            size="small"
                            target="_blank"
                            href="https://github.com/orgs/plimplomGamba/repositories"
                            aria-label="GitHub"
                            sx={{alignSelf: 'center'}}
                        >
                            <GitHubIcon/>
                        </IconButton>
                    </Stack>
                </Box>
            </Container>
        </React.Fragment>
    );
}
