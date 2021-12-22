package com.switchfully.jaws.services.parkingLot.dtos;

import com.switchfully.jaws.domain.parkingLot.Category;

public record ParkingLotDto (long id, String name, Category category, int maxCapacity, ContactPersonDto contactPerson, AddressDto address, double pricePerHour) {
}
