package org.pl.serwis_panel.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AirplaneDTO {
    private int id;
    private String model;
    private String registration;
    private int seatCount;
    private Date lastServiceDate;
}
