import React from 'react'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { Paper } from '@mui/material';

export default function PaperUsuariosAdmin() {

  const [usuariosRows, setUsuariosRows] = React.useState([])

  React.useEffect(()=>{
    async function fetchDataUsuarios() {
      const token = localStorage.getItem('token')
      const response = await fetch("/tpo_apis/usuarios/", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
      })
      if (!response.ok){
        throw new Error(await response.json())
      }
      const usuarios = await response.json()
      setUsuariosRows(usuarios)
    }

    fetchDataUsuarios();
  },[])

  return (
    <Paper sx={{ my: 4, boxShadow: 10, borderRadius: 2, pt: 2 }}>
          <React.Fragment>
            <h2 className='titulo-reclamos'>Usuarios</h2>
            <Table size="small">
              <TableHead>
                <TableRow>
                  <TableCell>Id Usuario</TableCell>
                  <TableCell>Usuario</TableCell>
                  <TableCell>CUIL</TableCell>
                  <TableCell>Nombre Completo</TableCell>
                  <TableCell>Tipo de Usuario</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {usuariosRows.map((usuario) => (
                  <TableRow key={usuario.idUsuario}>
                    <TableCell>{usuario.idUsuario}</TableCell>
                    <TableCell>{usuario.usuario}</TableCell>
                    <TableCell>{usuario.cuil}</TableCell>
                    <TableCell>{usuario.nombreCompleto}</TableCell>
                    <TableCell>{usuario.tipoUsuario}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </React.Fragment>
        </Paper>
  )
}
