import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
    const [credentials, setCredentials] = useState({ login: "", password: "" });
    const [error, setError] = useState("");
    const navigate = useNavigate();


    // async function for login (commented the backend endpoint attempt for testing reasons)
    const handleLogin = async () => {
        try {
            // navigate("/dashboard")
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
            <div classnName="left-pane"></div>
            <div className="right-pane">
                <div className="login-container-right">
                    <h2>Welcome to AMS PK</h2>
                    <div className="login-inputs">
                        <h3>Username</h3>
                        <input type="text" placeholder="Input username" onChange={(e) => setCredentials({...credentials, login: e.target.value})}/>
                    </div>
                    <div className="login-inputs">
                        <h3>Password</h3>
                        <input type="password" placeholder="Input password" onChange={(e) => setCredentials({...credentials, password: e.target.value})}/>
                    </div>
                    <button onClick={handleLogin} className="confirm-btn">Sign in</button>
                    {error && <p style={{color: "red"}}>{error}</p>}
                </div>
            </div>
        </div>
    );
}

export default Login;
