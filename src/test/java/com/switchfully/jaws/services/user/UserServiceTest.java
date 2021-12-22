package com.switchfully.jaws.services.user;

import com.switchfully.jaws.domain.user.Address;
import com.switchfully.jaws.domain.user.ContactInformation;
import com.switchfully.jaws.domain.user.User;
import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.user.dto.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;
    private UserMapper userMapper;

    private Address address = new Address.AddressBuilder()
            .withCity("Gent")
            .withCountry("Belgium")
            .withStreet("husestraat")
            .withStreetNumber("22")
            .withZipCode(9000)
            .build();

    private ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
            .withCellPhoneNumber("0458235")
            .withEmailAddress("Jeroen.smissaert@outlook.com")
            .withHomePhoneNumber("5405465")
            .build();

    private User testUser = new User.UserBuilder()
            .withAddress(address)
            .withContactInformation(contactInformation)
            .withFirstName("Jeroen")
            .withLastName("Smissaert")
            .withLicensePlate("B2020")
            .build();

    private final CreateAddressDto createAddressDto = new CreateAddressDto(address.getStreet(), address.getStreetNumber(), address.getCity(), address.getCountry(), address.getZipCode());
    private final ContactInformationDto contactInformationDto = new ContactInformationDto(contactInformation.getCellphoneNumber(), contactInformation.getHomePhoneNumber(), contactInformation.getEmailAddress());
    private final CreateUserDto createUserDto = new CreateUserDto(testUser.getFirstName(), testUser.getLastName(), testUser.getLicensePlate(), createAddressDto, contactInformationDto);


    @BeforeEach
    void setUpStockService() {
        userRepository = Mockito.mock(UserRepository.class);
        userMapper = Mockito.mock(UserMapper.class);
        userService = new UserService(userRepository, userMapper);
    }

//    @Test
//    void addingUserToDatabaseWillReturnCorrectUser() {
//        Mockito.when(userRepository.save(testUser)).thenReturn(testUser);
//        Mockito.when(userMapper.toUser(createUserDto)).thenReturn(testUser);
////        Mockito.when(userMapper.toUserDto(testUser)).thenReturn();
//
//        Assertions.assertThat(userService.addUser(createUserDto)).isEqualTo(testUser);
//    }

    @Test
    void mockTest_WhenSavingUser_CheckIfSaveFunctionOfRepositoryIsCalled(){
        userService.addUser(createUserDto);
        Mockito.verify(userRepository).save(userMapper.toUser(createUserDto));
    }

    @Test
    void subtest() {
        User user = userMapper.toUser(createUserDto);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        UserDto receivedUserDto = userService.addUser(createUserDto);
        Assertions.assertThat(receivedUserDto).isEqualTo(userMapper.toUserDto(userMapper.toUser(createUserDto)));
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
//        Mockito.when(userRepository.findAll()).thenReturn();
//        userService.addUser(createUserDto);
//        Assertions.assertThat(userService.getAllMembersOverview()).contains(testUser.getFirstName());
//
//    }
}