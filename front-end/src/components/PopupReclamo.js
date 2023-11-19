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
    const [estado, setEstado] = React.useState('');
    const [descripcion, setDescripcion] = React.useState('');
    const [idUsuario, setIdUsuario] = React.useState('');
    const [idEdificio, setIdEdificio] = React.useState('');
    const [idUnidad, setIdUnidad] = React.useState('');
    const [idEspacioComun, setIdEspacioComun] = React.useState('');
    const [imagen, setImagen] = React.useState(null);

    async function handleSubmit(event) {
        try {
            event.preventDefault();
            const data = { estado, descripcion, idEdificio, idUsuario, idUnidad, idEspacioComun, imagen}
            console.log(data);
            const token = localStorage.getItem('token')
            console.log(token);
            const response = await fetch("/tpo_apis/reclamos/", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(data)
            })
            if (!response.ok) {
                throw new Error("Error en la creacion del reclamo")
            }
            console.log("Reclamo Creado!");
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
                Crear Reclamo
            </Button>
            <Dialog open={open} onClose={handleClose} maxWidth={'sm'} fullWidth>
                <DialogTitle>Crear Reclamo</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Ingrese los datos del reclamo.
                    </DialogContentText>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="estado-reclamo"
                        label="Estado Reclamo"
                        type="text"
                        fullWidth
                        variant="standard"
                        value={estado}
                        onChange={(e) => setEstado(e.target.value)}
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="descripcion-reclamo"
                        label="Descripcion Reclamo"
                        type="text"
                        fullWidth
                        variant="standard"
                        value={descripcion}
                        onChange={(e) => setDescripcion(e.target.value)}
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="Id-edificio"
                        label="Id Edificio"
                        type="text"
                        fullWidth
                        variant="standard"
                        value={idEdificio}
                        onChange={(e) => setIdEdificio(e.target.value)}
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="Id-usuario"
                        label="Id Usuario"
                        type="text"
                        fullWidth
                        variant="standard"
                        value={idUsuario}
                        onChange={(e) => setIdUsuario(e.target.value)}
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="Id-unidad"
                        label="Id Unidad"
                        type="text"
                        fullWidth
                        variant="standard"
                        value={idUnidad}
                        onChange={(e) => setIdUnidad(e.target.value)}
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="Id-espacioComun"
                        label="Id Espacio Comun"
                        type="text"
                        fullWidth
                        variant="standard"
                        value={idEspacioComun}
                        onChange={(e) => setIdEspacioComun(e.target.value)}
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="imagen"
                        label="Imagen"
                        type="file"
                        fullWidth
                        variant="standard"
                        value={imagen}
                        onChange={(e) => setImagen(e.target.value)}
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