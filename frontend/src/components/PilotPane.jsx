import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import PK from "../img/PK.png";

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
                <div className="form-wrapper">
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
            </div>
        </div>
    );
}

export default PilotPane;