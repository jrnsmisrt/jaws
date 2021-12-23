package com.switchfully.jaws.services.user.dto;


import com.switchfully.jaws.services.common.dto.ContactInformationDto;
import com.switchfully.jaws.services.common.dto.CreateAddressDto;

public record CreateUserDto(String firstName, String lastName, String licensePlate, CreateAddressDto addressDto,
                            ContactInformationDto contactInformationDto, String memberShipLevel) {
}
