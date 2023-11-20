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

  const [piso, setPiso] = React.useState('');
  const [numero, setNumero] = React.useState('');
  const [edificio, setEdificio] = React.useState('');
  const [duenio, setDuenio] = React.useState('');
  const [inquilino, setInquilino] = React.useState('');

  async function handleSubmit(event) {
    try {
      event.preventDefault();
      if (piso === '' || numero === '' || edificio === '' || duenio === '') {
        alert('Error al crear la unidad (llene los campos necesarios)')
        throw new Error('Error al crear la unidad (llene los campos necesarios)');
      }
      const idEdificio = parseInt(edificio)
      const idDuenio = parseInt(duenio)
      var idInquilino = parseInt(inquilino)
      if (isNaN(idInquilino)) {
        idInquilino = null;
      }

      const data = { piso, numero, idDuenio, idInquilino, idEdificio }
      console.log(data);
      const token = localStorage.getItem('token')

      const response = await fetch("/tpo_apis/unidades/", {
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
      console.log("Unidad Creada!");
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
    setPiso('');
    setNumero('');
    setEdificio('');
    setDuenio('');
    setInquilino('');
  };

  return (
    <React.Fragment>
      <Button variant="contained" onClick={handleClickOpen}>
        Crear Unidad
      </Button>
      <Dialog open={open} onClose={handleClose} maxWidth={'sm'} fullWidth>
        {creado ? (
          <div>
            <DialogTitle>Unidad creada</DialogTitle>
            <DialogContent>
              <DialogContentText>
              Unidad creada con éxito!
              </DialogContentText>
              <Button onClick={handleClose} variant='outlined' sx={{ my: 2 }}>Aceptar</Button>
            </DialogContent>
          </div>
        ) :
          (
            <div>
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
                  id="edificio"
                  label="Id del Edificio"
                  type="number"
                  value={edificio}
                  onChange={(e) => setEdificio(e.target.value)}
                  fullWidth
                  variant="standard"
                />
                <TextField
                  autoFocus
                  margin="dense"
                  id="duenio"
                  label="Id del Duenio"
                  type="number"
                  value={duenio}
                  onChange={(e) => setDuenio(e.target.value)}
                  fullWidth
                  variant="standard"
                />
                <TextField
                  autoFocus
                  margin="dense"
                  id="inquilino"
                  label="Id del Inquilino"
                  type="number"
                  value={inquilino}
                  onChange={(e) => setInquilino(e.target.value)}
                  fullWidth
                  variant="standard"
                />
              </DialogContent>
              <DialogActions>
                <Button onClick={handleClose}>Cancelar</Button>
                <Button onClick={handleSubmit}>Crear</Button>
              </DialogActions>
            </div>
          )};
      </Dialog>
    </React.Fragment>
  );
}