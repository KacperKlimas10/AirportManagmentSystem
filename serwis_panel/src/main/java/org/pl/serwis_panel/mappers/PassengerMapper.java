package org.pl.serwis_panel.mappers;

import org.pl.serwis_panel.dto.PassengerDTO;
import org.pl.serwis_panel.entities.Passenger;

import java.util.Base64;

public class PassengerMapper {
    public static PassengerDTO toResponseDTO(Passenger passenger) {
        PassengerDTO dto = new PassengerDTO();
            dto.setName(passenger.getFirstName());
            dto.setSurname(passenger.getLastName());
            dto.setBirthDate(passenger.getBirthDate());
            dto.setNationality(passenger.getNationality());
            dto.setDocumentNumber(passenger.getDocumentNumber());

            byte[] photoBytes = passenger.getPhoto();
            if (photoBytes != null) {
                String photoBase64 = Base64.getEncoder().encodeToString(photoBytes);
                dto.setPhotoBase64(photoBase64);
            } else dto.setPhotoBase64(null);
            return dto;
    }

    public static Passenger toEntity(Passenger passenger, PassengerDTO passengerDTO) {
        if (passengerDTO.getName() != null) {passenger.setFirstName(passengerDTO.getName());}
        if (passengerDTO.getSurname() != null) {passenger.setLastName(passengerDTO.getSurname());}
        if (passengerDTO.getBirthDate() != null) {passenger.setBirthDate(passengerDTO.getBirthDate());}
        if (passengerDTO.getDocumentNumber() != null) {passenger.setDocumentNumber(passengerDTO.getDocumentNumber());}
        if (passengerDTO.getNationality() != null) {passenger.setNationality(passengerDTO.getNationality());}
        if (passengerDTO.getCheckInStatus() != null) {passenger.setCheckInStatus(passengerDTO.getCheckInStatus());}

        if (passengerDTO.getPhotoBase64() != null) {
            byte[] photoBytes = Base64.getDecoder().decode(passengerDTO.getPhotoBase64());
            passenger.setPhoto(photoBytes);
        }
        return passenger;
    }
}