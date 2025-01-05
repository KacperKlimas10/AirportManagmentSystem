import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./AuthProvider";
import PK from "../img/PK.png";
import staffIcon from "../img/persons-in-an-airport.png";
import pilotIcon from "../img/pilot-of-airplane.png";
import securityIcon from "../img/traveler-at-the-airport.png";
import adminIcon from "../img/airport-worker.png";

function Dashboard() {
    const [name, setName] = useState();
    const [role, setRole] = useState();
    const navigate = useNavigate();
    const { logout } = useAuth();

    useEffect(() => {
        const fetchName = async () => {
            try {
                const response = await fetch("http://localhost:8081/auth", {
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

    useEffect(() => {
        const fetchRole = async () => {
            try {
                const response = await fetch("http://localhost:8082/panel/admin", {
                    method: "GET",
                    credentials: "include",
                });

                if (response.ok) {
                    const fetchedRole = await response.json(); // Parse as JSON
                    setRole(fetchedRole.trim()); // Ensure no extra spaces
                    console.log("Fetched role:", fetchedRole);
                } else {
                    const errorText = await response.text();
                    console.error("Failed to fetch role", response.status, errorText);
                }
            } catch (error) {
                console.error("Error fetching role:", error);
            }
        };

        fetchRole();
    }, []);

    const handleNavigation = (role) => {
        navigate(`/${role}`);
    };

    return (
        <div className="main-container">
            <div className="page-header">
                <div className="page-header-image">
                    <img src={PK} alt="PK logo" className="pklogo-header"/>
                </div>
                <div className="page-header-button">
                    <button className="header-button" onClick={logout}></button>
                </div>
            </div>
            <div className="dashboard-message">
                <p>Welcome to airport management system PK {name}</p>
            </div>
            <div className="dashboard-buttons">
                {(role === "administrator" || role === "obsługa_techniczna") && (
                    <div className="dashboard-button-container">
                        <h2 className="button-heading">Staff</h2>
                        <button className="dashboard-button green" onClick={() => handleNavigation("staff")}>
                            <img src={staffIcon} alt="staffIcon" className="dashboard-button-icon"/>
                        </button>
                    </div>
                )}
                {(role === "administrator" || role === "kontrola_lotów") && (
                    <div className="dashboard-button-container">
                        <h2 className="button-heading">Pilot</h2>
                        <button className="dashboard-button blue" onClick={() => handleNavigation("pilot")}>
                            <img src={pilotIcon} alt="pilotIcon" className="dashboard-button-icon"/>
                        </button>
                    </div>
                )}
                {(role === "administrator" || role === "security") && (
                    <div className="dashboard-button-container">
                        <h2 className="button-heading">Security</h2>
                        <button className="dashboard-button yellow" onClick={() => handleNavigation("security")}>
                            <img src={securityIcon} alt="securityIcon" className="dashboard-button-icon"/>
                        </button>
                    </div>
                )}
                {role === "administrator" && (
                    <div className="dashboard-button-container">
                        <h2 className="button-heading">Admin</h2>
                        <button className="dashboard-button red" onClick={() => handleNavigation("admin")}>
                            <img src={adminIcon} alt="adminIcon" className="dashboard-button-icon"/>
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
}

export default Dashboard;