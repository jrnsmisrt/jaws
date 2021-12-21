package com.switchfully.jaws.services.user;

import com.switchfully.jaws.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    UserServiceTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setUpStockService() {
        userRepository = Mockito.mock(UserRepository.class);
    }

    @Test
    void AddingUserToDatabaseWillReturnCorrectUser() {

    }
}