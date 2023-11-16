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

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const cargaEdificio = () => {
    // Tomo valor    
    // Componente para crear edificio
    const crearUnidad = crearEdificio('unidad');

    if(crearUnidad){
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
        Crear Unidad
      </Button>
      <Dialog open={open} onClose={handleClose} maxWidth={'sm'} fullWidth>
        <DialogTitle>Crear Unidad</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Ingrese los datos de la unidad.
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="piso"
            label="Piso"
            type="text"
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="numero"
            label="Numero"
            type="number"
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="idDuenio"
            label="Id del Duenio"
            type="number"
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="idInquilino"
            label="Id del Inquilino"
            type="number"
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="idEdificio"
            label="Id del Edificio"
            type="number"
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
}