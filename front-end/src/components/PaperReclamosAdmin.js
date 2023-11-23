import React from 'react'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { Paper } from '@mui/material';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import PopupEstado from '../components/PopupEstado';
import PopupImagen from './PopupImagen';

export default function PaperReclamosAdmin() {

    const [reclamosRows, setReclamosRows] = React.useState([])

    React.useEffect(()=>{    
        async function fetchDataReclamos() {
          const token = localStorage.getItem('token')
          const response = await fetch("/tpo_apis/reclamos/all", {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              "Authorization": `Bearer ${token}`
            },
          })
          if (!response.ok){
            throw new Error(await response.json())
          }
          const reclamos = await response.json()
          console.log(reclamos)
          setReclamosRows(reclamos)
        };
        fetchDataReclamos();
      },[])

      const handleDelete = async (idReclamo) => {
        try {
          const token = localStorage.getItem('token')
          const response = await fetch(`/tpo_apis/reclamos/${idReclamo}`, {
            method: "DELETE",
            headers: {
              "Content-Type": "application/json",
              "Authorization": `Bearer ${token}`
            },
          })
          if (!response.ok) {
            throw new Error(await response.text())
          }
          const res = await response.text()
          alert(res)
          window.location.reload(true)
        }
        catch (error) {
          alert(error)
          console.error(error);
        }
      };

    return (
    <Paper sx={{ my: 4, boxShadow: 10, borderRadius: 2, pt: 2 }}>
          <React.Fragment>
            <h2 className='titulo'>Reclamos</h2>
            <Table size="small">
              <TableHead>
                <TableRow>
                  <TableCell>Id Reclamo</TableCell>
                  <TableCell>Estado</TableCell>
                  <TableCell>Descripcion</TableCell>
                  <TableCell>Edificio</TableCell>
                  <TableCell>Lugar Reclamo</TableCell>
                  <TableCell>Imagen</TableCell>
                  <TableCell>Cambiar Estado</TableCell>
                  <TableCell align="right">Eliminar</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {reclamosRows.map((reclamo) => (
                  <TableRow key={reclamo.idReclamo}>
                    <TableCell>{reclamo.idReclamo}</TableCell>
                    <TableCell>{reclamo.estado}</TableCell>
                    <TableCell>{reclamo.descripcion}</TableCell>
                    <TableCell>{reclamo.edificio}</TableCell>
                    <TableCell>{reclamo.lugarReclamo}</TableCell>
                    <TableCell>
                      <PopupImagen idImagen={reclamo.idReclamo}/>
                    </TableCell>
                    <TableCell>
                      <PopupEstado idReclamo={reclamo.idReclamo}/>
                    </TableCell>
                    <TableCell align="right">
                      <div onClick={(e) => { handleDelete(reclamo.idReclamo) }}>
                        <DeleteOutlineIcon sx={{ ":hover": { cursor: 'pointer' } }} />
                      </div>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </React.Fragment>
        </Paper>
  )
}
