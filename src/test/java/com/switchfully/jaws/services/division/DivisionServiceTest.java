package com.switchfully.jaws.services.division;

import com.switchfully.jaws.domain.Division;
import com.switchfully.jaws.repositories.DivisionRepository;
import com.switchfully.jaws.services.division.dtos.CreateDivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class DivisionServiceTest {

    private Division division;
    private Division dummyDivision;
    private CreateDivisionDto createDivisionDto;

    private DivisionRepository divisionRepository;
    private DivisionService divisionServiceMock;

    private DivisionMapper divisionMapperMock;

    @BeforeEach
    void setUp() {
        divisionRepository = mock(DivisionRepository.class);
        divisionMapperMock = mock(DivisionMapper.class);
        divisionServiceMock = new DivisionService(divisionMapperMock, divisionRepository);

        division = new Division.DivisionBuilder()
                .withName("Monkey")
                .withDirectorFullName("BossMan")
                .withOriginalName("Number One")
                .build();

        dummyDivision = new Division.DivisionBuilder()
                .withName("Monkey")
                .withDirectorFullName("Lalalala")
                .withOriginalName("Jack Sparrow")
                .build();

        createDivisionDto = new CreateDivisionDto("Monkey", "Bossman", "Number One");
    }

    @Test
    void givenDivisionRepository_whenDivisionSavedToDb_willReturnDivision() {
        //Mockito.when(divisionRepository.save(division)).thenReturn(division);
        divisionRepository.save(division);
        DivisionDto divisionDto = divisionServiceMock.createDivision(createDivisionDto);
        Assertions.assertThat(divisionDto.name().equalsIgnoreCase(division.getName()));

    }

    @Test
    void givenDivisionService_whenDivisionSavedToDb_thenVerifyRepositorySavesEntity() {
        divisionServiceMock.createDivision(createDivisionDto);
        Mockito.verify(divisionRepository).save(divisionMapperMock.mapDivisionDtoToDivision(createDivisionDto));
    }

    @Test
    void givenDivisionRepository_whenDivisionsRetrievedFromDb_thenReturnAllDivisions(){
        Mockito.when(divisionRepository.findAll()).thenReturn(List.of());
    }

    @Test
    void givenDivisionService_whenDivisionsRetrievedFromDb_thenVerifyRepositoryReturnsEntities(){
        divisionServiceMock.getAllDivisions();
        Mockito.verify(divisionRepository).findAll();
    }
}