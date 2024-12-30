import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./AuthProvider";
import PK from '../img/PK.png';

function Login() {
    const [credentials, setCredentials] = useState({ login: "", password: "" });
    const [error, setError] = useState("");
    const navigate = useNavigate();
    const { login: authenticate } = useAuth();

    const handleLogin = async () => {
        try {
            const response = await fetch("http://localhost:8081/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(credentials),
                credentials: "include",
            });

            if (response.ok) {
                const cookies = document.cookie.split("; ");
                const authTokenCookie = cookies.find(cookie => cookie.startsWith("authToken="));
                if (authTokenCookie) {
                    const token = authTokenCookie.split("=")[1];
                    localStorage.setItem("authToken", token); // Save JWT (json web token)
                }
                authenticate(); // Set isAuthenticated to true
                navigate("/dashboard");
            } else {
                setError("Invalid username or password");
            }
        } catch (err) {
            setError("An error occurred. Please try again.");
        }
    };

    return (
        <div className="login">
            <div className="left-pane">
                <img src={PK} alt="PK logo" className="pklogo"/>
            </div>
            <div className="right-pane">
                <div className="login-container-right">
                    <h1 className="login-header">LOG INTO AMS</h1>
                    <div className="login-inputs-container">
                        <input type="text" placeholder="Input username" className="login-inputs"
                               onChange={(e) => setCredentials({...credentials, login: e.target.value})}/>
                        <input type="password" placeholder="Input password" className="login-inputs"
                               onChange={(e) => setCredentials({...credentials, password: e.target.value})}/>
                    </div>
                    <button onClick={handleLogin} className="confirm-btn">LOGIN</button>
                    {error && <p style={{color: "red"}}>{error}</p>}
                    <p className="notice">*if you don't have an account, please contact your system administrator</p>
                </div>
            </div>
        </div>
    );
}

export default Login;