import './App.css';
import React from 'react';
import Login from './pages/Login';
import Home from './pages/Home'
import Register from './pages/Register';
import Error404 from './pages/Error404';
import Footer from './components/Footer';
import AdminDashboardHome from './pages/AdminDashboardHome';
import UserDashboardHome from './pages/UserDashboardHome';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom"

function App() {
  return (
      <div className="App">
        <Router> 
          <Routes> 
            <Route path='/' exact Component={Home}/>
            <Route path='/login' exact Component={Login}/>
            <Route path='/registro' exact Component={Register}/>
            <Route path='/AdminDashboard' exact Component={AdminDashboardHome}/>
            <Route path='/UserDashboard' exact Component={UserDashboardHome}/>
            <Route path='*' exact Component={Error404}/>
          </Routes>
        </Router>
        <Footer title={"Pisos Picados"}/>
      </div>
  );
}

export default App;
