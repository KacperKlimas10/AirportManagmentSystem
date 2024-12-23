import React, {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";

function Dashboard() {

    const [name, setName] = useState();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchName = async () => {
            try {
                const response = await fetch("http://localhost:8081/auth/verifytoken", {
                    method: "GET",
                    credentials: "include",
                });
                if (response.ok) {
                    const fetchedName = await response.text();
                    setName(fetchedName);
                } else {
                    console.error("Failed to fetch name");
                }
            } catch (error) {
                console.error("Error fetching name:", error);
            }
        };

        fetchName();
    }, []);

    const handleNavigation = (role) => {
        navigate(`/${role}`);
    };


    // nav handlers
    return (
        <div className="dashboard-main">
            <div className="dashboard-header">
                <div className="top-right">
                    <button onClick={() => navigate("/account")}>
                        <i className="bi bi-person-circle"></i>
                    </button>
                </div>
            </div>
            <div className="dashboard-text">
                <p>Witaj, {name}</p>
            </div>
            <div className="dashboard-body">
                <div className="icon-grid">
                    <button onClick={() => handleNavigation("security")}>
                        <h1>xd</h1>
                        <p>Security Pane</p>
                    </button>
                    <button onClick={() => handleNavigation("admin")}>Administrator Pane</button>
                    <button onClick={() => handleNavigation("pilot")}>Pilot Pane <i className="bi bi-airplane"></i>
                    </button>
                    <button onClick={() => handleNavigation("staff")}>Airport Staff Pane</button>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;
