package com.switchfully.jaws.api;

import com.switchfully.jaws.services.division.dtos.CreateDivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class DivisionControllerTest {
    private CreateDivisionDto createDivisionDto;
    private CreateDivisionDto createDivisionDtoAlt;
    private CreateDivisionDto createDivisionDtoSameName;
    private CreateDivisionDto createDivisionDifferentNameDto;

    @Value("${server.port}")
    private int port;


    @BeforeEach
    public void setup() {
        createDivisionDto = new CreateDivisionDto.CreateDivisionDtoBuilder()
                .withName("Monkey")
                .withDirectorFullName("Boss Monkey")
                .withParentDivisionId(null)
                .withOriginalName("Popo")
                .build();

        createDivisionDtoAlt = new CreateDivisionDto.CreateDivisionDtoBuilder()
                .withName("Lola")
                .withDirectorFullName("Boss Monkey")
                .withParentDivisionId(null)
                .withOriginalName("Popo")
                .build();

        createDivisionDifferentNameDto = new CreateDivisionDto.CreateDivisionDtoBuilder()
                .withName("Umpa Lumpa")
                .withDirectorFullName("Boss Monkey")
                .withParentDivisionId(null)
                .withOriginalName("Popo")
                .build();

        createDivisionDtoSameName = new CreateDivisionDto.CreateDivisionDtoBuilder()
                .withName("Monkey")
                .withDirectorFullName("Manager Monkey")
                .withParentDivisionId(null)
                .withOriginalName("Polo")
                .build();
    }

    private Response postCreateDivision(CreateDivisionDto createDivisionDto) {
        return RestAssured
                .given()
                .body(createDivisionDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .post("/divisions");
    }



    @Test
    @DisplayName("checking to see if the Dto gets filled in correctly")
    void givenCreateDivisionDto_doFieldsDivisionDtoGetFilledIn() {

        DivisionDto divisionDto = postCreateDivision(createDivisionDtoAlt)
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(DivisionDto.class);

        Assertions.assertEquals(divisionDto.getName(),createDivisionDtoAlt.getName());
        Assertions.assertEquals(divisionDto.getOriginalName(),createDivisionDtoAlt.getOriginalName());
        Assertions.assertEquals(divisionDto.getDirectorFullName(),createDivisionDtoAlt.getDirectorFullName());
        Assertions.assertEquals(divisionDto.getParentDivisionId(), Optional.empty());
    }

    @Test
    @DisplayName("when creating divisions with same name, get back BAD request")
    void givenCreateDivisionDtoWithSameName_thenBadRequest() {
        postCreateDivision(createDivisionDto);

        postCreateDivision(createDivisionDtoSameName)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("when creating divisions with same name, get back CREATED request")
    void givenCreateDivisionDtoWithDifferentName_thenCreatedRequest() {
        postCreateDivision(createDivisionDto);

        postCreateDivision(createDivisionDifferentNameDto)
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }


}
