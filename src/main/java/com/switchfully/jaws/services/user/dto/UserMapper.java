package com.switchfully.jaws.services.user.dto;

import com.switchfully.jaws.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getLicensePlate(), user.getAddress(), user.getContactInformation(), user.getRegistrationDate());
    }

    public User toUser(CreateUserDto createUserDto) {
        return new User.UserBuilder()
                .withAddress(createUserDto.address())
                .withContactInformation(createUserDto.contactInformation())
                .withFirstName(createUserDto.firstName())
                .withLastName(createUserDto.lastName())
                .withLicensePlate(createUserDto.licensePlate())
                .build();
    }
}
