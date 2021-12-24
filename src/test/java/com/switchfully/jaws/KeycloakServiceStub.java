package com.switchfully.jaws;

import com.switchfully.jaws.services.security.KeycloakService;
import com.switchfully.jaws.services.security.dto.KeycloakUserDTO;
import org.springframework.stereotype.Service;

@Service
public class KeycloakServiceStub implements KeycloakService {
    @Override
    public void addUser(KeycloakUserDTO keycloakUserDTO) {

    }
}
