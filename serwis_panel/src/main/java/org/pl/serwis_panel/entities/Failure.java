package org.pl.serwis_panel.entities;

import jakarta.persistence.*;
import lombok.*;
import org.pl.serwis_panel.entities.enums.IssueStatus;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Awaria")
@Data
public class Failure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Awaria")
    private Integer id;

    @Column(name = "Opis_awarii", nullable = false)
    private String description;

    @Column(name = "Data_zgłoszenia", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDate;

    @Column(name = "Data_naprawy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date repairDate;

    @Column(name = "Status_awarii", nullable = false)
    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;
}