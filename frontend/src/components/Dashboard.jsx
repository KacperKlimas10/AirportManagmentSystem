import React, {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";
import PK from "../img/PK.png";
import staffIcon from "../img/persons-in-an-airport.png";
import pilotIcon from "../img/pilot-of-airplane.png";
import securityIcon from "../img/traveler-at-the-airport.png";
import adminIcon from "../img/airport-worker.png";


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
                <div className="dashboard-header-image">
                    <img src={PK} alt="PK logo" className="pklogo-header"/>
                </div>
                <div className="dashboard-header-button">
                    <button className="header-button" onClick={() => navigate("/account")}>
                    </button>
                </div>
            </div>
            <div className="dashboard-message">
                <p>Welcome to airport management system PK, {name}</p>
            </div>
            <div className="dashboard-buttons">
                <div className="dashboard-button-container">
                    <h2 className="button-heading">Staff</h2>
                    <button className="dashboard-button green" onClick={() => handleNavigation("staff")}>
                        <img src={staffIcon} alt="staffIcon" className="dashboard-button-icon"/>
                    </button>
                </div>
                <div className="dashboard-button-container">
                    <h2 className="button-heading">Pilot</h2>
                    <button className="dashboard-button blue" onClick={() => handleNavigation("staff")}>
                        <img src={pilotIcon} alt="pilotIcon" className="dashboard-button-icon"/>
                    </button>
                </div>
                <div className="dashboard-button-container">
                    <h2 className="button-heading">Security</h2>
                    <button className="dashboard-button yellow" onClick={() => handleNavigation("staff")}>
                        <img src={securityIcon} alt="securityIcon" className="dashboard-button-icon"/>
                    </button>
                </div>
                <div className="dashboard-button-container">
                    <h2 className="button-heading">Admin</h2>
                    <button className="dashboard-button red" onClick={() => handleNavigation("staff")}>
                        <img src={adminIcon} alt="adminIcon" className="dashboard-button-icon"/>
                    </button>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;
