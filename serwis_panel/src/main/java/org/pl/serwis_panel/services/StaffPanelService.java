package org.pl.serwis_panel.services;

import lombok.RequiredArgsConstructor;
import org.pl.serwis_panel.dto.PassengerDTO;
import org.pl.serwis_panel.entities.Passenger;
import org.pl.serwis_panel.mappers.PassengerMapper;
import org.pl.serwis_panel.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffPanelService {

    private final PassengerRepository passengerRepository;

    public PassengerDTO getPassengerByName(String name, String surname) {
        Passenger passenger = passengerRepository.getPassagerByName(name, surname);
        if (passenger != null) {
            return PassengerMapper.toResponseDTO(passenger);
        } else return null;
    }

    public boolean updatePassenger(String name, String surname, PassengerDTO passengerDTO) {
        Passenger passenger = passengerRepository.getPassagerByName(name, surname);
        if (passenger != null) {
            passengerRepository.save(PassengerMapper.toEntity(passenger, passengerDTO));
            return true;
        } else return false;
    }
}
