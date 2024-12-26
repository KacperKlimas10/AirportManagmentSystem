import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from './AuthProvider';

const PrivateRoute = ({ element: Component }) => {
    const { isAuthenticated } = useAuth();

    return isAuthenticated ? <Component /> : <Navigate to="/dashboard" />;
};

export default PrivateRoute;