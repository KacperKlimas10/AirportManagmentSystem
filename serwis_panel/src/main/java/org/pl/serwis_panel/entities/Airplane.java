package org.pl.serwis_panel.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Samolot")
@Data
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Samolotu")
    private Integer id;

    @Column(name = "Model", nullable = false)
    private String model;

    @Column(name = "Rejestracja", nullable = false, unique = true)
    private String registration;

    @Column(name = "Liczba_miejsc", nullable = false)
    private Integer seatCount;

    @Column(name = "Data_ostatniego_serwisu")
    @Temporal(TemporalType.DATE)
    private Date lastServiceDate;

    @ManyToMany(mappedBy = "airplanes")
    private List<Flight> flights;

    @ManyToMany
    @JoinTable(
            name = "Samolot_Bagaż",
            joinColumns = @JoinColumn(name = "ID_Samolotu"),
            inverseJoinColumns = @JoinColumn(name = "ID_Bagażu")
    )
    private List<Baggage> baggages ;
}