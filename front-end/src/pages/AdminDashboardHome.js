import '../styles/AdminDashboard.css';
import NabvarAdminDashboard from '../components/NabvarAdminDashboardHome';
import PopupEdificio from '../components/PopupEdificio';
import PopupUnidad from '../components/PopupUnidad';
import PopupEspacioComun from '../components/PopupEspacioComun';

export default function AdminDashboard() {

  return (
    <div className='main'>
      <NabvarAdminDashboard/>
      <div className='mainDashboardHome'>
        <h1>Bienvenido al dashboard de Admin</h1>
        <div className='button-wrapper'>
          <PopupEdificio/>
          <PopupUnidad/>
          <PopupEspacioComun/>
        </div>
      </div>
    </div>
  )
}
