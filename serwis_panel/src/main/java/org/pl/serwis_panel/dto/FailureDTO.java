package org.pl.serwis_panel.dto;

import lombok.Data;
import org.pl.serwis_panel.enums.FailureStatus;

import java.util.Date;

@Data
public class FailureDTO {

    private String description;
    private Date reportDate;
    private Date repairDate;
    private FailureStatus issueStatus;

}
