import './App.css';
import Login from './pages/Login';
import Home from './pages/Home'
import Register from './pages/Register';
import Error404 from './pages/Error404';
import Footer from './components/Footer';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom"

function App() {
  return (
    <div className="App">
      <Router> 
        <Routes> 
          <Route path='/' exact Component={Home}/>
          <Route path='/login' exact Component={Login}/>
          <Route path='/registro' exact Component={Register}/>
          <Route path='*' exact Component={Error404}/>
        </Routes>
        <Footer description={'Aplicacion Web para el manejo de reclamos sobre distintas unidades en un conjunto de edificios'} title={'Pisos Picados'}/>
      </Router>
    </div>
  );
}

export default App;
