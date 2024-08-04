import React from 'react';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import HomePage from './Pages/HomePage';
import LoginPage from './Pages/LoginPage';
import ProjectPage from './Pages/ProjectPage';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css'
import TaskDetailsPage from './Components/TaskDetailsPage';
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/project" element={<ProjectPage/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
