import React, { createContext, useContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const navigate = useNavigate();

    const login = () => {
        setIsAuthenticated(true);
    };

    const logout = async () => {
        try {
            await fetch("http://localhost:8081/auth/logout", {
                method: "POST",
                credentials: "include",
            });
            setIsAuthenticated(false);
            navigate("/login");
        } catch (error) {
            console.error("Error logging out:", error);
        }
    };

    const refreshToken = async () => {
        try {
            const response = await fetch("http://localhost:8081/auth/refresh", {
                method: "POST",
                credentials: "include",
            });

            if (response.ok) {
                setIsAuthenticated(true);
            } else {
                setIsAuthenticated(false);
                navigate("/login");
            }
        } catch (error) {
            console.error("Error refreshing token:", error);
            setIsAuthenticated(false);
            navigate("/login");
        }
    };

    useEffect(() => {
        refreshToken();
    }, []);

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout, refreshToken }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);