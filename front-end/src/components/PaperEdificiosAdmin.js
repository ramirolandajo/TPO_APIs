import React from 'react'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { Paper } from '@mui/material';

export default function PaperEdificiosAdmin() {

  const [edificiosRows, setEdificiosRows] = React.useState([])

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

    fetchDataEdificios();
  },[])

  return (
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
                </TableRow>
              </TableHead>
              <TableBody>
                {edificiosRows.map((edificio) => (
                  <TableRow key={edificio.idEdificio}>
                    <TableCell>{edificio.idEdificio}</TableCell>
                    <TableCell>{edificio.direccion}</TableCell>
                    <TableCell>{edificio.unidades}</TableCell>
                    <TableCell>{edificio.espaciosComunes}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </React.Fragment>
        </Paper>
  )
}
