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

    const [estado, setEstado] = React.useState('');
    const [descripcion, setDescripcion] = React.useState('');
    const [usuario, setUsuario] = React.useState('');
    const [edificio, setEdificio] = React.useState('');
    const [unidad, setUnidad] = React.useState('');
    const [espacioComun, setEspacioComun] = React.useState('');
    const [imagen, setImagen] = React.useState(undefined);

    async function handleSubmit(event) {
        try {
            event.preventDefault();
            if (estado === '' || descripcion === '' || usuario === '' || edificio === '') {
                alert('Error al crear el reclamo (llene los campos necesarios)')
                throw new Error('Error al crear el reclamo (llene los campos necesarios');
            }
            const idEdificio = parseInt(edificio)
            const idUsuario = parseInt(usuario)
            var idUnidad = parseInt(unidad)
            var idEspacioComun = parseInt(espacioComun)
            if (isNaN(idUnidad)) {
                idUnidad = null;
            }
            else if (isNaN(idEspacioComun)){
                idEspacioComun = null;
            }

            const data = { estado, descripcion, idEdificio, idUsuario, idUnidad, idEspacioComun }
            console.log(data);
            const token = localStorage.getItem('token')
            
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
        setEstado('');
        setDescripcion('');
        setUsuario('');
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
                ) :
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
                                    value={edificio}
                                    onChange={(e) => setEdificio(e.target.value)}
                                />
                                <TextField
                                    autoFocus
                                    margin="dense"
                                    id="Id-usuario"
                                    label="Id Usuario"
                                    type="text"
                                    fullWidth
                                    variant="standard"
                                    value={usuario}
                                    onChange={(e) => setUsuario(e.target.value)}
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