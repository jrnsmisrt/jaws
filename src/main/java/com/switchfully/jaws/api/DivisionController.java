package com.switchfully.jaws.api;

import com.switchfully.jaws.services.division.DivisionService;
import com.switchfully.jaws.services.division.dtos.CreateDivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/divisions", produces = APPLICATION_JSON_VALUE)
public class DivisionController {

    private DivisionService divisionService;

    @Autowired
    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CREATE_DIVISION')")
    public DivisionDto createDivision(@RequestBody CreateDivisionDto createDivisionDTO) {
        return divisionService.createDivision(createDivisionDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('GET_DIVISION_OVERVIEW')")
    public List<DivisionDto> getAllDivisions(){
        return divisionService.getAllDivisions();
    }
}
