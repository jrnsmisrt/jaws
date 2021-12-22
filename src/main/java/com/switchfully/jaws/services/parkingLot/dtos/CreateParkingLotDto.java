package com.switchfully.jaws.services.parkingLot.dtos;

import com.switchfully.jaws.services.common.dto.CreateAddressDto;

public record CreateParkingLotDto(String name,
                                  String category,
                                  int maxCapacity,
                                  double pricePerHour,
                                  CreateContactPersonDto contactPerson,
                                  CreateAddressDto address) {
}
