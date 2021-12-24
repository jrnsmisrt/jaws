package com.switchfully.jaws.repositories;

import com.switchfully.jaws.domain.Division;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@ActiveProfiles("test")
public class DivisionRepositoryTest {
    private Division division;
    private Division dummyDivision;
    private Division dummyDivisionTwo;
    private Division subDivision;

    @Autowired
    private DivisionRepository divisionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void setup() {
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

        dummyDivisionTwo = new Division.DivisionBuilder()
                .withName("Olé")
                .withDirectorFullName("Lalalala")
                .withOriginalName("Jack Sparrow")
                .build();

        subDivision = new Division.DivisionBuilder()
                .withName("Sub")
                .withDirectorFullName("sub")
                .withOriginalName("sub")
                .build();

    }

    @Test
    @DisplayName("check entity equality based on name")
    public void givenDivision_whenDivisionWithSameName_thenShouldBeEqual() {
        Assertions.assertEquals(division, dummyDivision);
        Assertions.assertNotEquals(division, dummyDivisionTwo);
    }


    @Test
    @DisplayName("check entity saved in repo has same id as entity created")
    public void givenDivision_whenSavedInRepository_thenReturnSameWithId() {
        divisionRepository.save(division);
        entityManager.flush();
        Division divisionInRepo = divisionRepository.getById(division.getId());
        Assertions.assertEquals(divisionInRepo, division);
    }

    @Test
    @DisplayName("check when entity as Json is null, that exception is thrown")
    public void givenNullValue_whenSavedInRepository_thenThrowException() {
        Executable method = () -> divisionRepository.save(null);
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, method, "Division cannot be null");
    }

    @Test
    @DisplayName("when saving subdivision, should be part of list subdivisions parentdivision")
    void givenDivision_shouldBePartOfSubdivisionsParentDivision() {
        division.addSubdivision(subDivision);
        divisionRepository.save(division);
        divisionRepository.save(subDivision);

        Assertions.assertTrue(division.getSubDivisions().contains(subDivision));
    }

    @Test
    @DisplayName("when saving subdivision, parentId should be id parentdivision")
    void givenDivision_subdivisionShouldContainCorrectId() {
        division.addSubdivision(subDivision);
        divisionRepository.save(division);
        divisionRepository.save(subDivision);

        Assertions.assertEquals(divisionRepository.findDivisionsBySubDivisionsIn(subDivision.getName()).get(), division.getId());
    }
}
