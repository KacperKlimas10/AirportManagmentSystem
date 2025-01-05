package org.pl.serwis_panel.mappers;

import org.pl.serwis_panel.dto.FailureDTO;
import org.pl.serwis_panel.entities.Failure;

public class FailureMapper {

    public static Failure toEntity(FailureDTO failureDTO) {
        Failure failure = new Failure();
        failure.setDescription(failureDTO.getDescription());
        failure.setReportDate(failureDTO.getReportDate());
        failure.setRepairDate(failureDTO.getRepairDate());
        failure.setIssueStatus(failureDTO.getIssueStatus());
        return failure;
    }
}
