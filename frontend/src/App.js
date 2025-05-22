import React from "react";
import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import './App.css';
import "bootstrap-icons/font/bootstrap-icons.css";
import PrivateRoute from "./components/PrivateRoute";
import {AuthProvider} from "./components/AuthProvider";
import AirportStaffPane from "./components/AirportStaffPane";
import AdminPane from "./components/AdminPane";
import SecurityPane from "./components/SecurityPane";
import PilotPane from "./components/PilotPane";

function App() {
    return (
        <Router>
            <AuthProvider>
                <Routes>
                    <Route path="/" element={<Navigate to="/login" />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/dashboard" element={<PrivateRoute element={Dashboard} />} />
                    <Route path="/staff" element={<PrivateRoute element={AirportStaffPane} />} />
                    <Route path="/admin" element={<PrivateRoute element={AdminPane} />} />
                    <Route path="/security" element={<PrivateRoute element={SecurityPane} />} />
                    <Route path="/pilot" element={<PrivateRoute element={PilotPane} />} />
                </Routes>
            </AuthProvider>
        </Router>
    );
}

export default App;