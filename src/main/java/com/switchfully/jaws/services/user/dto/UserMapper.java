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

    public Address toAddress(CreateAddressDto addressDto) {
        return new Address.AddressBuilder()
                .withCity(addressDto.city())
                .withCountry(addressDto.country())
                .withStreet(addressDto.street())
                .withStreetNumber(addressDto.streetNumber())
                .withZipCode(addressDto.zipCode())
                .build();
    }

    public ContactInformation toContactInformation(ContactInformationDto contactInformationDto) {
        return new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber(contactInformationDto.cellPhoneNumber())
                .withEmailAddress(contactInformationDto.emailAddress())
                .withHomePhoneNumber(contactInformationDto.homePhoneNumber())
                .build();
    }

    public AddressDto toAddressDto(Address address) {
        return new AddressDto(address.getId(), address.getStreet(), address.getStreetNumber(), address.getCity(), address.getCountry(), address.getZipCode());
    }

    public ContactInformationDto toContactInformationDto(ContactInformation contactInformation){
        return new ContactInformationDto(contactInformation.getCellphoneNumber(), contactInformation.getHomePhoneNumber(), contactInformation.getEmailAddress());
    }

}
