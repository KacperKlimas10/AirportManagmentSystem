import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import PK from "../img/PK.png";

function AdminPane() {
    const navigate = useNavigate();

    // nav handlers
    return (
        <div className="main-container">
            <div className="page-header red">
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
                    <h1>Administrative Operations</h1>
                </div>
                <div className="form-wrapper">
                    <div className="form-list">
                        <div className="form-list-item">
                            <h2>ID:</h2>
                            <input type="text" placeholder="Input ID" className="input-box"/>
                        </div>
                        <div className="form-list-item">
                            <h2>Username:</h2>
                            <input type="text" placeholder="Input Username" className="input-box"/>
                        </div>
                        <div className="form-list-item">
                            <h2>First Name:</h2>
                            <input type="text" placeholder="Input First Name" className="input-box"/>
                        </div>
                        <div className="form-list-item">
                            <h2>Last Name:</h2>
                            <input type="text" placeholder="Input Last Name" className="input-box"/>
                        </div>
                        <div className="form-list-item">
                            <h2>Password:</h2>
                            <input type="text" placeholder="Input Password" className="input-box"/>
                        </div>
                        <div className="form-list-item">
                            <h2>Role:</h2>
                            <input type="text" placeholder="Input Role" className="input-box"/>
                        </div>
                    </div>
                    <div className="admin-buttons">
                        <button className="button-styled green">ADD</button>
                        <button className="button-styled red">DELETE</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AdminPane;