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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.mockito.Mockito.mock;


//@SpringBootTest
//@AutoConfigureTestDatabase
@DataJpaTest
class DivisionServiceTest {

    private Division division;
    private Division dummyDivision;
    private CreateDivisionDto createDivisionDto;

    private DivisionRepository divisionRepository;
    private DivisionService divisionServiceMock;
    private DivisionMapper divisionMapperMock;


    private DivisionService divisionService;
    private DivisionMapper divisionMapper;

    @Autowired
    private DivisionRepository repository;



    @BeforeEach
    void setUp() {
        divisionRepository = mock(DivisionRepository.class);
        divisionMapperMock = mock(DivisionMapper.class);
        divisionServiceMock = new DivisionService(divisionMapperMock, divisionRepository);

        divisionMapper = new DivisionMapper();

        divisionService = new DivisionService(divisionMapper, repository);


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
        CreateDivisionDto createDivisionDto2 = new CreateDivisionDto("Div", "Bossman", "Number One");

        DivisionDto expected = divisionService.createDivision(createDivisionDto2);

        Division actual = repository.getById(expected.id());

        Assertions.assertThat(actual.getName()).isEqualTo(expected.name());

    }


    @Test
    void givenDivisionService_whenDivisionSavedToDb_thenVerifyRepositorySavesEntity() {
        divisionServiceMock.createDivision(createDivisionDto);
        Mockito.verify(divisionRepository).save(divisionMapperMock.mapDivisionDtoToDivision(createDivisionDto));
    }

    @Test
    void givenDivisionRepository_whenDivisionsRetrievedFromDb_thenReturnAllDivisions(){
        Assertions.assertThat(divisionService.getAllDivisions() != null);
    }

    @Test
    void givenDivisionService_whenDivisionsRetrievedFromDb_thenVerifyRepositoryReturnsEntities(){
        divisionServiceMock.getAllDivisions();
        Mockito.verify(divisionRepository).findAll();
    }
}