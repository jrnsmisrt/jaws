package com.switchfully.jaws.services.division;

import com.switchfully.jaws.domain.Division;
import com.switchfully.jaws.repositories.DivisionRepository;
import com.switchfully.jaws.services.division.dtos.CreateDivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionMapper;
import org.springframework.stereotype.Service;

@Service
public class DivisionService {


    private DivisionRepository divisionRepository;
    private DivisionMapper divisionMapper;

    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
    }

    public DivisionDto createDivision(CreateDivisionDto createDivisionDTO) {
        Division division= divisionMapper.mapDivisionDtoToDivision(createDivisionDTO);
        divisionRepository.save(division);
        return divisionMapper.mapDivisionToDivisionDto(division);
    }
}
