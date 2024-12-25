import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import './App.css';
import "bootstrap-icons/font/bootstrap-icons.css";
import PrivateRoute from "./components/PrivateRoute";
import { AuthProvider } from "./components/AuthProvider";

function App() {
    return (
        <Router>
            <AuthProvider>
                <Routes>
                    <Route path="/" element={<Navigate to="/login" />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/dashboard" element={<PrivateRoute element={Dashboard} />} />
                </Routes>
            </AuthProvider>
        </Router>
    );
}

export default App;