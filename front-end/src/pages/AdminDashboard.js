import * as React from 'react';
import '../styles/AdminDashboard.css';
import NabvarDashboard from '../components/Navbars/NabvarDashboard';
import PopupEdificio from '../components/PopupEdificio';
import PopupUnidad from '../components/PopupUnidad';
import PopupEspacioComun from '../components/PopupEspacioComun';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { Paper } from '@mui/material';
import { decodeToken } from 'react-jwt';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';

export default function AdminDashboard() {

  const [edificiosRows, setEdificiosRows] = React.useState([])
  const [reclamosRows, setReclamosRows] = React.useState([])

   React.useEffect(()=>{
    async function fetchDataEdificios() {
      const token = localStorage.getItem('token')
      const response = await fetch("/tpo_apis/edificios/", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
      })
      if (!response.ok){
        throw new Error(await response.json())
      }
      const edificios = await response.json()
      console.log(edificios)
      setEdificiosRows(edificios)
    }

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
    fetchDataEdificios();
    fetchDataReclamos();
  },[])

  function handleDelete() {

  };

  return (
    <div className='main'>
      <NabvarDashboard/>
      <div className='mainDashboardHome'>
        <div className='funcionalidades'>
          <h1>Bienvenido al Dashboard {decodeToken(localStorage.getItem('token')).sub}</h1>
          <PopupEdificio/>
          <PopupUnidad/>
          <PopupEspacioComun/>
        </div>
        <Paper sx={{ my: 4, boxShadow: 10, borderRadius: 2, pt: 2 }}>
          <React.Fragment>
            <h2 className='titulo-reclamos'>Edificios</h2>
            <Table size="small">
              <TableHead>
                <TableRow>
                  <TableCell>Id Edificio</TableCell>
                  <TableCell>Direccion</TableCell>
                  <TableCell>Cantidad Unidades</TableCell>
                  <TableCell>Cantidad Espacios Comunes</TableCell>
                  <TableCell align="right">Eliminar</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {edificiosRows.map((edificio) => (
                  <TableRow key={edificio.idEdificio}>
                    <TableCell>{edificio.idEdificio}</TableCell>
                    <TableCell>{edificio.direccion}</TableCell>
                    <TableCell>{edificio.unidades}</TableCell>
                    <TableCell>{edificio.espaciosComunes}</TableCell>
                    <TableCell align="right">
                      <div onClick={(e) => { handleDelete(edificio.idReclamo) }}>
                        <DeleteOutlineIcon sx={{ ":hover": { cursor: 'pointer' } }} />
                      </div>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </React.Fragment>
        </Paper>

        <Paper sx={{ my: 4, boxShadow: 10, borderRadius: 2, pt: 2 }}>
          <React.Fragment>
            <h2 className='titulo-reclamos'>Reclamos</h2>
            <Table size="small">
              <TableHead>
                <TableRow>
                  <TableCell>Id Reclamo</TableCell>
                  <TableCell>Estado</TableCell>
                  <TableCell>Descripcion</TableCell>
                  <TableCell>Edificio</TableCell>
                  <TableCell>Lugar Reclamo</TableCell>
                  <TableCell>Imagen</TableCell>
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
                      <div onClick={(e) => { handleDelete(reclamo.idReclamo) }}>

                      </div>
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
      </div>
    </div>
  )
}
