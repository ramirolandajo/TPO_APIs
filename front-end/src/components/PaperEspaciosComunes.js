import React from 'react'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { Paper } from '@mui/material';

export default function PaperEspaciosComunes() {

  const [espaciosRows, setEspaciosRows] = React.useState([])

  React.useEffect(()=>{
    async function fetchDataEspaciosComunes() {
      const token = localStorage.getItem('token')
      const response = await fetch("/tpo_apis/espacios_comunes/", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
      })
      if (!response.ok){
        throw new Error(await response.json())
      }
      const espacios = await response.json()
      console.log(espacios);
      setEspaciosRows(espacios)
    }

    fetchDataEspaciosComunes();
  },[])

  return (
    <Paper sx={{ my: 4, boxShadow: 10, borderRadius: 2, pt: 2 }}>
          <React.Fragment>
            <h2 className='titulo'>Espacios Comunes</h2>
            <Table size="small">
              <TableHead>
                <TableRow>
                  <TableCell>Id Espacio Comun</TableCell>
                  <TableCell>Edificio</TableCell>
                  <TableCell>Piso</TableCell>
                  <TableCell>Descripcion</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {espaciosRows.map((espacio) => (
                  <TableRow key={espacio.idEspacioComun}>
                    <TableCell>{espacio.idEspacioComun}</TableCell>
                    <TableCell>{espacio.idEdificio}</TableCell>
                    <TableCell>{espacio.piso}</TableCell>
                    <TableCell>{espacio.descripcion}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </React.Fragment>
        </Paper>
  )
}
