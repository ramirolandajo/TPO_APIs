import React from 'react';
import '../styles/AdminDashboard.css';
import DomainAddIcon from '@mui/icons-material/DomainAdd';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router';

export default function AdminDashboardEdificio() {

  const [enviado,setEnviado] = React.useState(false)
  const [direccion, setDireccion] = React.useState('');
  const nav = useNavigate();

  function handleOfChange(event){
    setDireccion(event.target.value);
  }

  function handleSubmit(event){
    event.preventDefault();
    setEnviado(true);
  }

    function volver(){
      nav('/AdminDashboard')
    }

  return (
    <div className='mainDashboardEdificio'>
      <div className='caja'>
        {enviado ? (
          <div>
            <p>Los datos se enviaron correctamente!</p>
            <br/>
            <br/>
            <button onClick={volver}>Aceptar</button>
          </div>
        ):(
          <form onSubmit={handleSubmit}>
          <DomainAddIcon className='icon' fontSize='large'/>
          <h2>Creación de edificio</h2>
          <TextField id="standard-basic" label="Dirección" variant="standard" value={direccion} onChange={handleOfChange}/>
          <br/>
          <br/>
          <Button variant="outlined" className='boton' type='submit'>Guardar</Button>
        </form>
        )}
      </div>
    </div>
  )
}
