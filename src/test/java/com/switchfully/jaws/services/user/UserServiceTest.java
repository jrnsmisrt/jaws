package com.switchfully.jaws.services.user;

import com.switchfully.jaws.domain.Address;
import com.switchfully.jaws.domain.ContactInformation;
import com.switchfully.jaws.domain.User;
import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.user.dto.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;
    private UserMapper userMapper;

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

    @Test
    void verifyUserRepositorySaveIsExecuted() {
        userService.addUser(testUser);
        Mockito.verify(userRepository).save(testUser);
    }

    @Test
    void verifyUserRepositoryFindAllIsCalled(){
        userService.getAllMembersOverview();
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void whenGetAllMembersOverviewIsCalled_StringShouldContainSpecifiedUser(){
        userService.addUser(testUser);
        Assertions.assertThat(userService.getAllMembersOverview()).contains(testUser.getFirstName());

    }
}