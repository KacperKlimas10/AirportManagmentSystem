import React, { useEffect } from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from './AuthProvider';

const PrivateRoute = ({ element: Component }) => {
    const { isAuthenticated, refreshToken } = useAuth();

    useEffect(() => {
        refreshToken();
    }, [refreshToken]);

    return isAuthenticated ? <Component /> : <Navigate to="/login" />;
};

export default PrivateRoute;