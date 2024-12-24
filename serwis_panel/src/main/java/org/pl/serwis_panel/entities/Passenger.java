package org.pl.serwis_panel.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Pasażer")
@Data
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Pasażera")
    private Integer id;

    @Column(name = "Imię", nullable = false)
    private String firstName;

    @Column(name = "Nazwisko", nullable = false)
    private String lastName;

    @Column(name = "Data_urodzenia", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "Numer_dokumentu", nullable = false, unique = true)
    private String documentNumber;

    @Column(name = "Narodowość")
    private String nationality;

    @Column(name = "Status_odprawy", nullable = false)
    private String checkInStatus;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Baggage> baggageList;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parking> parkingList;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transport> transportList;
}