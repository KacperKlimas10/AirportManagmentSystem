package org.pl.serwis_panel.services;

import org.pl.serwis_panel.dto.PassengerDTO;
import org.pl.serwis_panel.entities.Passenger;
import org.pl.serwis_panel.mappers.PassengerMapper;
import org.pl.serwis_panel.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

@Service
public class StaffPanelService {

    private final PassengerRepository passengerRepository;

    public StaffPanelService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public PassengerDTO getPassengerByName(String name, String surname) {
        Passenger passenger = passengerRepository.getPassagerByName(name, surname);
        if (passenger != null) {
            return PassengerMapper.toResponseDTO(passenger);
        } else return null;
    }
}
