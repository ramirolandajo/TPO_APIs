import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DownloadIcon from '@mui/icons-material/Download';
import { ImageList, ImageListItem } from '@mui/material';

export default function PopupImagen(props) {

    const { idImagen } = props;
    const [open, setOpen] = React.useState(false);
    const [imageSourceUrl, setImageSourceUrl] = React.useState('');

    const handleClickOpen = async () => {
        setOpen(true);
        try {
            const token = localStorage.getItem('token')
            const response = await fetch(`/tpo_apis/imagenes/${idImagen}`, {
                method: "GET",
                headers: {
                    "Authorization": `Bearer ${token}`
                },
            })
            if (!response.ok) {
                throw new Error(await response.text())
            }
            const image = await response.blob()
            setImageSourceUrl(URL.createObjectURL(image))
        }
        catch (error) {
            alert(error)
            console.error(error);
        }
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <React.Fragment>
            <div onClick={handleClickOpen}>
                <DownloadIcon />
            </div>
            <Dialog open={open} onClose={handleClose}>
                <ImageList cols={1}>
                    <ImageListItem key={idImagen}>
                        <img
                            srcSet={imageSourceUrl}
                            src={imageSourceUrl}
                            alt={"IMAGEN"}
                            loading="lazy"
                        />
                    </ImageListItem>
                </ImageList>
                <DialogActions>
                    <Button onClick={handleClose}>Cerrar</Button>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    )
}


