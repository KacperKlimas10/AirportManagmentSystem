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
}