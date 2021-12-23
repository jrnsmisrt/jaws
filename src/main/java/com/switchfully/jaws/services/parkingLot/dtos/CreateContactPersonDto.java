package com.switchfully.jaws.services.parkingLot.dtos;

import com.switchfully.jaws.services.common.dto.ContactInformationDto;
import com.switchfully.jaws.services.common.dto.CreateAddressDto;

public record CreateContactPersonDto(String lastName,
                                     String firstName,
                                     ContactInformationDto contactInformation,
                                     CreateAddressDto address) {
}
