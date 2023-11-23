import React from 'react'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { Paper } from '@mui/material';

export default function PaperUnidades() {

  const [unidadesRows, setUnidadesRows] = React.useState([])

  React.useEffect(()=>{
    async function fetchDataEspaciosComunes() {
      const token = localStorage.getItem('token')
      const response = await fetch("/tpo_apis/unidades/", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
      })
      if (!response.ok){
        throw new Error(await response.json())
      }
      const unidades = await response.json()
      console.log(unidades);
      setUnidadesRows(unidades)
    }

    fetchDataEspaciosComunes();
  },[])

  return (
    <Paper sx={{ my: 4, boxShadow: 10, borderRadius: 2, pt: 2 }}>
          <React.Fragment>
            <h2 className='titulo'>Unidades</h2>
            <Table size="small">
              <TableHead>
                <TableRow>
                  <TableCell>Id Unidad</TableCell>
                  <TableCell>Numero</TableCell>
                  <TableCell>Piso</TableCell>
                  <TableCell>Id Dueño</TableCell>
                  <TableCell>Id Inquilino</TableCell>
                  <TableCell>Id Edificio</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {unidadesRows.map((unidad) => (
                  <TableRow key={unidad.idUnidad}>
                    <TableCell>{unidad.idUnidad}</TableCell>
                    <TableCell>{unidad.numero}</TableCell>
                    <TableCell>{unidad.piso}</TableCell>
                    <TableCell>{unidad.idDuenio}</TableCell>
                    <TableCell>{unidad.idInquilino === null ? "Sin inquilino" : unidad.idInquilino}</TableCell>
                    <TableCell>{unidad.idEdificio}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </React.Fragment>
        </Paper>
  )
}
