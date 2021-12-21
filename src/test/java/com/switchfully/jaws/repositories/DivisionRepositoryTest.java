package com.switchfully.jaws.repositories;


import com.switchfully.jaws.domain.Division;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
public class DivisionRepositoryTest {
    private Division division;
    private Division dummyDivision;

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
    }

    @Test
    public void givenDivision_whenDivisionWithSameName_thenShouldBeEqual() {
        Assertions.assertEquals(division.getName(), dummyDivision.getName());
    }

    @Test
    public void givenDivision_whenSavedInRepository_thenReturnSizeOne() {
        divisionRepository.save(division);
        int amount = divisionRepository.findAll().size();
        Assertions.assertEquals(1, amount);
    }

    @Test
    public void givenDivision_whenSavedInRepository_thenReturnSameWithId() {
        divisionRepository.save(division);
        entityManager.flush();
        Division divisionInRepo = divisionRepository.getById(division.getId());
        Assertions.assertEquals(divisionInRepo, division);
    }
}
