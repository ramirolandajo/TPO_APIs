import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { decodeToken } from 'react-jwt';

export default function FormDialog() {
    const [open, setOpen] = React.useState(false);
    const [creado, setCreado] = React.useState(false);

    const [descripcion, setDescripcion] = React.useState('');
    const [edificio, setEdificio] = React.useState('');
    const [unidad, setUnidad] = React.useState('');
    const [espacioComun, setEspacioComun] = React.useState('');
    const [imagen, setImagen] = React.useState(undefined);

    async function handleSubmit(event) {
        try {
            event.preventDefault();
            if (descripcion === '' || edificio === '') {
                alert('Error al crear el reclamo (llene los campos necesarios)')
                throw new Error('Error al crear el reclamo (llene los campos necesarios');
            }
            const idEdificio = parseInt(edificio)
            var idUnidad = parseInt(unidad)
            var idEspacioComun = parseInt(espacioComun)
            if (isNaN(idUnidad)) {
                idUnidad = null;
            }
            else if (isNaN(idEspacioComun)){
                idEspacioComun = null;
            }
            const estado = "NUEVO"
            const token = localStorage.getItem('token')
            const decodedToken = decodeToken(token)
            const idUsuario = decodedToken.id
            const data = { estado, descripcion, idEdificio, idUsuario, idUnidad, idEspacioComun }
            console.log(data);
            
            const response = await fetch("/tpo_apis/reclamos/", {
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
            
            // fetch para la imagen
            const formData = new FormData();
            formData.append('archivo', imagen);
            const imageResponpse = await fetch("/tpo_apis/imagenes/", {
                method: "POST",
                headers: {
                    "Authorization": `Bearer ${token}`
                },
                body: formData
            })
            if (!imageResponpse.ok) {
                throw new Error(await imageResponpse.text())
            }
            console.log("Reclamo Creado!");
            setCreado(true);
        }
        catch (error) {
            alert(error);
            console.error(error);
        }
    };

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        setCreado(false);
        setDescripcion('');
        setEdificio('');
        setUnidad('');
        setEspacioComun('');
    };

    return (
        <React.Fragment>
            <Button variant="contained" onClick={handleClickOpen}>
                Crear Reclamo
            </Button>
            <Dialog open={open} onClose={handleClose} maxWidth={'sm'} fullWidth>
                {creado ? (
                    <div>
                        <DialogTitle>Reclamo creado</DialogTitle>
                        <DialogContent>
                            <DialogContentText>
                                Reclamo creado con éxito!
                            </DialogContentText>
                            <Button onClick={handleClose} variant='outlined' sx={{ my: 2 }}>Aceptar</Button>
                        </DialogContent>
                    </div>
                ) 
                :
                (
                    <div>
                        <DialogTitle>Crear Reclamo</DialogTitle>
                        <DialogContent>
                            <DialogContentText>
                                Ingrese los datos del reclamo.
                            </DialogContentText>
                            <TextField
                                autoFocus
                                margin="dense"
                                id="descripcion-reclamo"
                                label="Descripcion Reclamo"
                                type="text"
                                fullWidth
                                variant="standard"
                                value={descripcion}
                                inputProps={{maxLength: 50}}
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
                                value={edificio}
                                onChange={(e) => setEdificio(e.target.value)}
                            />
                            <TextField
                                autoFocus
                                margin="dense"
                                id="Id-unidad"
                                label="Id Unidad"
                                type="text"
                                fullWidth
                                variant="standard"
                                value={unidad}
                                onChange={(e) => setUnidad(e.target.value)}
                            />
                            <TextField
                                autoFocus
                                margin="dense"
                                id="Id-espacioComun"
                                label="Id Espacio Comun"
                                type="text"
                                fullWidth
                                variant="standard"
                                value={espacioComun}
                                onChange={(e) => setEspacioComun(e.target.value)}
                            />
                            <TextField
                                autoFocus
                                margin="dense"
                                id="imagen"
                                label="Imagen"
                                type="file"
                                fullWidth
                                variant="standard"
                                onChange={(e) => setImagen(e.target.files[0])}
                            />
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={handleClose}>Cancelar</Button>
                            <Button onClick={handleSubmit}>Crear</Button>
                        </DialogActions>
                    </div>)};
            </Dialog>
        </React.Fragment>
    );
}