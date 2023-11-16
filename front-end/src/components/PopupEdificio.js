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

  async function handleSubmit(){

  };

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const cargaEdificio = () => {
    // Tomo valor    
    // Componente para crear edificio
    const crearEdifio = crearEdificio('edificio');

    if(crearEdifio){
        // Cierro        
        handleClose();
    }else{
      alert("NO se pudo ingresar la informacion");
      return false;
    }
  }

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
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancelar</Button>
          <Button onClick={cargaEdificio}>Crear</Button>
        </DialogActions>
      </Dialog>
    </React.Fragment>
  );
  // Cambiar handleClose por validacion y carga de envio
}