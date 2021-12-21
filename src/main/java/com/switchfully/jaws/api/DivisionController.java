package com.switchfully.jaws.api;

import com.switchfully.jaws.services.division.DivisionService;
import com.switchfully.jaws.services.division.dtos.CreateDivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DivisionController {


    private DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    //As a Manager I want to create a division.
    //
    //ParkShark became the company it is by doing takeovers of competing companies. These companies were never fully merged with ParkShark, they became divisions.
    //A division has a name, an original name (the original name of the bought company) and a director
    //Prioritization: Must-Have

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DivisionDto createDivision(CreateDivisionDto createDivisionDTO){
        return divisionService.createDivision(createDivisionDTO);
    }
}
