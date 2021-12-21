package com.switchfully.jaws.repositories;


import com.switchfully.jaws.domain.Division;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
public class DivisionRepositoryTest {
    private Division division;
    private Division dummyDivision;

    @Autowired
    private DivisionRepository divisionRepository;

    @BeforeEach
    public void setup() {
        division = new Division.DivisionBuilder()
                .withName("Maxim")
                .withDirectorFullName("BossMan")
                .withOriginalName("Number One")
                .build();

        dummyDivision = new Division.DivisionBuilder()
                .withName("Maxim")
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
}
