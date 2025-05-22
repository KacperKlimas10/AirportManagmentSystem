import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import PK from "../img/PK.png";
import userIcon from "../img/user.png";

const PANEL_SERVICE = process.env.REACT_APP_PANEL_SERVICE

function AirportStaffPane() {
    const navigate = useNavigate();
    const [isCheckedIn, setIsCheckedIn] = useState(false);
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [passenger, setPassenger] = useState(null);
    const [error, setError] = useState("");
    const [checkInStatus, setCheckInStatus] = useState("");

    const handleCheckIn = async () => {
        try {
            const response = await fetch(`${process.env.REACT_APP_PANEL_SERVICE}/panel/staff/passenger?name=${name}&surname=${surname}`,{
                method: "GET",
                credentials: "include",
            });
            if (response.ok) {
                const passengerData = await response.json();
                setPassenger(passengerData);
                setIsCheckedIn(true);
                setError(""); // Clear any previous error
                console.log(passengerData);
            } else if (response.status === 404) {
                setError("Passenger is invalid");
                setPassenger(null);
                setIsCheckedIn(false);
            } else {
                console.error("Failed to fetch passenger data");
            }
        } catch (error) {
            console.error("Error fetching passenger data:", error);
        }
    };



    const handleAccept = async () => {
        try {
            const response = await fetch(`${process.env.REACT_APP_PANEL_SERVICE}/panel/staff/passenger?name=${name}&surname=${surname}`, {
                method: "PATCH",
                credentials: "include",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    checkInStatus: "odprawiony"
                })
            });
            if (response.status === 204) {
                setCheckInStatus("odprawiony");
                setError("Check-in successful");
            } else if (response.status === 404) {
                setError("Passenger is invalid");
                setIsCheckedIn(false);
            } else {
                console.error("Failed to check-in the passenger");
            }
        } catch (error) {
            console.error("Check-in error:", error);
        }
        setIsCheckedIn(false);
    };

    const handleDeny = async () => {
        try {
            const response = await fetch(`${process.env.REACT_APP_PANEL_SERVICE}/panel/staff/passenger?name=${name}&surname=${surname}`, {
                method: "PATCH",
                credentials: "include",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    checkInStatus: "odmowa"
                })
            });
            if (response.status === 204) {
                setCheckInStatus("odmowa");
                setError("Passenger denied check-in");
            } else if (response.status === 404) {
                setError("Passenger is invalid");
                setIsCheckedIn(false);
            } else {
                console.error("Failed to check-in the passenger");
            }
        } catch (error) {
            console.error("Check-in error:", error);
        }
        setIsCheckedIn(false);
    };

    const formatDate = (timestamp) => {
        const date = new Date(timestamp); // Convert milliseconds to date
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
        const day = String(date.getDate()).padStart(2, '0');
        return `${day}.${month}.${year}`; // Format the date as YYYY.MM.DD
    };

    function messageColor(){
        if(checkInStatus === "odprawiony"){
            return "green";
        } else {
            return "red";
        }
    }

    return (
        <div className="main-container">
            <div className="page-header green">
                <div className="page-header-image">
                    <img src={PK} alt="PK logo" className="pklogo-header"/>
                </div>
                <div className="page-header-button">
                    <button className="header-button header-button-back" onClick={() => navigate("/dashboard")}>
                    </button>
                </div>
            </div>
            <div className="container-middle">
                <div className="headingInfo">
                    <h1>Passenger Check-In</h1>
                </div>
                {!isCheckedIn && (
                    <div className="staff-form">
                        <h3>Input passenger's name and last name to proceed with check-in</h3>
                        <div className="staff-input-container">
                            <input
                                type="text"
                                placeholder="Name"
                                className="input-box"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                            <input
                                type="text"
                                placeholder="Last Name"
                                className="input-box"
                                value={surname}
                                onChange={(e) => setSurname(e.target.value)}
                            />
                        </div>
                        <button className="staff-button green" onClick={handleCheckIn}>Check-In</button>
                        {error !== null && <p style={{color: messageColor()}}>{error}</p>}
                    </div>
                )}
                {isCheckedIn && passenger && (
                    <div className="staff-card">
                        <div className="staff-card-container">
                            <div className="staff-card-image">
                                <img
                                    src={passenger.photoBase64 ? `data:image/png;base64,${passenger.photoBase64}` : userIcon}
                                    alt="user-icon"
                                    className="user-icon"
                                />
                            </div>
                            <div className="staff-card-info">
                                <div className="passenger-info">
                                    <h3>Name</h3>
                                    <h4>{passenger.name} {passenger.surname}</h4>
                                </div>
                                <div className="passenger-info">
                                    <h3>Date of birth</h3>
                                    <h4>{formatDate(passenger.birthDate)}</h4>
                                </div>
                                <div className="passenger-info">
                                    <h3>Nationality</h3>
                                    <h4>{passenger.nationality}</h4>
                                </div>
                                <div className="passenger-info">
                                    <h3>Document Number</h3>
                                    <h4>{passenger.documentNumber}</h4>
                                </div>
                            </div>
                        </div>
                        <div className="staff-card-buttons">
                            <button className="button-styled green" onClick={handleAccept}>ACCEPT</button>
                            <button className="button-styled red" onClick={handleDeny}>DENY</button>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}

export default AirportStaffPane;