package com.switchfully.jaws.services.user.dto;

import com.switchfully.jaws.domain.Address;
import com.switchfully.jaws.domain.ContactInformation;

import java.time.LocalDate;

public record CreateUserDto(String firstName, String lastName, String licensPlate, Address address,
                      ContactInformation contactInformation, LocalDate registrationDate) {
}
