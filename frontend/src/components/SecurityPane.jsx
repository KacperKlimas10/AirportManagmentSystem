import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import PK from "../img/PK.png";

function SecurityPane() {
    const navigate = useNavigate();
    const [description, setDescription] = useState("");
    const [reportDate, setreportDate] = useState("");
    const [repairDate, setrepairDate] = useState("");
    const [issueStatus, setissueStatus] = useState("zgłoszona");
    const [error, setError] = useState("");
    const [messageColor, setMessageColor] = useState("red");

    const handleReport = async () => {
        try {
            const response = await fetch(`http://localhost:8082/panel/security/report`, {
                method: "POST",
                credentials: "include",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ description, reportDate, repairDate, issueStatus })
            });
            if (response.ok) {
                setError("Report submitted successfully");
                setMessageColor("green");
            } else {
                setError("Failed to submit report");
                setMessageColor("red");
            }
        } catch (error) {
            console.error("Error submitting report:", error);
            setError("Error submitting report");
            setMessageColor("red");
        }
    };

    return (
        <div className="main-container">
            <div className="page-header yellow">
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
                    <h1>Security Panel</h1>
                </div>
                <div className="center-wrapper">
                    <div className="form-wrapper">
                        <div className="form-list">
                            <div className="form-list-item">
                                <h2>Description:</h2>
                                <input type="text" placeholder="Description" className="input-box" value={description} onChange={(e) => setDescription(e.target.value)} />
                            </div>
                            <div className="form-list-item">
                                <h2>Outage Date:</h2>
                                <input type="text" placeholder="Outage Date" className="input-box" value={reportDate} onChange={(e) => setreportDate(e.target.value)} />
                            </div>
                            <div className="form-list-item">
                                <h2>Fix Date:</h2>
                                <input type="text" placeholder="Fix Date" className="input-box" value={repairDate} onChange={(e) => setrepairDate(e.target.value)} />
                            </div>
                            <div className="form-list-item">
                                <h2>Outage Status:</h2>
                                <select className="input-box" value={issueStatus} onChange={(e) => setissueStatus(e.target.value)}>
                                    <option value="zgłoszona">zgłoszona</option>
                                    <option value="w_trakcie_naprawy">w trakcie naprawy</option>
                                    <option value="naprawiona">naprawiona</option>
                                </select>
                            </div>
                        </div>
                        <div className="single-centered-button">
                            <button className="button-styled orange" onClick={handleReport}>REPORT</button>
                        </div>
                        {error && <p style={{color: messageColor}}>{error}</p>}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SecurityPane;