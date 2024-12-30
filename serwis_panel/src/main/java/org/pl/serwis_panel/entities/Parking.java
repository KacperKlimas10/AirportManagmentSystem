package org.pl.serwis_panel.entities;

import lombok.Data;
import jakarta.persistence.*;
import org.pl.serwis_panel.enums.ParkingStatus;

@Entity
@Table(name = "Parking")
@Data
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Parkingu")
    private Integer id;

    @Column(name = "Numer_miejsca", nullable = false, unique = true)
    private String spotNumber;

    @Column(name = "Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParkingStatus status;

    @ManyToOne
    @JoinColumn(name = "ID_Pasażera")
    private Passenger passenger;
}
