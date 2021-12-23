package com.switchfully.jaws.api;

import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class DivisionControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Value("${server.port}")
    private int port;

}
