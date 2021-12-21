package com.switchfully.jaws.services.division;

import com.switchfully.jaws.domain.Division;
import com.switchfully.jaws.repositories.DivisionRepository;
import com.switchfully.jaws.services.division.dtos.CreateDivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DivisionServiceTest {


    private DivisionRepository divisionRepository;
    private DivisionService divisionServiceMock;
    private DivisionMapper divisionMapperMock;

    @BeforeEach
    void setUp() {
        divisionRepository = mock(DivisionRepository.class);
        divisionMapperMock = mock(DivisionMapper.class);
        divisionServiceMock = new DivisionService(divisionMapperMock, divisionRepository);

    }

    @Test
    public void test() {
        CreateDivisionDto createDivisionDto = new CreateDivisionDto("testDivision", "Gotham", "batman");

        divisionServiceMock.createDivision(createDivisionDto);
        //Mockito.verify(divisionServiceMock).connect();

    }


}