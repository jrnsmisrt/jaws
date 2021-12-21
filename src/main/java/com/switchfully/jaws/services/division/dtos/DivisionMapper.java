package com.switchfully.jaws.services.division.dtos;


import com.switchfully.jaws.domain.Division;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {

    public Division mapDivisionDtoToDivision(CreateDivisionDto createDivisionDto){
         return new Division.DivisionBuilder()
                 .withName(createDivisionDto.name())
                 .withDirectorFullName(createDivisionDto.directorFullName())
                 .withOriginalName(createDivisionDto.originalName())
                 .build();
    }

    public DivisionDto mapDivisionToDivisionDto(Division division){
         return new DivisionDto(division.getId(), division.getName(), division.getOriginalName(), division.getDirectorFullName());
    }

}
