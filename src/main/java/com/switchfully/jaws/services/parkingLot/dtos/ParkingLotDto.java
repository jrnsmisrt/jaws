package com.switchfully.jaws.services.parkingLot.dtos;

import com.switchfully.jaws.domain.parkingLot.Category;
import com.switchfully.jaws.services.common.dto.AddressDto;

public record ParkingLotDto (long id, String name, Category category, int maxCapacity, CreateContactPersonDto contactPerson, AddressDto address, double pricePerHour) {
}
