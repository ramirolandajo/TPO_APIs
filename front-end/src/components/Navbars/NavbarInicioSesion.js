import React from 'react'
import {Link} from "react-router-dom";
import "../../styles/Navbar.css";
import ApartmentIcon from '@mui/icons-material/Apartment';

function NavbarRegistro() {
  return (
    <div className='navbar'>
        <div className='leftSide'>         
         <ApartmentIcon sx={{width: '2em', height: '2em'}}/>
          <Link to={"/"}>
            <h2 className='title'>Pisos Picados</h2>
          </Link>
        </div>
        <div className='rightSide'>
            <Link to={"/"} className='navbarLink'>Home</Link>
            <Link to={"/registro"} className='navbarLink'>Registrarse</Link>
        </div>
    </div>
  )
}

export default NavbarRegistro;
