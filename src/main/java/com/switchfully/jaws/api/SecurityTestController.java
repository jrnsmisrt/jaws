package com.switchfully.jaws.api;

import com.switchfully.jaws.services.security.KeycloakService;
import com.switchfully.jaws.services.security.Role;
import com.switchfully.jaws.services.security.dto.KeycloakUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class SecurityTestController {

    private final KeycloakService keycloakService;

    @Autowired
    public SecurityTestController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    void test() {
        KeycloakUserDTO user = new KeycloakUserDTO("Léonie", "Emil", Role.MANAGER);
        keycloakService.addUser(user);
    }

}
