package org.pl.serwis_panel.services;

import lombok.RequiredArgsConstructor;
import org.pl.serwis_panel.dto.FailureDTO;
import org.pl.serwis_panel.mappers.FailureMapper;
import org.pl.serwis_panel.repositories.FailureRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityPanelService {

    private final FailureRepository failureRepository;

    public void saveFailure(FailureDTO failureDTO) {
        failureRepository.save(FailureMapper.toEntity(failureDTO));
    }
}
