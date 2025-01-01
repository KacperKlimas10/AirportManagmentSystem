import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import PK from "../img/PK.png";

function SecurityPane() {
    const navigate = useNavigate();

    // nav handlers
    return (
        <div className="main-container">
            <div className="page-header yellow">
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
                    <h1>Security Panel</h1>
                </div>
                <div className="form-wrapper">
                    <div className="form-list">
                        <div className="form-list-item">
                            <h2>Gate Number:</h2>
                            <input type="text" placeholder="Input Number" className="input-box"/>
                        </div>
                        <div className="form-list-item">
                            <h2>Gate Outage ID:</h2>
                            <input type="text" placeholder="Input ID" className="input-box"/>
                        </div>
                        <div className="form-list-item">
                            <h2>Outage Date:</h2>
                            <input type="text" placeholder="Input Date" className="input-box"/>
                        </div>
                        <div className="form-list-item">
                            <h2>Gate Status:</h2>
                            <input type="text" placeholder="Input Gate Status" className="input-box"/>
                        </div>
                        <div className="form-list-item">
                            <h2>Description:</h2>
                            <input type="text" placeholder="Description" className="input-box"/>
                        </div>
                    </div>
                    <div className="single-centered-button">
                        <button className="button-styled orange">REPORT</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SecurityPane;