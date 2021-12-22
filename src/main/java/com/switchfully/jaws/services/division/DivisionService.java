package com.switchfully.jaws.services.division;

import com.switchfully.jaws.domain.Division;
import com.switchfully.jaws.repositories.DivisionRepository;
import com.switchfully.jaws.services.division.dtos.CreateDivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DivisionService {

    private DivisionRepository divisionRepository;
    private DivisionMapper divisionMapper;

    @Autowired
    public DivisionService(DivisionMapper divisionMapper, DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
    }

    public DivisionDto createDivision(CreateDivisionDto createDivisionDTO) {
        if (nameDoesNotExists(createDivisionDTO)) {
            return getDivisionDto(createDivisionDTO);
        } else if (isParentDivision(createDivisionDTO)) {
            divisionRepository.findAll().stream()
                    .filter(division -> division.getName().equalsIgnoreCase(createDivisionDTO.getName()))
                    .map(Division::getId)
                    .findAny()
                    .ifPresent( id -> divisionRepository.deleteById(id));
            return getDivisionDto(createDivisionDTO);
        }
        throw new IllegalArgumentException("Could not add division");
    }

    private DivisionDto getDivisionDto(CreateDivisionDto createDivisionDTO) {
        Division division = divisionMapper.mapDivisionDtoToDivision(createDivisionDTO);
        divisionRepository.save(division);
        return divisionMapper.mapDivisionToDivisionDto(division);
    }

    private boolean isParentDivision(CreateDivisionDto createDivisionDTO) {
        Optional<Division> optDivision = divisionRepository.findDivisionByName(createDivisionDTO.getName());
        return optDivision.filter(division -> division.getSubDivisions() != null).isPresent();
    }

    public List<DivisionDto> getAllDivisions() {
        return divisionRepository.findAll().stream()
                .map(division -> divisionMapper.mapDivisionToDivisionDto(division))
                .collect(Collectors.toList());
    }


    private boolean nameDoesNotExists(CreateDivisionDto createDivisionDto) {//TODO
        return divisionRepository.findAll().stream()
                .map(Division::getName)
                .noneMatch(name -> name.equalsIgnoreCase(divisionMapper.mapDivisionDtoToDivision(createDivisionDto).getName()));
    }
}
