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

/*
class DivisionServiceTest {


    private DivisionService divisionService;
    private DivisionService divisionServiceMock;
    //private ExternalStockPriceService externalStockPriceServiceMock;
    private DivisionMapper divisionMapperMock;

    @BeforeEach
    void setUp() {
        //divisionServiceMock = mock(DivisionService.class);
        DivisionRepository divisionRepository = mock(DivisionRepository.class);
        divisionMapperMock = mock(DivisionMapper.class);
        divisionService = new DivisionService(divisionMapperMock, divisionRepository);

    }

    @Test
    public void test() {
        CreateDivisionDto createDivisionDto = new CreateDivisionDto("testDivision", "Gotham", "batman");

        divisionServiceMock.createDivision(createDivisionDto);
        //Mockito.verify(divisionServiceMock).connect();

    }




 */