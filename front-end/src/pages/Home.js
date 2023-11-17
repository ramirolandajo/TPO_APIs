import NavbarPrincipal from '../components/Navbars/NavbarPrincipal';
import React from 'react';
import Background from '../components/Background';
import '../styles/Home.css'

export default function Home() {

  return (
    <div className='home'>
      <NavbarPrincipal/>
      <Background/>
    </div>
  )
}
