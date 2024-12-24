package org.pl.serwis_panel.entities;

import jakarta.persistence.*;
import lombok.*;
import org.pl.serwis_panel.entities.enums.GateStatus;

import java.util.List;

@Entity
@Table(name = "Brama")
@Data
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Bramy")
    private Integer id;

    @Column(name = "Numer_bramy", nullable = false, unique = true)
    private String gateNumber;

    @Column(name = "Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private GateStatus gateStatus;

    @ManyToOne
    @JoinColumn(name = "ID_Awaria")
    private Failure failure;

    @ManyToMany(mappedBy = "gates")
    private List<Flight> flights;
}
