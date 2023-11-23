import * as React from 'react';
import '../styles/AdminDashboard.css';
import NabvarDashboard from '../components/Navbars/NabvarDashboard';
import PopupEdificio from '../components/PopupEdificio';
import PopupUnidad from '../components/PopupUnidad';
import PopupEspacioComun from '../components/PopupEspacioComun';
import { decodeToken } from 'react-jwt';
import Footer from '../components/Footer';
import PaperEdificiosAdmin from '../components/PaperEdificiosAdmin';
import PaperReclamosAdmin from '../components/PaperReclamosAdmin';
import PaperUsuariosAdmin from '../components/PaperUsuariosAdmin';
import PaperEspaciosComunes from '../components/PaperEspaciosComunes';
import PaperUnidades from '../components/PaperUnidades';

export default function AdminDashboard() {
  return (
    <div className='main'>
      <NabvarDashboard/>
      <div className='mainDashboardHome'>
        <div className='funcionalidades'>
          <h1>Bienvenido al Dashboard {decodeToken(localStorage.getItem('token')).sub}</h1>
          <PopupEdificio/>
          <PopupUnidad/>
          <PopupEspacioComun/>
        </div>
        <PaperEdificiosAdmin/>
        <PaperReclamosAdmin/>
        <PaperUsuariosAdmin/>
        <PaperEspaciosComunes/>
        <PaperUnidades/>
      </div>
      <Footer title={"Pisos Picados"} pos={'sticky'}/>
    </div>
  )
}
