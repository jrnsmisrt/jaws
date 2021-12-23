package com.switchfully.jaws.services.user.dto;


import java.time.LocalDate;

public record UserDto(Long id, String firstName, String lastName, String licensePlate, AddressDto addressDto,
                      ContactInformationDto contactInformationDto, LocalDate registrationDate, String memberShipLevel) {
}
