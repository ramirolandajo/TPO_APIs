import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { crearEdificio } from './createBuilding';

export default function FormDialog() {
  const [open, setOpen] = React.useState(false);
  const [piso, setPiso] = React.useState('');
  const [descripcion, setDescripcion] = React.useState('');
  const [idEdificio, setIdEdificio] = React.useState('');

  async function handleSubmit(event) {
    try {
      event.preventDefault();
      const data = {piso, descripcion, idEdificio}
      const token = localStorage.getItem('token')
      const authHeader = "Bearer " + token
      const response = await fetch("/tpo_apis/espacios_comunes/",{
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": authHeader  
        },
        body: JSON.stringify(data)
      })
      if (!response.ok) {
        throw new Error("Error creando la unidad")
      }
      console.log("La unidad ha sido creada con exito!")
      console.log(await response.json())
    }
    catch (error) {
      console.error(error)
    }
  }

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  // const cargaEdificio = () => {
  //   // Tomo valor    
  //   // Componente para crear edificio
  //   const crearEspacio = crearEdificio('espacioComun');

  //   if(crearEspacio){
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
        Crear Espacio Comun
      </Button>
      <Dialog open={open} onClose={handleClose} maxWidth={'sm'} fullWidth>
        <DialogTitle>Crear Espacio Comun</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Ingrese los datos del espacio comun.
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="idEdificio"
            label="Id del Edificio"
            type="number"
            fullWidth
            variant="standard"
            value={idEdificio}
            onChange={(e)=>setIdEdificio(e.target.value)}
          />
          <TextField
            autoFocus
            margin="dense"
            id="piso"
            label="Piso"
            type="number"
            fullWidth
            variant="standard"
            value={piso}
            onChange={(e)=>setPiso(e.target.value)}
          />
          <TextField
            autoFocus
            margin="dense"
            id="descripcion"
            label="Descripcion"
            type="text"
            fullWidth
            variant="standard"
            value={descripcion}
            onChange={(e)=>setDescripcion(e.target.value)}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancelar</Button>
          <Button onClick={handleSubmit}>Crear</Button>
        </DialogActions>
      </Dialog>
    </React.Fragment>
  );
}