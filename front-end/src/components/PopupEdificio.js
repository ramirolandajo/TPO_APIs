import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
// import { crearEdificio } from './createBuilding';

export default function FormDialog() {
  const [open, setOpen] = React.useState(false);
  const [direccion, setDireccion] = React.useState('');
  
  async function handleSubmit(event){
    try {
      event.preventDefault();
      const data = {direccion}
      console.log(data);
      const token = localStorage.getItem('token')
      console.log(token);
      const authHeader = "Bearer " + token
      console.log(authHeader)
      const response = await fetch("http://localhost:8080/tpo_apis/edificios/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": authHeader
        },
        body: JSON.stringify(data)
      })
      if (!response.ok) {
        throw new Error("Error en la creacion del edificio")
      }
      console.log("Edificio Creado!");
    }
    catch (error) {
      console.error(error);
    }
  };

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  // const cargaEdificio = () => {
  //   // Tomo valor    
  //   // Componente para crear edificio
  //   const crearEdifio = crearEdificio('edificio');

  //   if(crearEdifio){
  //       // Cierro        
  //       handleClose();
  //   }else{
  //     alert("NO se pudo ingresar la informacion");
  //     return false;
  //   }
  // }

  return (
    <React.Fragment>
      <Button variant="contained" onClick={handleClickOpen}>
        Crear Edificio
      </Button>
      <Dialog open={open} onClose={handleClose} maxWidth={'sm'} fullWidth>
        <DialogTitle>Crear Edificio</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Ingrese los datos del edificio.
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="direccion-edificio"
            label="Direccion Edificio"
            type="text"
            fullWidth
            variant="standard"
            value={direccion}
            onChange={(e)=>setDireccion(e.target.value)}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancelar</Button>
          <Button onClick={handleSubmit}>Crear</Button>
        </DialogActions>
      </Dialog>
    </React.Fragment>
  );
  // Cambiar handleClose por validacion y carga de envio
}