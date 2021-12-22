package com.switchfully.jaws.services.user.dto;


public record CreateUserDto(String firstName, String lastName, String licensePlate, CreateAddressDto addressDto,
                            ContactInformationDto contactInformationDto) {
}
