import * as React from 'react'
import PopupReclamo from '../components/PopupReclamo'
import NavbarDashboard from '../components/Navbars/NabvarDashboard';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import '../styles/UserDashboard.css';
import { decodeToken } from 'react-jwt';
import { Paper } from '@mui/material';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';

export default function UserDashboard() {

  const [rows, setRows] = React.useState([])
  
  React.useEffect(()=>{
    async function fetchData() {
      const token = localStorage.getItem('token')
      const decodedToken = decodeToken(token)

      const response = await fetch(`/tpo_apis/reclamos/allFromUser/${decodedToken.id}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
      })
      if (!response.ok){
        throw new Error(await response.text())
      }
      const res = await response.json()
      setRows(res)
      console.log(res)
    }
    fetchData();
  },[])

  const handleDelete = async (idReclamo) => {
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
  }

  return (
    <div className='main'>
      <NavbarDashboard/>
      <div className='mainDashboardHome'>
        <div className='funcionalidades'>
          <h1>Bienvenido al dashboard {decodeToken(localStorage.getItem('token')).sub}!</h1>
          <PopupReclamo />
        </div>
        <Paper sx={{my: 4, boxShadow: 10, borderRadius: 2, pt: 2}}>
          <React.Fragment>
            <h2 className='titulo-reclamos'>Mis reclamos</h2>
            <Table size="small">
              <TableHead>
                <TableRow>
                  <TableCell>Estado</TableCell>
                  <TableCell>Descripcion</TableCell>
                  <TableCell>Edificio</TableCell>
                  <TableCell>Lugar Reclamo</TableCell>
                  <TableCell>Imagen</TableCell>
                  <TableCell align="right">Eliminar</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <TableRow key={row.idReclamo}>
                    <TableCell>{row.estado}</TableCell>
                    <TableCell>{row.descripcion}</TableCell>
                    <TableCell>{row.edificio}</TableCell>
                    <TableCell>{row.lugarReclamo}</TableCell>
                    <TableCell >
                      <div onClick={(e) => {handleDelete(row.idReclamo)}}>

                      </div>
                    </TableCell>
                    <TableCell align="right">
                      <div onClick={(e) => {handleDelete(row.idReclamo)}}>
                        <DeleteOutlineIcon sx={{":hover":{cursor: 'pointer'}}}/>
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
