package com.switchfully.jaws.services.user.dto;

import com.switchfully.jaws.domain.common.Address;
import com.switchfully.jaws.domain.common.ContactInformation;
import com.switchfully.jaws.domain.user.User;
import com.switchfully.jaws.services.common.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final AddressMapper addressMapper;
    private final ContactInformationMapper contactInformationMapper;

    @Autowired
    public UserMapper(AddressMapper addressMapper, ContactInformationMapper contactInformationMapper) {
        this.addressMapper = addressMapper;
        this.contactInformationMapper = contactInformationMapper;
    }

    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getLicensePlate(), addressMapper.mapEntityToDto(user.getAddress()), contactInformationMapper.mapEntityToDto(user.getContactInformation()), user.getRegistrationDate());
    }

    public User toUser(CreateUserDto createUserDto) {
        return new User.UserBuilder()
                .withAddress(addressMapper.mapCreateDtoToEntity(createUserDto.addressDto()))
                .withContactInformation(contactInformationMapper.mapDtoToEntity(createUserDto.contactInformationDto()))
                .withFirstName(createUserDto.firstName())
                .withLastName(createUserDto.lastName())
                .withLicensePlate(createUserDto.licensePlate())
                .build();
    }
}
