package com.switchfully.jaws.services.user.dto;

import com.switchfully.jaws.domain.user.Address;
import com.switchfully.jaws.domain.user.ContactInformation;
import com.switchfully.jaws.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getLicensePlate(), toAddressDto(user.getAddress()), toContactInformationDto(user.getContactInformation()), user.getRegistrationDate());
    }

    public User toUser(CreateUserDto createUserDto) {
        return new User.UserBuilder()
                .withAddress(toAddress(createUserDto.addressDto()))
                .withContactInformation(toContactInformation(createUserDto.contactInformationDto()))
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
