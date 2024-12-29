package org.pl.serwis_panel.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PassengerDTO {
    private String name;
    private String surname;
    private Date birthDate;
    private String documentNumber;
    private String nationality;
    private String photoBase64;
}