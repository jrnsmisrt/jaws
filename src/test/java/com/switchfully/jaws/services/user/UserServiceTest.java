package com.switchfully.jaws.services.user;

import com.switchfully.jaws.domain.user.Address;
import com.switchfully.jaws.domain.user.ContactInformation;
import com.switchfully.jaws.domain.user.User;
import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.common.dto.AddressMapper;
import com.switchfully.jaws.services.common.dto.ContactInformationDto;
import com.switchfully.jaws.services.common.dto.ContactInformationMapper;
import com.switchfully.jaws.services.common.dto.CreateAddressDto;
import com.switchfully.jaws.services.user.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.List;

class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;
    private UserMapper userMapper = new UserMapper(new AddressMapper(), new ContactInformationMapper());

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

    private ContactInformation contactInformation2 = new ContactInformation.ContactInfoBuilder()
            .withCellPhoneNumber("0458235")
            .withEmailAddress("Jeroen2.smissaert@outlook.com")
            .withHomePhoneNumber("5405465")
            .build();

    private User testUser = new User.UserBuilder()
            .withAddress(address)
            .withContactInformation(contactInformation)
            .withFirstName("Jeroen")
            .withLastName("Smissaert")
            .withLicensePlate("B2020")
            .build();

    private User testUser2 = new User.UserBuilder()
            .withAddress(address)
            .withContactInformation(contactInformation2)
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
        userService = new UserService(userRepository, userMapper);
        userMapper = Mockito.mock(UserMapper.class);
        userService = new UserService(userRepository, userMapper);
    }

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

    @Test
    void verifyUserRepositoryFindAllIsCalled(){
        userService.getAllMembersOverview();
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void whenGetAllMembersOverviewIsCalled_StringShouldContainSpecifiedUser(){
        List<User> expectedUsers = List.of(testUser,testUser2);
        Mockito.when(userRepository.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userService.getAllUser();
        Assertions.assertThat(actualUsers).isEqualTo(expectedUsers);
    }
}