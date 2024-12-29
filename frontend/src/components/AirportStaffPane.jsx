import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import PK from "../img/PK.png";
import userIcon from "../img/user.png";

function AirportStaffPane() {
    const navigate = useNavigate();
    const [isCheckedIn, setIsCheckedIn] = useState(false);

    const handleCheckIn = () => {
        setIsCheckedIn(true);
    };

    const handleAccept = () => {
        setIsCheckedIn(false);
    };

    const handleDeny = () => {
        setIsCheckedIn(false);
    };

    return (
        <div className="main-container">
            <div className="page-header green">
                <div className="page-header-image">
                    <img src={PK} alt="PK logo" className="pklogo-header"/>
                </div>
                <div className="page-header-button">
                    <button className="header-button" onClick={() => navigate("/dashboard")}>
                    </button>
                </div>
            </div>
            <div className="staff-container">
                <div className="staff-heading">
                    <h1>Passenger Check-In</h1>
                </div>
                {!isCheckedIn && (
                    <div className="staff-form">
                        <h3>Input passenger's name and last name to proceed with check-in</h3>
                        <div className="staff-input-container">
                            <input type="text" placeholder="Name" className="staff-input"/>
                            <input type="text" placeholder="Last Name" className="staff-input"/>
                        </div>
                        <button className="staff-button green" onClick={handleCheckIn}>Check-In</button>
                    </div>
                )}
                {isCheckedIn && (
                    <div className="staff-card">
                        <div className="staff-card-container">
                            <div className="staff-card-image">
                                <img src={userIcon} alt="user-icon" className="user-icon"/>
                            </div>
                            <div className="staff-card-info">
                                <div className="passenger-info">
                                    <h3>Name</h3>
                                    <h4>placeholder</h4>
                                </div>
                                <div className="passenger-info">
                                    <h3>Date of birth</h3>
                                    <h4>placeholder</h4>
                                </div>
                                <div className="passenger-info">
                                    <h3>Nationality</h3>
                                    <h4>placeholder</h4>
                                </div>
                                <div className="passenger-info">
                                    <h3>Document Number</h3>
                                    <h4>placeholder</h4>
                                </div>
                                <div className="passenger-info">
                                    <h3>Flight ID</h3>
                                    <h4>placeholder</h4>
                                </div>
                            </div>
                        </div>
                        <div className="staff-card-buttons">
                            <button className="staff-card-button green" onClick={handleAccept}>ACCEPT</button>
                            <button className="staff-card-button red" onClick={handleDeny}>DENY</button>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}

export default AirportStaffPane;