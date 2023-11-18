import * as React from 'react';
import '../styles/AdminDashboard.css';
import NabvarAdminDashboard from '../components/Navbars/NabvarAdminDashboardHome';
import PopupEdificio from '../components/PopupEdificio';
import PopupUnidad from '../components/PopupUnidad';
import PopupEspacioComun from '../components/PopupEspacioComun';

export default function AdminDashboard() {

  const [user, setUser] = React.useState('');

   React.useEffect(()=>{
    async function fetchData() {
      const token = localStorage.getItem('token')
      const response = await fetch("/tpo_apis/usuarios/1", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
      })
      if (!response.ok){
        throw new Error("Error en el metodo GET")
      }
      const usuario = await response.json()
      setUser(usuario)
      console.log(usuario)
    }
    fetchData();
  },[])

  return (
    <div className='main'>
      <NabvarAdminDashboard/>
      <div className='mainDashboardHome'>
        <h1>Bienvenido al dashboard de Admin {user.usuario}</h1>
        <div className='button-wrapper'>
          <PopupEdificio/>
          <PopupUnidad/>
          <PopupEspacioComun/>
        </div>
      </div>
    </div>
  )
}
