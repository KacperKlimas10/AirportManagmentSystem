package org.pl.serwis_panel.entities;

import jakarta.persistence.*;
import lombok.*;
import org.pl.serwis_panel.entities.enums.BaggageStatus;
import org.pl.serwis_panel.entities.enums.BaggageType;

import java.util.List;

@Entity
@Table(name = "Bagaż")
@Data
public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Bagażu")
    private Integer id;

    @Column(name = "Typ_bagażu", nullable = false)
    @Enumerated(EnumType.STRING)
    private BaggageType baggageType;

    @Column(name = "Waga", nullable = false)
    private Double weight;

    @Column(name = "Status_bagażu", nullable = false)
    @Enumerated(EnumType.STRING)
    private BaggageStatus baggageStatus;

    @Column(name = "Lokalizacja_bagażu")
    private String baggageLocation;

    @ManyToMany(mappedBy = "baggages")
    private List<Airplane> airplanes;

    @ManyToOne
    @JoinColumn(name = "ID_Pasażera", nullable = false)
    private Passenger passenger;
}
