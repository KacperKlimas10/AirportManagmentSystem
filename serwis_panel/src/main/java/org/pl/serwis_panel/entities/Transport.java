package org.pl.serwis_panel.entities;

import lombok.Data;
import jakarta.persistence.*;
import org.pl.serwis_panel.enums.TransportType;

import java.util.Date;

@Entity
@Table(name = "Transport")
@Data
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Transportu")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Typ_transportu", nullable = false)
    private TransportType transportType;

    @Column(name = "Czas_przyjazdu", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;

    @Column(name = "Lokalizacja_docelowa", nullable = false)
    private String destinationLocation;

    @ManyToOne
    @JoinColumn(name = "ID_Pasażera", nullable = false)
    private Passenger passenger;
}
