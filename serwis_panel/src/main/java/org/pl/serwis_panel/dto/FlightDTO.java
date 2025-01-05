package org.pl.serwis_panel.dto;

import lombok.Data;
import org.pl.serwis_panel.enums.FlightStatus;

import java.util.Date;
import java.util.List;

@Data
public class FlightDTO {

    private String flightNumber;
    private Date departureTime;
    private Date arrivalTime;
    private FlightStatus flightStatus;
    private String destination;
    private List<AirplaneDTO> airplanes;
}
