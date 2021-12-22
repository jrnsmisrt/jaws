package com.switchfully.jaws.services.common.dto;

import com.switchfully.jaws.domain.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address mapCreateDtoToEntity(CreateAddressDto addressDto) {
        return new Address.AddressBuilder()
                .withCity(addressDto.city())
                .withCountry(addressDto.country())
                .withStreet(addressDto.street())
                .withStreetNumber(addressDto.streetNumber())
                .withZipCode(addressDto.zipCode())
                .build();
    }

    public AddressDto mapEntityToDto(Address address) {
        return new AddressDto(address.getId(), address.getStreet(), address.getStreetNumber(), address.getCity(), address.getCountry(), address.getZipCode());
    }

}
