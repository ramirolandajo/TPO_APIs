import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import PersonIcon from '@mui/icons-material/Person';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import NavbarPrincipal from '../components/NavbarPrincipal';

const defaultTheme = createTheme();

export default function SignIn() {
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      username: data.get('username'),
      password: data.get('password'),
    });
  };

  const [usuario, setUsuario] = React.useState("");
  const [contraseña, setContraseña] = React.useState("");

  function handleUsuarioChange(event){
    setUsuario(event.target.value);
  }

  function handleContraseñaChange(event){
    setContraseña(event.target.value);
  }

  return (
    <div>
      <div className='main'>
        <NavbarPrincipal/>
        <ThemeProvider theme={defaultTheme}>
          <Container component="main" maxWidth="xs">
            <CssBaseline />
            <Box
              sx={{
                marginTop: 8,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
              }}
            >
              <PersonIcon fontSize='large' className='personIcon' color='primary'/>
              <Typography component="h1" variant="h5">
                Inicio de sesión
              </Typography>
              <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="usuario"
                  label="Usuario"
                  name="usuario"
                  autoComplete="usuario"
                  value={usuario}
                  onChange={handleUsuarioChange}
                  autoFocus
                />
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  name="contraseña"
                  label="Contraseña"
                  type="Contraseña"
                  id="contraseña"
                  value={contraseña}
                  onChange={handleContraseñaChange}
                  autoComplete="contraseña recurrente"
                />
                <FormControlLabel
                  control={<Checkbox value="remember" color="primary" />}
                  label="Recuerdame"
                />
                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 3, mb: 2 }}
                >
                  Registrarse
                </Button>
                <Grid container>
                  <Grid item xs>
                    <Link href="#" variant="body2">
                      Olvidaste tu contraseña?
                    </Link>
                  </Grid>
                  <Grid item>
                    <Link href="/registro" variant="body2">
                      {"No tienes una cuenta? Registrate!"}
                    </Link>
                  </Grid>
                </Grid>
              </Box>
            </Box>
          </Container>
        </ThemeProvider>
      </div>
    </div>
  );
}