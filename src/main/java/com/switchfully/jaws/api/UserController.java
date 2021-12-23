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
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final KeycloakService keycloakService;

    public UserController(UserService userService, KeycloakService keycloakService) {
        this.userService = userService;
        this.keycloakService = keycloakService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerMember(@RequestBody CreateUserDto createUserDto) {
        UserDto result = userService.addUser(createUserDto);
        keycloakService.addUser(new KeycloakUserDTO(result.contactInformationDto().emailAddress(), "password", Role.MEMBER));
        return result;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('GET_MEMBER_OVERVIEW')")
    public String getAllMembers() {
        return userService.getAllMembersOverview();
    }
}
