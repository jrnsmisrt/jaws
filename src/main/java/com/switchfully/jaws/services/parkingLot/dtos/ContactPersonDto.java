package com.switchfully.jaws.services.parkingLot.dtos;

public record ContactPersonDto(String lastName,
                               String firstName,
                               String cellphoneNumber,
                               String homePhoneNumber,
                               String emailAddress,
                               AddressDto address) {
}
