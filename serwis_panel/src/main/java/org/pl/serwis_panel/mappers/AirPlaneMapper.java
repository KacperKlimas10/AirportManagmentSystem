package org.pl.serwis_panel.mappers;

import org.pl.serwis_panel.dto.AirplaneDTO;
import org.pl.serwis_panel.entities.Airplane;

import java.util.List;
import java.util.stream.Collectors;

public class AirPlaneMapper {
    public static AirplaneDTO toDto(Airplane airplane) {
        AirplaneDTO airplaneDTO = new AirplaneDTO();
        airplaneDTO.setId(airplane.getId());
        airplaneDTO.setModel(airplane.getModel());
        airplaneDTO.setRegistration(airplane.getRegistration());
        airplaneDTO.setSeatCount(airplane.getSeatCount());
        airplaneDTO.setLastServiceDate(airplane.getLastServiceDate());
        return airplaneDTO;
    }

    public static List<AirplaneDTO> toDtoList(List<Airplane> airplanes) {
        return airplanes.stream()
                .map(AirPlaneMapper::toDto)
                .collect(Collectors.toList());
    }
}
