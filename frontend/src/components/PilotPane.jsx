import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import PK from "../img/PK.png";

const PANEL_SERVICE = process.env.REACT_APP_PANEL_SERVICE;

function PilotPane() {
    const navigate = useNavigate();
    const [planeId, setPlaneId] = useState(null);
    const [planeDetails, setPlaneDetails] = useState(null);
    const [weatherInfo, setWeatherInfo] = useState(null);
    const [error, setError] = useState("");
    const [planes, setPlanes] = useState([]);

    useEffect(() => {
        const fetchPlanes = async () => {
            try {
                const response = await fetch(`${process.env.REACT_APP_PANEL_SERVICE}/panel/pilot/plane`, {
                    method: "GET",
                    credentials: "include"
                });
                if (response.ok) {
                    const data = await response.json();
                    setPlanes(data);
                } else {
                    console.error("Failed to fetch planes");
                }
            } catch (error) {
                console.error("Error fetching planes:", error);
            }
        };

        fetchPlanes();
    }, []);

    const handleFetchPlaneDetails = async () => {
        try {
            const response = await fetch(`${process.env.REACT_APP_PANEL_SERVICE}/panel/pilot/plane/${planeId}`, {
                method: "GET",
                credentials: "include"
            });
            if (response.ok) {
                const data = await response.json();
                setPlaneDetails(data);
                setError("");
                if (data.flights && data.flights.length > 0) {
                    fetchWeatherInfo(data.flights[0].destination);
                }
            } else {
                setError("Failed to fetch plane details");
                setPlaneDetails(null);
            }
        } catch (error) {
            console.error("Error fetching plane details:", error);
            setError("Error fetching plane details");
            setPlaneDetails(null);
        }
    };

    const fetchWeatherInfo = async (city) => {
        try {
            const response = await fetch(`${process.env.REACT_APP_PANEL_SERVICE}/panel/pilot/weather?city=${city}`, {
                method: "GET",
                credentials: "include"
            });
            if (response.ok) {
                const data = await response.json();
                setWeatherInfo(data);
            } else {
                setWeatherInfo(null);
            }
        } catch (error) {
            console.error("Error fetching weather information:", error);
            setWeatherInfo(null);
        }
    };

    const formatDate = (timestamp) => {
        const date = new Date(timestamp);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${day}.${month}.${year}`;
    };

    const formatTime = (timestamp) => {
        const date = new Date(timestamp);
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        const seconds = String(date.getSeconds()).padStart(2, '0');
        return `${hours}:${minutes}:${seconds}`;
    };

    return (
        <div className="main-container">
            <div className="page-header blue">
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
                    <h1>Pilot Panel</h1>
                </div>
                <div className="plane-wrapper">
                    <div className="form-wrapper-pilot">
                        <div className="form-list">
                            <div className="form-list-item">
                                <h2>Plane:</h2>
                                <select className="input-box" value={planeId} onChange={(e) => setPlaneId(e.target.value)}>
                                    <option value="">Select a plane</option>
                                    {planes.map(plane => (
                                        <option key={plane.id} value={plane.id}>{plane.model} - ({plane.registration})</option>
                                    ))}
                                </select>
                            </div>
                        </div>
                        <div className="single-centered-button">
                            <button className="button-styled blue" onClick={handleFetchPlaneDetails}>CHECK</button>
                        </div>
                        {error && <p style={{color: "red"}} className="errorText">{error}</p>}
                    </div>
                    {planeDetails && (
                        <div className="plane-info">
                            <div className="weather-data">
                                <div className="weather-heading">
                                    <div className="weather-heading-left">
                                        <div className="icon-wrapper">
                                            {weatherInfo && weatherInfo.weather && weatherInfo.weather[0] && (
                                                <img
                                                    src={`http://openweathermap.org/img/w/${weatherInfo.weather[0].icon}.png`}
                                                    alt={weatherInfo.weather[0].description}
                                                    className="weather-icon"
                                                />
                                            )}
                                        </div>
                                        {weatherInfo && weatherInfo.name && (
                                            <p className="temperature">{weatherInfo.name}</p>
                                        )}
                                    </div>
                                    <div className="weather-heading-right">
                                        {weatherInfo && weatherInfo.main && (
                                            <p>Feels Like: {weatherInfo.main.feels_like}°C</p>
                                        )}
                                    </div>
                                </div>
                                {weatherInfo ? (
                                    <h3 className="weather-description">{weatherInfo.weather[0].description}</h3>
                                ) : (
                                    <h2>No weather information available</h2>
                                )}
                                <div className="weather-grid">
                                    {weatherInfo && weatherInfo.main && (
                                        <>
                                            <div className="weather-grid-item">
                                                <h4>Temperature: </h4>
                                                <p>{weatherInfo.main.temp}°C</p>
                                            </div>
                                            <div className="weather-grid-item">
                                                <h4>Pressure: </h4>
                                                <p>{weatherInfo.main.pressure} hPa</p>
                                            </div>
                                            <div className="weather-grid-item">
                                                <h4>Humidity: </h4>
                                                <p>{weatherInfo.main.humidity} %</p>
                                            </div>
                                        </>
                                    )}
                                    {weatherInfo && (
                                        <>
                                            <div className="weather-grid-item">
                                                <h4>Visibility: </h4>
                                                <p>{weatherInfo.visibility}</p>
                                            </div>
                                            <div className="weather-grid-item">
                                                <h4>Wind Speed: </h4>
                                                <p>{weatherInfo.wind.speed} m/s</p>
                                            </div>
                                            <div className="weather-grid-item">
                                                <h4>Wind Degree: </h4>
                                                <p>{weatherInfo.wind.deg}</p>
                                            </div>
                                            <div className="weather-grid-item">
                                                <h4>Gust: </h4>
                                                <p>{weatherInfo.wind.gust} m/s</p>
                                            </div>
                                            <div className="weather-grid-item">
                                                <h4>Cloud Cover:</h4>
                                                <p>{weatherInfo.clouds.all} %</p>
                                            </div>
                                        </>
                                    )}
                                </div>
                            </div>
                            <div className="plane-info-container">
                                <div className="plane-info-list">
                                    <ol className="plane-data">
                                        <li><p className="plane-data-p">Destination: {planeDetails.flights[0].destination}</p></li>
                                        <li><p className="plane-data-p">Departure Time: {formatTime(planeDetails.flights[0].departureTime)}</p></li>
                                        <li><p className="plane-data-p">Arrival Time: {formatTime(planeDetails.flights[0].arrivalTime)}</p></li>
                                        <li><p className="plane-data-p">Flight Number: {planeDetails.flights[0].flightNumber}</p></li>
                                        <li><p className="plane-data-p">Flight Status: {planeDetails.flights[0].flightStatus}</p></li>
                                    </ol>
                                </div>
                                <div className="plane-info-list">
                                    <ul className="plane-data">
                                        <li>Plane Model: {planeDetails.model}</li>
                                        <li>Registration Plate: {planeDetails.registration}</li>
                                        <li>Seat Count: {planeDetails.seatCount}</li>
                                        <li>Last Serviced: {formatDate(planeDetails.lastServiceDate)}</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}

export default PilotPane;