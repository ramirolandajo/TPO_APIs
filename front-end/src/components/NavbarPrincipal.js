import React from 'react'
import {Link} from "react-router-dom";
import "../styles/Navbar.css";
import ApartmentIcon from '@mui/icons-material/Apartment';

export default function NavbarPrincipal() {
  return (
    <nav className='navbar'>
        <div className='leftSide'>
          <ApartmentIcon sx={{width: '2em', height: '2em'}}/>
          <Link to={"/"}>
            <h1 className='title'>Pisos Picados</h1>
          </Link>
        </div>
        <div className='rightSide'>
            <Link to={"/login"} className='navbarLink'>Inicio de sesión</Link>
            <Link to={"/registro"} className='navbarLink'>Registrarse</Link>
        </div>
    </nav>
  )
}
