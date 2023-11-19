import React from 'react'
import PopupReclamo from '../components/PopupReclamo'

export default function UserDashboard() {
  return (
    <div className='main'>
      <div className='mainDashboardHome'>
        <h1>Bienvenido al dashboard de Usuario!</h1>
        <div className='button-wrapper'>
          <PopupReclamo/>
        </div>
      </div>
    </div>
    )
}
