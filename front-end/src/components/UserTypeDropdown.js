import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

export default function Dropdown() {
  const [tipoUsuario, setTipoUsuario] = React.useState('');

  const handleChange = (event) => {
    setTipoUsuario(event.target.value);
  };

  return (
    <Box sx={{ minWidth: 400 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Tipo de Usuario</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={tipoUsuario}
          label="Tipo de Usuario"
          onChange={handleChange}
        >
          <MenuItem value={10}>Dueño</MenuItem>
          <MenuItem value={20}>Inquilino</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
}