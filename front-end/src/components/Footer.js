import * as React from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';

function Copyright() {
  return (
    <Typography variant="body2" color="text.secondary" align="center" sx={{paddingBlockStart: '1em', color: '#2f2f30'}}>
      {'Copyright © '}
      <Link color="inherit" href="/">
        Pisos Picados
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

function Footer(props) {
  const { description, title } = props;

  return (
    <footer className='footer'>
      <Box component="footer" sx={{ bgcolor: '#F2F1F1', py: 2, position: 'fixed', bottom: 0, width: '100vw', color: '#2f2f30'}}>
        <Container maxWidth="lg">
          <Typography variant="h5" align="center" gutterBottom>
            {title}
          </Typography>
          <Typography
            variant="subtitle1"
            align="center"
            component="p"
          >
            {description}
          </Typography>
          <Copyright/>
        </Container>
      </Box>
    </footer>
  );
}

export default Footer;