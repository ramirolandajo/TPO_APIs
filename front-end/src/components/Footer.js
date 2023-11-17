import * as React from 'react';
import PropTypes from 'prop-types';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';

function Copyright() {
  return (
    <Typography variant="body2" color="text.secondary" align="center" sx={{paddingBlockStart: '1em', color: 'white'}}>
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
      <Box component="footer" sx={{ bgcolor: 'transparent', py: 2, position: 'fixed', bottom: 0, width: '100vw', color: 'white'}}>
        <Container maxWidth="lg">
          <Typography variant="h5" align="center" gutterBottom>
            {title}
          </Typography>
          <Typography
            variant="subtitle1"
            align="center"
            color="white"
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

Footer.propTypes = {
  description: PropTypes.string.isRequired,
  title: PropTypes.string.isRequired,
};

export default Footer;