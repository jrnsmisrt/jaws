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
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
class DivisionServiceTest {

    private Division division;
    private Division dummyDivision;
    private CreateDivisionDto createDivisionDto;

    private DivisionRepository divisionRepository;
    private DivisionService divisionServiceMock;

    @BeforeEach
    void setUp() {
        divisionRepository = mock(DivisionRepository.class);
        divisionServiceMock = new DivisionService(new DivisionMapper(divisionRepository), divisionRepository);

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

        createDivisionDto = new CreateDivisionDto.CreateDivisionDtoBuilder()
                .withName("Monkey")
                .withParentDivisionId(null)
                .withOriginalName("original name")
                .withDirectorFullName("COucoucouc")
                .build();
    }

    @Test
    void givenDivisionRepository_whenDivisionSavedToDb_willReturnDivision() {
        Mockito.when(divisionRepository.save(division)).thenReturn(division);
        DivisionDto divisionDto = divisionServiceMock.createDivision(createDivisionDto);
        Assertions.assertThat(divisionDto.getName().equalsIgnoreCase(division.getName()));
    }

    @Test
    void givenDivisionService_whenDivisionSavedToDb_thenVerifyRepositorySavesEntity() {
        divisionServiceMock.createDivision(createDivisionDto);
        Mockito.verify(divisionRepository).save(new DivisionMapper(divisionRepository).mapDivisionDtoToDivision(createDivisionDto));
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