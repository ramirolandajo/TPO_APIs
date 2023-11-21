import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import EditIcon from '@mui/icons-material/Edit';
import { MenuItem, InputLabel, FormControl, Box, Select } from '@mui/material';

export default function PopupEstado(props) {

    const {idReclamo} = props;
    const [open, setOpen] = React.useState(false);
    const [estado, setEstado] = React.useState('')

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        setEstado('');
    };

    const handleEdit = async () => {
        try {
          const token = localStorage.getItem('token')
          console.log(estado)
          console.log(idReclamo);
          const response = await fetch(`/tpo_apis/reclamos/${idReclamo}`,{
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
              "Authorization": `Bearer ${token}`
            },
            body: estado
          })
    
          if (!response.ok) {
            throw new Error(await response.text())
          }
          const res = await response.json()
          alert("Estado actualizado con exito")
          console.log(res)
          handleClose();
        }
        catch (error) {
          alert(error)
          console.error(error);
        }
      };

    return (
        <React.Fragment>
            <div onClick={handleClickOpen}>
                <EditIcon />
            </div>
            <Dialog open={open} onClose={handleClose} maxWidth={'sm'} fullWidth>
                <DialogTitle>Editar estado de reclamo</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Ingrese el estado del reclamo.
                    </DialogContentText>
                    <Box sx={{ minWidth: 400}}>
                        <FormControl fullWidth>
                            <InputLabel sx={{mt: 5}} id="demo-simple-select-label">Estado</InputLabel>
                            <Select
                                labelId="demo-simple-select-label"
                                id="demo-simple-select"
                                value={estado}
                                label="Estado"
                                sx={{mt: 5}}
                                onChange={(e) => setEstado(e.target.value)}
                            >
                                <MenuItem value={"ABIERTO"}>ABIERTO</MenuItem>
                                <MenuItem value={"EN PROCESO"}>EN PROCESO</MenuItem>
                                <MenuItem value={"DESESTIMADO"}>DESESTIMADO</MenuItem>
                                <MenuItem value={"ANULADO"}>ANULADO</MenuItem>
                                <MenuItem value={"TERMINADO"}>TERMINADO</MenuItem>
                            </Select>
                        </FormControl>
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancelar</Button>
                    <Button onClick={handleEdit}>Editar</Button>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    )
}


