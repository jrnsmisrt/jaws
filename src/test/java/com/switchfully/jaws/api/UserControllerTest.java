package com.switchfully.jaws.api;

import com.switchfully.jaws.domain.Address;
import com.switchfully.jaws.domain.ContactInformation;
import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.user.dto.CreateUserDto;
import com.switchfully.jaws.services.user.dto.UserDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {


    @BeforeEach
    void setUp() {

    }
    @Autowired
    private UserRepository userRepository;

    @Test
    void name() {
        Address address = new Address.AddressBuilder()
                .withCity("Gent")
                .withCountry("Belgium")
                .withStreet("husestraat")
                .withStreetNumber("22")
                .withZipCode(9000)
                .build();

        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaert@outlook.com")
                .withHomePhoneNumber("5405465")
                .build();

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", address, contactInformation, LocalDate.now());


        UserDto userDto = RestAssured
                .given()
                .body(createUserDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(8080)
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(UserDto.class);

        Assertions.assertThat(userDto.address()).isEqualTo(createUserDto.address());
        Assertions.assertThat(userDto.firstName()).isEqualTo(createUserDto.firstName());
        Assertions.assertThat(userDto.lastName()).isEqualTo(createUserDto.lastName());
        Assertions.assertThat(userDto.licensePlate()).isEqualTo(createUserDto.licensePlate());
        Assertions.assertThat(userDto.contactInformation()).isEqualTo(createUserDto.contactInformation());
        Assertions.assertThat(userDto.registrationDate()).isEqualTo(createUserDto.registrationDate());

    }
}