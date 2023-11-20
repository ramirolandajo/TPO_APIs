import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

export default function FormDialog() {
  const [open, setOpen] = React.useState(false);
  const [piso, setPiso] = React.useState('');
  const [descripcion, setDescripcion] = React.useState('');
  const [edificio, setEdificio] = React.useState('');
  const [creado, setCreado] = React.useState(false);

  async function handleSubmit(event) {
    try {
      event.preventDefault();
      if (piso === '' || descripcion === '' || edificio === '') {
        alert('Error en la creación del espacio común!')
        throw new Error('Error en la creación del espacio común!');
      }
      const data = { piso, descripcion, edificio }
      const token = localStorage.getItem('token')
      const response = await fetch("/tpo_apis/espacios_comunes/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(data)
      })
      if (!response.ok) {
        throw new Error("Error creando el espacio común")
      }
      console.log("El espacio comun ha sido creado con exito!")
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
    setCreado(false);
  };

  return (
    <React.Fragment>
      <Button variant="contained" onClick={handleClickOpen}>
        Crear Espacio Comun
      </Button>
      <Dialog open={open} onClose={handleClose} maxWidth={'sm'} fullWidth>
        {creado ? (
          <div>
            <DialogTitle>Edificio creado</DialogTitle>
            <DialogContent>
              <DialogContentText>
                Edificio creado con éxito!
              </DialogContentText>
              <Button onClick={handleClose} variant='outlined' sx={{ my: 2 }}>Aceptar</Button>
            </DialogContent>
          </div>
        ) : (
          <div>
            <DialogTitle>Crear Espacio Comun</DialogTitle>
            <DialogContent>
              <DialogContentText>
                Ingrese los datos del espacio comun.
              </DialogContentText>
              <TextField
                autoFocus
                margin="dense"
                id="piso"
                label="Piso"
                type="number"
                fullWidth
                variant="standard"
                value={piso}
                onChange={(e) => setPiso(e.target.value)}
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
                onChange={(e) => setDescripcion(e.target.value)}
              />
              <TextField
                autoFocus
                margin="dense"
                id="idEdificio"
                label="Id del Edificio"
                type="number"
                fullWidth
                variant="standard"
                value={edificio}
                onChange={(e) => setEdificio(e.target.value)}
              />
            </DialogContent>
            <DialogActions>
              <Button onClick={handleClose}>Cancelar</Button>
              <Button onClick={handleSubmit}>Crear</Button>
            </DialogActions>
          </div>
        )}
      </Dialog>
    </React.Fragment>
  );
}