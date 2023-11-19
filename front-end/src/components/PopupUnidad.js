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
  const [numero, setNumero] = React.useState('');
  const [idEdificio, setIdEdificio] = React.useState('');
  const [idDuenio, setIdDuenio] = React.useState('');
  const [idInquilino, setIdInquilino] = React.useState('');

  async function handleSubmit(event) {
    try {
      event.preventDefault();
      const data = { piso, numero, idDuenio, idInquilino, idEdificio }
      console.log(data);
      const token = localStorage.getItem('token')
      console.log(token);
      const response = await fetch("/tpo_apis/unidades/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(data)
      })
      if (!response.ok) {
        throw new Error("Error en la creacion del unidad")
      }
      console.log("Unidad Creada!");
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
            value={piso}
            onChange={(e) => setPiso(e.target.value)}
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="numero"
            label="Numero"
            type="number"
            value={numero}
            onChange={(e) => setNumero(e.target.value)}
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="idEdificio"
            label="Id del Edificio"
            type="number"
            value={idEdificio}
            onChange={(e) => setIdEdificio(e.target.value)}
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="idDuenio"
            label="Id del Duenio"
            type="number"
            value={idDuenio}
            onChange={(e) => setIdDuenio(e.target.value)}
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="idInquilino"
            label="Id del Inquilino"
            type="number"
            value={idInquilino}
            onChange={(e) => setIdInquilino(e.target.value)}
            fullWidth
            variant="standard"
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