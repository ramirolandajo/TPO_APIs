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
import NavbarInicioSesion from '../components/Navbars/NavbarInicioSesion.js';
import '../styles/InicioSesion.css'
import { useNavigate } from 'react-router';
import { isExpired, decodeToken } from 'react-jwt';

const defaultTheme = createTheme();

export default function SignIn() {

  const [usuario, setUsuario] = React.useState("");
  const [password, setPassword] = React.useState("");

  const nav = useNavigate();

  function navegarAdminDashboard() {
    nav('/AdminDashboard')
  }

  function navegarUserDashboard() {
    nav('/UserDashboard')
  }

  const handleSubmit = async (event) => {
    try {
      event.preventDefault();
      const data = {usuario, password}
      console.log(data);
      const response = await fetch("/auth/login", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
      })
      if (!response.ok) {
        throw new Error(await response.text())
      }
      const token = await response.text()
      localStorage.setItem('token', token)
      const decodedToken = decodeToken(token);

      if (!isExpired(token)) {
        console.log(decodedToken)
        if (decodedToken.rol === "ADMIN") {
          navegarAdminDashboard();
        }
        else {
          navegarUserDashboard();
        }
      }
    }
    catch (error) {
      alert(error);
      console.error(error);
    }
  }

  function handleUsuarioChange(event) {
    setUsuario(event.target.value);
  }

  function handleContraseñaChange(event) {
    setPassword(event.target.value);
  }



  return (
      <div className='main'>
        <NavbarInicioSesion />
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
              <PersonIcon fontSize='large' className='personIcon' color='primary' />
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
                  name="password"
                  label="Contraseña"
                  type="password"
                  id="contraseña"
                  value={password}
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
                  onClick={handleSubmit}
                >
                  Iniciar Sesión
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
  );
}