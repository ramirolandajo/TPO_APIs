import React from 'react'
import PopupReclamo from '../components/PopupReclamo'
import NavbarAdminDashboardHome from '../components/Navbars/NabvarAdminDashboardHome';

export default function UserDashboard() {
  return (
    <div className='main'>
      <NavbarAdminDashboardHome/>
      <div className='mainDashboardHome'>
        <h1>Bienvenido al dashboard de Usuario!</h1>
        <div className='button-wrapper'>
          <PopupReclamo/>
        </div>
      </div>
    </div>
    )
}
