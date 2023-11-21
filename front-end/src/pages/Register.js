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
import { createTheme, ThemeProvider } from '@mui/material/styles';
import PersonIcon from '@mui/icons-material/Person';
import { InputLabel, Select, MenuItem, FormControl } from '@mui/material';
import { useNavigate } from 'react-router';
import NavbarRegistro from '../components/Navbars/NavbarRegistro';
import Footer from '../components/Footer';
import "../styles/Register.css";

const defaultTheme = createTheme();

export default function Register() {

  const [nombre, setNombre] = React.useState('');
  const [apellido, setApellido] = React.useState('');
  const [cuil, setCuil] = React.useState('');
  const [usuario, setUsuario] = React.useState('');
  const [password, setPassword] = React.useState('');
  const [tipoUsuario, setTipoUsuario] = React.useState('');

  const nav = useNavigate();

  function navegarHome() {
      nav('/')
  }

  const handleSubmit = async (event) => {
    try {
      event.preventDefault();
      if (nombre === '' || apellido === '' || cuil === '' || usuario === '' || password === '' || tipoUsuario === '') {
        throw new Error('Error al registrarse (llene los campos necesarios)');
      }
      const nombreCompleto = nombre + " " + apellido
      const data = {usuario, password, cuil, nombreCompleto, tipoUsuario}
      console.log(data)

      const response = await fetch("/tpo_apis/usuarios/signUp",{
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
      })
      if (!response.ok) {
        throw new Error(await response.text())
      }
      console.log("Usuario registrado con exito!")
      console.log(await response.json())

      alert("Registro exitoso!")
      navegarHome();
    }
    catch (error) {
      alert(error);
      console.error(error);
    }
  };


  return (
    <div className='registro'>
      <NavbarRegistro />
      <div className='main'>
        <ThemeProvider theme={defaultTheme}>
          <Container component="main" maxWidth="xs">
            <CssBaseline />
            <Box
              sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
              }}
            >
              <PersonIcon fontSize='large' className='personIcon' color='primary'/>
              <Typography component="h1" variant="h5">
                Registro
              </Typography>
              <Box component="form" noValidate onSubmit={handleSubmit} sx={{pt: 3}}>
                <Grid container spacing={2}>
                  <Grid item xs={12} sm={6}>
                    <TextField
                      autoComplete="given-name"
                      name="nombre"
                      required
                      fullWidth
                      id="nombre"
                      label="Nombre"
                      autoFocus
                      value={nombre}
                      onChange={(e)=>setNombre(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <TextField
                      required
                      fullWidth
                      id="apellido"
                      label="Apellido"
                      name="apellido"
                      autoComplete="family-name"
                      value={apellido}
                      onChange={(e)=>setApellido(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      required
                      fullWidth
                      name="cuil"
                      label="CUIL"
                      type="cuil"
                      id="cuil"
                      autoComplete="cuil"
                      value={cuil}
                      onChange={(e)=>setCuil(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      required
                      fullWidth
                      id="usuario"
                      label="Usuario"
                      name="usuario"
                      autoComplete="usuario"
                      value={usuario}
                      onChange={(e)=>setUsuario(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      required
                      fullWidth
                      name="password"
                      label="Contraseña"
                      type="password"
                      id="password"
                      autoComplete="nueva-contraseña"
                      value={password}
                      onChange={(e)=>setPassword(e.target.value)}
                    />
                  </Grid>
                  <Grid item cs={12}>
                    {/* <Dropdown/> */}
                    <Box sx={{ minWidth: 400 }}>
                      <FormControl fullWidth>
                        <InputLabel id="demo-simple-select-label">Tipo de Usuario</InputLabel>
                        <Select
                          labelId="demo-simple-select-label"
                          id="demo-simple-select"
                          value={tipoUsuario}
                          label="Tipo de Usuario"
                          onChange={(e)=>setTipoUsuario(e.target.value)}
                        >
                          <MenuItem value={"DUENIO"}>Dueño</MenuItem>
                          <MenuItem value={"INQUILINO"}>Inquilino</MenuItem>
                        </Select>
                      </FormControl>
                    </Box>
                  </Grid>
                  <Grid item xs={12}>
                    <FormControlLabel
                      control={<Checkbox value="allowExtraEmails" color="primary" />}
                      label="Quiero recibir promociones de marketing y actualizaciones por correo electrónico."
                    />
                  </Grid>
                </Grid>
                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 3, mb: 2 }}
                >
                  Registrarse
                </Button>
                <Grid container justifyContent="flex-end">
                  <Grid item>
                    <Link href="/login" variant="body2">
                      Ya tienes una cuenta? Inicia sesión
                    </Link>
                  </Grid>
                </Grid>
              </Box>
            </Box>
          </Container>
        </ThemeProvider>
      </div>
      <Footer title={"Pisos Picados"} pos={'absolute'}/>
    </div>
  );
}
