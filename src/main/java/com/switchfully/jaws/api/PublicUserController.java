package com.switchfully.jaws.api;

import com.switchfully.jaws.services.security.KeycloakService;
import com.switchfully.jaws.services.security.Role;
import com.switchfully.jaws.services.security.dto.KeycloakUserDTO;
import com.switchfully.jaws.services.user.UserService;
import com.switchfully.jaws.services.user.dto.CreateUserDto;
import com.switchfully.jaws.services.user.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/public/users")
public class PublicUserController {
    private final UserService userService;
    private final KeycloakService keycloakServiceImpl;

    public PublicUserController(UserService userService, KeycloakService keycloakServiceImpl) {
        this.userService = userService;
        this.keycloakServiceImpl = keycloakServiceImpl;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerMember(@RequestBody CreateUserDto createUserDto) {
        UserDto result = userService.addUser(createUserDto);
        keycloakServiceImpl.addUser(new KeycloakUserDTO(result.contactInformationDto().emailAddress(), "password", Role.MEMBER));
        return result;
    }
}
