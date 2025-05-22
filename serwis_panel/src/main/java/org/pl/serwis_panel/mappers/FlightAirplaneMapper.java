package org.pl.serwis_panel.mappers;

import org.pl.serwis_panel.dto.AirplaneDTO;
import org.pl.serwis_panel.dto.FlightDTO;
import org.pl.serwis_panel.entities.Airplane;
import org.pl.serwis_panel.entities.Flight;

import java.util.stream.Collectors;

public class FlightAirplaneMapper {

    public static AirplaneDTO toAirPlaneDTO(Airplane airplane) {
        AirplaneDTO airplaneDTO = new AirplaneDTO();
        airplaneDTO.setModel(airplane.getModel());
        airplaneDTO.setRegistration(airplane.getRegistration());
        airplaneDTO.setSeatCount(airplane.getSeatCount());
        airplaneDTO.setLastServiceDate(airplane.getLastServiceDate());
        return airplaneDTO;
    }

    public static FlightDTO toFlightDTO(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightNumber(flight.getFlightNumber());
        flightDTO.setDepartureTime(flight.getDepartureTime());
        flightDTO.setArrivalTime(flight.getArrivalTime());
        flightDTO.setFlightStatus(flight.getFlightStatus());
        flightDTO.setDestination(flight.getDestination());
        flightDTO.setAirplanes(
                flight.getAirplanes().stream().map(
                        FlightAirplaneMapper::toAirPlaneDTO).collect(Collectors.toList()));
        return flightDTO;
    }
}
