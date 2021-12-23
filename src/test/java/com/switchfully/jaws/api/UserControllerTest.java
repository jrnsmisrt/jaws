package com.switchfully.jaws.api;

import com.switchfully.jaws.domain.user.ContactInformation;
import com.switchfully.jaws.domain.user.User;
import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.user.dto.CreateAddressDto;
import com.switchfully.jaws.services.user.dto.CreateUserDto;
import com.switchfully.jaws.services.user.dto.UserDto;
import com.switchfully.jaws.services.user.dto.UserMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class UserControllerTest {

    private UserController userController;
    private final UserMapper userMapper = new UserMapper();
    private final UserRepository userRepository;

    @Value("${server.port}")
    private int port;

    @Autowired
    UserControllerTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setUp() {

    }


    @Test
    void givenCorrectInformation_RegisterMemberWorks() {
        CreateAddressDto createAddressDto = new CreateAddressDto("husestraat", "22", "Gent", "Belgium", 9000);
        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaertcorrectInfo@outlook.com")
                .withHomePhoneNumber("5405465")
                .build();

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, userMapper.toContactInformationDto(contactInformation));

        UserDto userDto = RestAssured
                .given()
                .body(createUserDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
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

    @Test
    void givenCorrectInformation_OnlyEmaiDifferentUserinfoRepository_RegisterMemberWorks() {
        CreateAddressDto createAddressDto2 = new CreateAddressDto("husestraat", "22", "Gent", "Belgium", 9000);
        ContactInformation contactInformation2 = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaer2t@outlook.com") //diff email
                .withHomePhoneNumber("5405465")
                .build();

        CreateUserDto createUserDto2 = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto2, userMapper.toContactInformationDto(contactInformation2));


        User userAlreadyInRepository = new User.UserBuilder()
                .withAddress(userMapper.toAddress(createAddressDto2))
                .withContactInformation(contactInformation2)
                .withFirstName(createUserDto2.firstName())
                .withLastName(createUserDto2.lastName())
                .withLicensePlate(createUserDto2.licensePlate())
                .build();

        userRepository.save(userAlreadyInRepository);

        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaertOnlyEmailDiff@outlook.com")
                .withHomePhoneNumber("5405465")
                .build();

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto2, userMapper.toContactInformationDto(contactInformation));


        UserDto userDto = RestAssured
                .given()
                .body(createUserDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
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

    @Test
    void givenStreetNumberNull_RegisterMemberWorks_GivesException() {
        CreateAddressDto createAddressDto = new CreateAddressDto("husestraat", null, "Gent", "Belgium", 9000);
        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaert@outlook.com")
                .withHomePhoneNumber("5405465")
                .build();

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, userMapper.toContactInformationDto(contactInformation));


        RestAssured
                .given()
                .body(createUserDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenAlreadyExistingUserAssInput_RegisterMemberWorks_GivesException() {
        CreateAddressDto createAddressDto = new CreateAddressDto("husestraat", "2", "Gent", "Belgium", 9000);
        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaert@outlook.com")
                .withHomePhoneNumber("5405465")
                .build();

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, userMapper.toContactInformationDto(contactInformation));

        userRepository.save(userMapper.toUser(createUserDto));

        RestAssured
                .given()
                .body(createUserDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    void givenAddressNull_RegisterMemberWorks_GivesException() {
        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaert@outlook.com")
                .withHomePhoneNumber("5405465")
                .build();

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", null, userMapper.toContactInformationDto(contactInformation));


        RestAssured
                .given()
                .body(createUserDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenBadEmailAddress_RegisterMemberWorks_GivesException() {
        CreateAddressDto createAddressDto = new CreateAddressDto("husestraat", null, "Gent", "Belgium", 9000);
        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaertoutlook.com")
                .withHomePhoneNumber("5405465")
                .build();

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, userMapper.toContactInformationDto(contactInformation));


        RestAssured
                .given()
                .body(createUserDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenUserDto_WhenGetAllMembersOverviewIsCalled_OverviewContainsSaidUserDto(){
//        CreateAddressDto createAddressDto = new CreateAddressDto("husestraat", "22", "Gent", "Belgium", 9000);
//        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
//                .withCellPhoneNumber("0458235")
//                .withEmailAddress("Jeroen.smissaert@outlook.com")
//                .withHomePhoneNumber("5405465")
//                .build();
//
//        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, userMapper.toContactInformationDto(contactInformation));
//
//        UserDto userDto = RestAssured
//                .given()
//                .body(createUserDto)
//                .accept(ContentType.JSON)
//                .contentType(ContentType.JSON)
//                .when()
//                .port(port)
//                .post("/users")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.CREATED.value())
//                .extract()
//                .as(UserDto.class);
//
//        Assertions.assertThat(userController.getAllMembers()).contains("Jeroen");
    }
}