import NavbarPrincipal from '../components/Navbars/NavbarPrincipal';
import React from 'react';
import Background from '../components/Background';
import '../styles/Home.css'
import HomeWelcome from '../components/HomeWelcome';
import WaterDropGrid from '../components/WaterDropGrid';

export default function Home() {

  return (
    <div className='home'>
      <NavbarPrincipal/>
      <main className='main-wrapper'>
        {/* <Background/> */}
        <HomeWelcome/>
        <WaterDropGrid/>
      </main>
    </div>
  )
}
