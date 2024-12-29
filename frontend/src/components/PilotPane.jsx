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
        </div>
    );
}

export default PilotPane;