import '../styles/AdminDashboard.css';
import NabvarAdminDashboard from '../components/NabvarAdminDashboardHome';
import { useNavigate } from 'react-router';

export default function AdminDashboard() {

  const nav = useNavigate();

  function navegar() {
    nav('/AdminDashboard/edificio')
  }

  return (
    <div className='main'>
      <NabvarAdminDashboard/>
      <div className='mainDashboardHome'>
        <h1>Hola</h1>
        <div>
          <button onClick={navegar}>Crear edificio</button>
        </div>
      </div>
    </div>
  )
}
