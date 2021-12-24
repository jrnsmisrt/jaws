package com.switchfully.jaws.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.jaws.services.division.dtos.CreateDivisionDto;
import com.switchfully.jaws.services.division.dtos.DivisionDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DivisionControllerTest {
    private CreateDivisionDto createDivisionDto;
    private CreateDivisionDto createDivisionDtoAlt;
    private CreateDivisionDto createDivisionDtoSameName;
    private CreateDivisionDto createDivisionDifferentNameDto;

    @Value("${server.port}")
    private int port;

    @Autowired
    private MockMvc mockMvc;

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
    @WithMockUser(authorities = "CREATE_DIVISION")
    void givenCreateDivisionDto_doFieldsDivisionDtoGetFilledIn() {

        DivisionDto divisionDto = null;
        try {
            ResultActions resultActions = mockMvc.perform(post("/divisions").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsBytes(createDivisionDtoAlt))).andExpect(status().isCreated()).andDo(print());
            String stringResponse = resultActions.andReturn().getResponse().getContentAsString();
            divisionDto = new ObjectMapper().readValue(stringResponse, DivisionDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Assertions.assertEquals(divisionDto.getName(),createDivisionDtoAlt.getName());
        Assertions.assertEquals(divisionDto.getOriginalName(),createDivisionDtoAlt.getOriginalName());
        Assertions.assertEquals(divisionDto.getDirectorFullName(),createDivisionDtoAlt.getDirectorFullName());
        Assertions.assertEquals(divisionDto.getParentDivisionId(), null);
    }

    @Test
    @WithMockUser(authorities = "CREATE_DIVISION")
    @DisplayName("when creating divisions with same name, get back BAD request")
    void givenCreateDivisionDtoWithSameName_thenBadRequest() {

        try {
            mockMvc.perform(post("/divisions").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsBytes(createDivisionDto)));
            mockMvc.perform(post("/divisions").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsBytes(createDivisionDtoSameName))).andExpect(status().isBadRequest()).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @WithMockUser(authorities = "CREATE_DIVISION")
    @DisplayName("when creating divisions with same name, get back CREATED request")
    void givenCreateDivisionDtoWithDifferentName_thenCreatedRequest() {
        try {
            mockMvc.perform(post("/divisions").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsBytes(createDivisionDto)));
            mockMvc.perform(post("/divisions").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsBytes(createDivisionDifferentNameDto))).andExpect(status().isCreated()).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
