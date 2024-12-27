import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    useEffect(() => {
        const checkAuth = async () => {
            try {
                const response = await fetch("http://localhost:8081/auth/login", {
                    method: "POST",
                    credentials: "include",
                });
                setIsAuthenticated(response.ok);
            } catch (error) {
                setIsAuthenticated(false);
            }
        };

        const checkAuthAsync = async () => {
            await checkAuth();
        };

        checkAuthAsync();
    }, []);

    return (
        <AuthContext.Provider value={{ isAuthenticated }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);