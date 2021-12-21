package com.switchfully.jaws.services.security.dto;

import com.switchfully.jaws.services.security.Role;

public record KeycloakUserDTO (String userName, String password, Role role){
}
