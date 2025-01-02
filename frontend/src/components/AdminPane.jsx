import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import PK from "../img/PK.png";

function AdminPane() {
    const navigate = useNavigate();
    const [id, setId] = useState("");
    const [username, setUsername] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("");
    const [error, setError] = useState("");

    const handleAddUser = async () => {
        try {
            const response = await fetch("http://localhost:8082/panel/admin/user", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ username, firstName, lastName, password, role })
            });
            if (response.ok) {
                setError("User added successfully");
            } else {
                setError("Failed to add user");
            }
        } catch (error) {
            console.error("Error adding user:", error);
            setError("Error adding user");
        }
    };

    const handleDeleteUser = async () => {
        try {
            const response = await fetch(`http://localhost:8082/panel/admin/user?id=${id}`, {
                method: "DELETE",
            });
            if (response.ok) {
                setError("User deleted successfully");
            } else {
                setError("Failed to delete user");
            }
        } catch (error) {
            console.error("Error deleting user:", error);
            setError("Error deleting user");
        }
    };

    const handleUpdateUser = async () => {
        try {
            const response = await fetch("http://localhost:8082/panel/admin/user", {
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ id, username, firstName, lastName, password, role })
            });
            if (response.ok) {
                setError("User updated successfully");
            } else {
                setError("Failed to update user");
            }
        } catch (error) {
            console.error("Error updating user:", error);
            setError("Error updating user");
        }
    };

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
                            <input type="text" placeholder="Input ID" className="input-box" value={id} onChange={(e) => setId(e.target.value)} />
                        </div>
                        <div className="form-list-item">
                            <h2>Username:</h2>
                            <input type="text" placeholder="Input Username" className="input-box" value={username} onChange={(e) => setUsername(e.target.value)} />
                        </div>
                        <div className="form-list-item">
                            <h2>First Name:</h2>
                            <input type="text" placeholder="Input First Name" className="input-box" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                        </div>
                        <div className="form-list-item">
                            <h2>Last Name:</h2>
                            <input type="text" placeholder="Input Last Name" className="input-box" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                        </div>
                        <div className="form-list-item">
                            <h2>Password:</h2>
                            <input type="text" placeholder="Input Password" className="input-box" value={password} onChange={(e) => setPassword(e.target.value)} />
                        </div>
                        <div className="form-list-item">
                            <h2>Role:</h2>
                            <input type="text" placeholder="Input Role" className="input-box" value={role} onChange={(e) => setRole(e.target.value)} />
                        </div>
                    </div>
                    <div className="admin-buttons">
                        <button className="button-styled green" onClick={handleAddUser}>ADD</button>
                        <button className="button-styled red" onClick={handleDeleteUser}>DELETE</button>
                        <button className="button-styled blue" onClick={handleUpdateUser}>UPDATE</button>
                    </div>
                    {error && <p style={{color: "red"}}>{error}</p>}
                </div>
            </div>
        </div>
    );
}

export default AdminPane;