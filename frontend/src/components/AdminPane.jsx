import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import PK from "../img/PK.png";

function AdminPane() {
    const navigate = useNavigate();
    const [id, setId] = useState(null);
    const [login, setUsername] = useState(null);
    const [name, setFirstName] = useState(null);
    const [surname, setLastName] = useState(null);
    const [password, setPassword] = useState(null);
    const [role, setRole] = useState(null);
    const [error, setError] = useState("");
    const [messageColor, setMessageColor] = useState("red");
    const [users, setUsers] = useState([]);

    const fetchUsers = async () => {
        try {
            const response = await fetch(`http://localhost:8082/panel/admin/user`, {
                method: "GET",
                credentials: "include"
            });
            if (response.ok) {
                const data = await response.json();
                setUsers(data);
            } else {
                setError("Failed to fetch users");
                setMessageColor("red");
            }
        } catch (error) {
            console.error("Error fetching users:", error);
            setError("Error fetching users");
            setMessageColor("red");
        }
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    const handleAddUser = async () => {
        try {
            const response = await fetch(`http://localhost:8082/panel/admin/user`, {
                method: "POST",
                credentials: "include",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ name, surname, login, password, role })
            });
            if (response.ok) {
                setError("User added successfully");
                setMessageColor("green");
                fetchUsers();
            } else {
                setError("Failed to add user");
                setMessageColor("red");
            }
        } catch (error) {
            console.error("Error adding user:", error);
            setError("Error adding user");
            setMessageColor("red");
        }
    };

    const handleDeleteUser = async () => {
        try {
            const response = await fetch(`http://localhost:8082/panel/admin/user/${id}`, {
                method: "DELETE",
                credentials: "include"
            });
            if (response.ok) {
                setError("User deleted successfully");
                setMessageColor("green");
                fetchUsers();
            } else {
                setError("Failed to delete user");
                setMessageColor("red");
            }
        } catch (error) {
            console.error("Error deleting user:", error);
            setError("Error deleting user");
            setMessageColor("red");
        }
    };

    const handleUpdateUser = async () => {
        try {
            const response = await fetch(`http://localhost:8082/panel/admin/user/${id}`, {
                method: "PATCH",
                credentials: "include",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ name, surname, login, password, role })
            });
            if (response.ok) {
                setError("User updated successfully");
                setMessageColor("green");
                fetchUsers();
            } else {
                setError("Failed to update user");
                setMessageColor("red");
            }
        } catch (error) {
            console.error("Error updating user:", error);
            setError("Error updating user");
            setMessageColor("red");
        }
    };

    return (
        <div className="main-container">
            <div className="page-header red">
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
                    <h1>Administrative Operations</h1>
                </div>
                <div className="form-wrapper">
                    <div className="form-list">
                        <div className="form-list-item">
                            <h2>ID:</h2>
                            <select className="input-box" value={id} onChange={(e) => setId(e.target.value)}>
                                <option value="">Select User</option>
                                {users.map(user => (
                                    <option key={user.id} value={user.id}>{user.id} - {user.name} {user.surname}</option>
                                ))}
                            </select>
                        </div>
                        <div className="form-list-item">
                            <h2>Username:</h2>
                            <input type="text" placeholder="Input Username" className="input-box" value={login} onChange={(e) => setUsername(e.target.value)} />
                        </div>
                        <div className="form-list-item">
                            <h2>First Name:</h2>
                            <input type="text" placeholder="Input First Name" className="input-box" value={name} onChange={(e) => setFirstName(e.target.value)} />
                        </div>
                        <div className="form-list-item">
                            <h2>Last Name:</h2>
                            <input type="text" placeholder="Input Last Name" className="input-box" value={surname} onChange={(e) => setLastName(e.target.value)} />
                        </div>
                        <div className="form-list-item">
                            <h2>Password:</h2>
                            <input type="text" placeholder="Input Password" className="input-box" value={password} onChange={(e) => setPassword(e.target.value)} />
                        </div>
                        <div className="form-list-item">
                            <h2>Role:</h2>
                            <select className="input-box" value={role} onChange={(e) => setRole(e.target.value)}>
                                <option value="">Select Role</option>
                                <option value="administrator">administrator</option>
                                <option value="obsługa_techniczna">obsługa techniczna</option>
                                <option value="kontrola_lotów">kontrola lotów</option>
                                <option value="ochrona">ochrona</option>
                                <option value="pilot">pilot</option>
                            </select>
                        </div>
                    </div>
                    <div className="admin-buttons">
                        <button className="button-styled green" onClick={handleAddUser}>ADD</button>
                        <button className="button-styled red" onClick={handleDeleteUser}>DELETE</button>
                        <button className="button-styled blue" onClick={handleUpdateUser}>UPDATE</button>
                    </div>
                    {error && <p style={{color: messageColor}}>{error}</p>}
                </div>
            </div>
        </div>
    );
}

export default AdminPane;