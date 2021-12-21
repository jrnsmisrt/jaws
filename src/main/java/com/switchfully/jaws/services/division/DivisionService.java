package com.switchfully.jaws.services.division;

import com.switchfully.jaws.domain.Division;
import com.switchfully.jaws.repositories.DivisionRepository;
import com.switchfully.jaws.services.division.dtos.CreateDivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DivisionService {

    private final DivisionRepository divisionRepository;
    private final DivisionMapper divisionMapper;

    @Autowired
    public DivisionService(DivisionMapper divisionMapper, DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
    }

    public DivisionDto createDivision(CreateDivisionDto createDivisionDTO) {
        Division division= divisionMapper.mapDivisionDtoToDivision(createDivisionDTO);
        divisionRepository.save(division);
        return divisionMapper.mapDivisionToDivisionDto(division);
    }

    public List<DivisionDto> getAllDivisions() {
        return divisionRepository.findAll().stream()
                .map(division -> divisionMapper.mapDivisionToDivisionDto(division))
                .collect(Collectors.toList());
    }
}
