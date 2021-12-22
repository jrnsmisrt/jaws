package com.switchfully.jaws.services.parkingLot.dtos;

public record CreateParkingLotDto(String name,
                                  String category,
                                  int maxCapacity,
                                  ContactPersonDto contactPerson,
                                  double pricePerHour,
                                  AddressDto address) {
}
