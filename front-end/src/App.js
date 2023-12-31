import './App.css';
import React from 'react';
import Login from './pages/Login';
import Home from './pages/Home'
import Register from './pages/Register';
import Error404 from './pages/Error404';
import AdminDashboard from './pages/AdminDashboard';
import UserDashboard from './pages/UserDashboard';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom"

function App() {
  return (
      <div className="App">
        <Router> 
          <Routes> 
            <Route path='/' exact Component={Home}/>
            <Route path='/login' exact Component={Login}/>
            <Route path='/registro' exact Component={Register}/>
            <Route path='/AdminDashboard' exact Component={AdminDashboard}/>
            <Route path='/UserDashboard' exact Component={UserDashboard}/>
            <Route path='*' exact Component={Error404}/>
          </Routes>
        </Router>
      </div>
  );
}

export default App;
