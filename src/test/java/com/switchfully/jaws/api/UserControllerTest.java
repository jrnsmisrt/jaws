package com.switchfully.jaws.api;

import com.switchfully.jaws.JawsApplication;
import com.switchfully.jaws.domain.ContactInformation;
import com.switchfully.jaws.services.user.dto.CreateAddressDto;
import com.switchfully.jaws.services.user.dto.CreateUserDto;
import com.switchfully.jaws.services.user.dto.UserDto;
import com.switchfully.jaws.services.user.dto.UserMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@Transactional
class UserControllerTest {

    private final UserMapper userMapper = new UserMapper();

    @BeforeEach
    void setUp() {

    }

    @Test
    void name() {
        CreateAddressDto createAddressDto = new CreateAddressDto("husestraat","22","Gent","Belgium",9000);
        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaert@outlook.com")
                .withHomePhoneNumber("5405465")
                .build();

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, userMapper.toContactInformationDto(contactInformation));


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

        Assertions.assertThat(userDto.addressDto().city()).isEqualTo(createUserDto.addressDto().city());
        Assertions.assertThat(userDto.addressDto().country()).isEqualTo(createUserDto.addressDto().country());
        Assertions.assertThat(userDto.addressDto().street()).isEqualTo(createUserDto.addressDto().street());
        Assertions.assertThat(userDto.addressDto().streetNumber()).isEqualTo(createUserDto.addressDto().streetNumber());
        Assertions.assertThat(userDto.addressDto().zipCode()).isEqualTo(createUserDto.addressDto().zipCode());
        Assertions.assertThat(userDto.firstName()).isEqualTo(createUserDto.firstName());
        Assertions.assertThat(userDto.lastName()).isEqualTo(createUserDto.lastName());
        Assertions.assertThat(userDto.licensePlate()).isEqualTo(createUserDto.licensePlate());
        Assertions.assertThat(userDto.contactInformationDto()).isEqualTo(createUserDto.contactInformationDto());
        Assertions.assertThat(userDto.registrationDate()).isEqualTo(LocalDate.now());
    }
}