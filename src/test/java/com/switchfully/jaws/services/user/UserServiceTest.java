package com.switchfully.jaws.services.user;

import com.switchfully.jaws.domain.Address;
import com.switchfully.jaws.domain.ContactInformation;
import com.switchfully.jaws.domain.User;
import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.division.dtos.DivisionMapper;
import com.switchfully.jaws.services.user.dto.*;
import com.switchfully.jaws.services.user.dto.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.mock;
@DataJpaTest
@ActiveProfiles("test")
class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;
    private UserMapper userMapper = new UserMapper();


    private final Address address = new Address.AddressBuilder()
            .withCity("Gent")
            .withCountry("Belgium")
            .withStreet("husestraat")
            .withStreetNumber("22")
            .withZipCode(9000)
            .build();

    private final ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
            .withCellPhoneNumber("0458235")
            .withEmailAddress("Jeroen.smissaert@outlook.com")
            .withHomePhoneNumber("5405465")
            .build();

    private final User testUser = new User.UserBuilder()
            .withAddress(address)
            .withContactInformation(contactInformation)
            .withFirstName("Jeroen")
            .withLastName("Smissaert")
            .withLicensePlate("B2020")
            .build();

    private final CreateAddressDto createAddressDto = new CreateAddressDto(address.getStreet(), address.getStreetNumber(), address.getCity(), address.getCountry(), address.getZipCode());
    private final ContactInformationDto contactInformationDto = new ContactInformationDto(contactInformation.getCellphoneNumber(), contactInformation.getHomePhoneNumber(), contactInformation.getEmailAddress());
    private final CreateUserDto createUserDto = new CreateUserDto(testUser.getFirstName(), testUser.getLastName(), testUser.getLicensePlate(), createAddressDto,contactInformationDto);


    @BeforeEach
    void setUpStockService() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository, userMapper);

    }

    @Test
    void addingUserToDatabaseWillReturnCorrectUser() {
        Mockito.when(userRepository.save(testUser)).thenReturn(testUser);
        Assertions.assertThat(userService.addUser(testUser)).isEqualTo(testUser);
    }

//    @Test
//    void addingUserToDatabaseWillReturnCorrectUser() {
//        Mockito.when(userRepository.save(testUser)).thenReturn(testUser);
//        Assertions.assertThat(userService.addUser(createUserDto)).isEqualTo(userMapper.toUserDto(testUser));
//    }
//
//    @Test
//    void verifyUserRepositorySaveIsExecuted() {
//        Mockito.when(userRepository.save(testUser)).thenReturn(testUser);
//        userService.addUser(createUserDto);
//        Mockito.verify(userRepository).save(testUser);
//    }
//    @Test
//    void verifyUserRepositorySaveIsExecuted() {
//        userService.addUser(testUser);
//        Mockito.verify(userRepository).save(testUser);
//    }
//
//    @Test
//    void verifyUserRepositoryFindAllIsCalled(){
//        userService.getAllMembersOverview();
//        Mockito.verify(userRepository).findAll();
//    }
//
//    @Test
//    void whenGetAllMembersOverviewIsCalled_StringShouldContainSpecifiedUser(){
//        userService.addUser(testUser);
//        Assertions.assertThat(userService.getAllMembersOverview()).contains(testUser.getFirstName());
//
//    }
}