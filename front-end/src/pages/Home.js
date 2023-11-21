import NavbarPrincipal from '../components/Navbars/NavbarPrincipal';
import React from 'react';
import '../styles/Home.css'
import HomeWelcome from '../components/HomeWelcome';
import WaterDropGrid from '../components/WaterDropGrid';
import Footer from '../components/Footer';

export default function Home() {

  return (
    <div className='home'>
      <NavbarPrincipal/>
      <main className='main-wrapper'>
        <HomeWelcome/>
        <WaterDropGrid/>
      </main>
      <Footer title={"Pisos Picados"} pos={'absolute'}/>
    </div>
  )
}
