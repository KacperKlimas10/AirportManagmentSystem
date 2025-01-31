package org.pl.serwis_panel.services;

import lombok.RequiredArgsConstructor;
import org.pl.serwis_panel.config.WebClientConfig;
import org.pl.serwis_panel.dto.AirplaneDTO;
import org.pl.serwis_panel.dto.FlightDTO;
import org.pl.serwis_panel.dto.openweather.api.OpenWeatherDTO;
import org.pl.serwis_panel.entities.Airplane;
import org.pl.serwis_panel.entities.Flight;
import org.pl.serwis_panel.mappers.AirPlaneMapper;
import org.pl.serwis_panel.mappers.FlightAirplaneMapper;
import org.pl.serwis_panel.repositories.AirplaneRepository;
import org.pl.serwis_panel.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PilotPanelService {

    private final AirplaneRepository airplaneRepository;
    private final FlightRepository flightRepository;
    private final WebClientConfig webClientConfig;
    private final String openWeatherAPI = "44c41402e2eefed67400042c0e546e7f";

    public List<Airplane> getPlanes() {return this.airplaneRepository.findAll();}

    public List<AirplaneDTO> getPlanesWithFlights() {return AirPlaneMapper.toDtoList(this.airplaneRepository.findAllWithFlights());}

    public Airplane getAirplaneById(int id) {return this.airplaneRepository.findById(id);}

    public FlightDTO getFlightById(int id) {
        Flight flight = flightRepository.findById(id);
        if (flight != null) {
            return FlightAirplaneMapper.toFlightDTO(flight);
        } else return null;
    }

    public OpenWeatherDTO getWeather(String city) {
        try {
            return webClientConfig.webClientPilotServiceWeatherURL()
                    .get()
                    .uri("/data/2.5/weather?appid=" + openWeatherAPI + "&q=" + city + "&units=metric")
                    .retrieve()
                    .bodyToMono(OpenWeatherDTO.class)
                    .block();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
