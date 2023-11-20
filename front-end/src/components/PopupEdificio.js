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
  const [creado, setCreado] = React.useState(false);

  const [direccion, setDireccion] = React.useState('');

  async function handleSubmit(event) {
    try {
      event.preventDefault();
      if (direccion === '') {
        alert('Error al crear el edificio (llene los campos necesarios)')
        throw new Error('Error al crear el edificio (llene los campos necesarios)');
      }
      const data = { direccion }
      console.log(data);
      const token = localStorage.getItem('token')
      
      const response = await fetch("/tpo_apis/edificios/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(data)
      })
      if (!response.ok) {
        throw new Error(await response.text())
      }
      console.log("Edificio Creado!");
      setCreado(true);
    }
    catch (error) {
      alert(error)
      console.error(error);
    }
  };

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    setCreado(false);
    setDireccion('');
  };


  return (
    <React.Fragment>
      <Button variant="contained" onClick={handleClickOpen}>
        Crear Edificio
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
        ) :
          (
            <div>
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
                  onChange={(e) => setDireccion(e.target.value)}
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