package com.switchfully.jaws.services.division.dtos;


import com.switchfully.jaws.domain.Division;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DivisionMapper {

    public Division mapDivisionDtoToDivision(CreateDivisionDto createDivisionDto) {
        if (createDivisionDto.getCreateDivisionDtoList().isPresent()) {
            return buildDivisionWithSubdivisions(createDivisionDto);
        }
        return buildDivisionWithoutSubdivisions(createDivisionDto);
    }

    public DivisionDto mapDivisionToDivisionDto(Division division) {
        if(Optional.ofNullable(division.getSubDivisions()).isPresent()) {
            return mapDivisionToDivisionDtoWithSubdivisions(division);
        }
        return mapDivisionToDivisionDtoWithoutSubdivisions(division);
    }

    private DivisionDto mapDivisionToDivisionDtoWithoutSubdivisions(Division division) {
        return new DivisionDto.DivisionDtoBuilder()
                .withName(division.getName())
                .withDirectorFullName(division.getDirectorFullName())
                .withOriginalName(division.getOriginalName())
                .build();
    }

    private DivisionDto mapDivisionToDivisionDtoWithSubdivisions(Division division) {
        return new DivisionDto.DivisionDtoBuilder()
                .withName(division.getName())
                .withDirectorFullName(division.getDirectorFullName())
                .withOriginalName(division.getOriginalName())
                .withDivisionDtoList(division.getSubDivisions().stream()
                        .map(this::mapDivisionToDivisionDtoWithoutSubdivisions)
                        .collect(Collectors.toList()))
                .build();
    }

    private Division buildDivisionWithoutSubdivisions(CreateDivisionDto createDivisionDto) {
        return new Division.DivisionBuilder()
                .withName(createDivisionDto.getName())
                .withDirectorFullName(createDivisionDto.getDirectorFullName())
                .withOriginalName(createDivisionDto.getOriginalName())
                .build();
    }

    private Division buildDivisionWithSubdivisions(CreateDivisionDto createDivisionDto) {
        return new Division.DivisionBuilder()
                .withName(createDivisionDto.getName())
                .withDirectorFullName(createDivisionDto.getDirectorFullName())
                .withOriginalName(createDivisionDto.getOriginalName())
                .withParentDivisionId(createDivisionDto.getCreateDivisionDtoList().get().stream()
                        .map(this::buildDivisionWithoutSubdivisions)
                        .collect(Collectors.toList()))
                .build();
    }

}
