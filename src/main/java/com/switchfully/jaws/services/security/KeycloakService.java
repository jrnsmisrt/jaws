package com.switchfully.jaws.services.security;

import com.switchfully.jaws.services.security.dto.KeycloakUserDTO;

public interface KeycloakService {
    void addUser(KeycloakUserDTO keycloakUserDTO);
}
