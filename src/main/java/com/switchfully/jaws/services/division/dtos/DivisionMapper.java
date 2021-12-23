package com.switchfully.jaws.services.division.dtos;


import com.switchfully.jaws.domain.Division;
import com.switchfully.jaws.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DivisionMapper {

    private final DivisionRepository divisionRepository;

    @Autowired
    public DivisionMapper(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    public Division mapDivisionDtoToDivision(CreateDivisionDto createDivisionDto) {
        return new Division.DivisionBuilder()
                .withName(createDivisionDto.getName())
                .withDirectorFullName(createDivisionDto.getDirectorFullName())
                .withOriginalName(createDivisionDto.getOriginalName())
                .build();
    }

    public DivisionDto mapDivisionToDivisionDto(Division division) {
        return new DivisionDto.DivisionDtoBuilder()
                .withName(division.getName())
                .withDirectorFullName(division.getDirectorFullName())
                .withOriginalName(division.getOriginalName())
                .withParentDivisionId(divisionRepository.findDivisionsBySubDivisionsIn(division.getName()))
                .build();
    }
}
