package com.switchfully.jaws.repositories;

import com.switchfully.jaws.domain.Division;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

//@SpringJUnitConfig(DatasourceConfig.class)
class DivisionRepositoryTest {


    private DivisionRepository divisionRepository;

    public DivisionRepositoryTest(DivisionRepository divisionRepository){
        this.divisionRepository = divisionRepository;
    }

    @Test
    void save() {

        Assertions.assertThat(true).isFalse();
    }






}