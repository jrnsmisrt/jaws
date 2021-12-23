package com.switchfully.jaws.services.division;

import com.switchfully.jaws.domain.Division;
import com.switchfully.jaws.exceptions.ParentDivisionNotFoundException;
import com.switchfully.jaws.repositories.DivisionRepository;
import com.switchfully.jaws.services.division.dtos.CreateDivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
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
            return addDivisionToDbAndReturnDivisionDto(createDivisionDTO);
        }
        throw new IllegalArgumentException("Could not add division");
    }

    private DivisionDto addDivisionToDbAndReturnDivisionDto(CreateDivisionDto createDivisionDTO) {
        Division division = divisionMapper.mapDivisionDtoToDivision(createDivisionDTO);
        if (createDivisionDTO.getParentDivisionId() != null) {
            Optional<Division> parentDivision = divisionRepository.findById(createDivisionDTO.getParentDivisionId());
            parentDivision.ifPresentOrElse(parentDiv -> parentDiv.addSubdivision(division),
                    () -> {throw new ParentDivisionNotFoundException();});
        }
        divisionRepository.save(division);
        return divisionMapper.mapDivisionToDivisionDto(division);
    }

    public List<DivisionDto> getAllDivisions() {
        return divisionRepository.findAll().stream()
                .map(division -> divisionMapper.mapDivisionToDivisionDto(division))
                .collect(Collectors.toList());
    }

    private boolean nameDoesNotExists(CreateDivisionDto createDivisionDto) {
        return divisionRepository.findAll().stream()
                .map(Division::getName)
                .noneMatch(name -> name.equalsIgnoreCase(divisionMapper.mapDivisionDtoToDivision(createDivisionDto).getName()));
    }
}
