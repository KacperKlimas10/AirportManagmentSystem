package org.pl.serwis_panel.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.pl.serwis_panel.enums.FlightStatus;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Lot")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Lotu")
    private Integer id;

    @Column(name = "Numer_lotu", nullable = false, unique = true)
    private String flightNumber;

    @Column(name = "Data_i_godzina_startu", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;

    @Column(name = "Data_i_godzina_lądowania", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;

    @Column(name = "Status_lotu", nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;

    @Column(name = "Miejsce_docelowe", nullable = false)
    private String destination;

    @ManyToMany
    @JoinTable(
            name = "Lot_Samolot",
            joinColumns = @JoinColumn(name = "ID_Lotu"),
            inverseJoinColumns = @JoinColumn(name = "ID_Samolotu")
    )
    private List<Airplane> airplanes;

    @ManyToMany
    @JoinTable(
            name = "Lot_Brama",
            joinColumns = @JoinColumn(name = "ID_Lotu"),
            inverseJoinColumns = @JoinColumn(name = "ID_Bramy")
    )
    private List<Gate> gates;
}
