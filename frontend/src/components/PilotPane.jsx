import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import PK from "../img/PK.png";
import planeIcon from "../img/images.png"

function PilotPane() {
    const navigate = useNavigate();

    // nav handlers
    return (
        <div className="main-container">
            <div className="page-header blue">
                <div className="page-header-image">
                    <img src={PK} alt="PK logo" className="pklogo-header"/>
                </div>
                <div className="page-header-button">
                    <button className="header-button" onClick={() => navigate("/dashboard")}>
                    </button>
                </div>
            </div>
            <div className="container-middle">
                <div className="headingInfo">
                    <h1>Pilot Panel</h1>
                </div>
                <div className="plane-wrapper">
                    <div className="form-wrapper-pilot">
                        <div className="form-list">
                            <div className="form-list-item">
                                <h2>Plane ID:</h2>
                                <input type="text" placeholder="Input Plane ID" className="input-box"/>
                            </div>
                        </div>
                        <div className="single-centered-button">
                            <button className="button-styled blue">CHECK</button>
                        </div>
                    </div>
                    <div className="plane-info">
                        <div className="plane-info-photo">
                            <img src={planeIcon} alt="plane" className="plane-icon"/>
                        </div>
                        <div className="plane-info-list">
                            <ol className="plane-data">
                                <li>Destination:</li>
                                <li>Starting point:</li>
                                <li>Departure hour:</li>
                                <li>Seat count:</li>
                                <li>Plane Serial Number:</li>
                            </ol>
                        </div>
                        <div className="weather-map"></div>
                        <div className="weather-data"></div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default PilotPane;