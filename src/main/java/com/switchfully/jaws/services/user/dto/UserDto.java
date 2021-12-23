package com.switchfully.jaws.services.user.dto;

import com.switchfully.jaws.services.common.dto.AddressDto;
import com.switchfully.jaws.services.common.dto.ContactInformationDto;

import java.time.LocalDate;

public record UserDto(Long id, String firstName, String lastName, String licensePlate, AddressDto addressDto,
                      ContactInformationDto contactInformationDto, LocalDate registrationDate, String memberShipLevel) {
}
