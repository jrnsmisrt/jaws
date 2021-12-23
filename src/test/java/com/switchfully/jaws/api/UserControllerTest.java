package com.switchfully.jaws.api;

import com.switchfully.jaws.domain.common.ContactInformation;
import com.switchfully.jaws.domain.user.User;
import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.user.UserService;
import com.switchfully.jaws.services.common.dto.AddressMapper;
import com.switchfully.jaws.services.common.dto.ContactInformationDto;
import com.switchfully.jaws.services.common.dto.ContactInformationMapper;
import com.switchfully.jaws.services.common.dto.CreateAddressDto;
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
    private UserService userService;
    private final UserMapper userMapper = new UserMapper(new AddressMapper(), new ContactInformationMapper());
    private final UserRepository userRepository;

    @Value("${server.port}")
    private int port;

    @Autowired
    UserControllerTest(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
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

        ContactInformationDto contactInformationDto = new ContactInformationMapper().mapEntityToDto(contactInformation);

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, contactInformationDto,"gold");

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
        Assertions.assertThat(userDto.memberShipLevel()).isEqualTo((createUserDto.memberShipLevel()));
        Assertions.assertThat(userDto.registrationDate()).isEqualTo(LocalDate.now());
    }

    @Test
    void givenCorrectInformation_RegisterMemberWorksWithMemberShipLevelNull() {
        CreateAddressDto createAddressDto = new CreateAddressDto("husestraat", "22", "Gent", "Belgium", 9000);
        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaertcorrectInfo@outlook.com")
                .withHomePhoneNumber("5405465")
                .build();

        ContactInformationDto contactInformationDto = new ContactInformationMapper().mapEntityToDto(contactInformation);

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, contactInformationDto,null);

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

        Assertions.assertThat(userDto.memberShipLevel()).isEqualTo("bronze");

    }

    @Test
    void givenCorrectInformation_RegisterMemberWorksWithMemberShipLevelSilver() {
        CreateAddressDto createAddressDto = new CreateAddressDto("husestraat", "22", "Gent", "Belgium", 9000);
        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaertcorrectInfo@outlook.com")
                .withHomePhoneNumber("5405465")
                .build();

        ContactInformationDto contactInformationDto = new ContactInformationMapper().mapEntityToDto(contactInformation);

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, contactInformationDto,"silver");

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

        Assertions.assertThat(userDto.memberShipLevel()).isEqualTo(createUserDto.memberShipLevel());

    }

    @Test
    void givenCorrectInformation_OnlyEmaiDifferentUserinfoRepository_RegisterMemberWorks() {
        CreateAddressDto createAddressDto2 = new CreateAddressDto("husestraat", "22", "Gent", "Belgium", 9000);
        ContactInformation contactInformation2 = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen.smissaer2t@outlook.com") //diff email
                .withHomePhoneNumber("5405465")
                .build();

        ContactInformationDto contactInformationDto2 = new ContactInformationMapper().mapEntityToDto(contactInformation2);

        CreateUserDto createUserDto2 = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto2, contactInformationDto2,null);


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

        ContactInformationDto contactInformationDto = new ContactInformationMapper().mapEntityToDto(contactInformation);

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto2, contactInformationDto,null);


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

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, userMapper.toContactInformationDto(contactInformation), "null");


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
    void givenEmailNull_RegisterMemberWorks_GivesException() {
        CreateAddressDto createAddressDto = new CreateAddressDto("husestraat", "null", "Gent", "Belgium", 9000);
        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress(null)
                .withHomePhoneNumber("5405465")
                .build();

        ContactInformationDto contactInformationDto = new ContactInformationMapper().mapEntityToDto(contactInformation);

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, contactInformationDto,null);

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

        ContactInformationDto contactInformationDto = new ContactInformationMapper().mapEntityToDto(contactInformation);

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, contactInformationDto,null);

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

        ContactInformationDto contactInformationDto = new ContactInformationMapper().mapEntityToDto(contactInformation);

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", null, contactInformationDto,null);


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

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, userMapper.toContactInformationDto(contactInformation),null);


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
    void givenUserDto_WhenGetAllMembersOverviewIsCalled_OverviewContainsSaidUserDto() {
        CreateAddressDto createAddressDto = new CreateAddressDto("husestraat", "22", "Gent", "Belgium", 9000);
        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0458235")
                .withEmailAddress("Jeroen@smissaert.com")
                .withHomePhoneNumber("5405465")
                .build();

        CreateUserDto createUserDto = new CreateUserDto("Jeroen", "Smissaert", "B2051", createAddressDto, userMapper.toContactInformationDto(contactInformation),null);

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

        String listUserDto = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when().port(port)
                .get("/users")
                .then().assertThat()
                .statusCode(HttpStatus.OK.value()).extract().asString();

        Assertions.assertThat(listUserDto).contains(userDto.contactInformationDto().emailAddress());
    }


}