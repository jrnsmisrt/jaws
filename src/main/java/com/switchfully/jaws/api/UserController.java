package com.switchfully.jaws.api;

import com.switchfully.jaws.services.user.UserService;
import com.switchfully.jaws.services.user.dto.CreateUserDto;
import com.switchfully.jaws.services.user.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerMember(@RequestBody CreateUserDto createUserDto) {
        return userService.addUser(createUserDto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getAllMembers() {
        return userService.getAllMembersOverview();
    }
}
