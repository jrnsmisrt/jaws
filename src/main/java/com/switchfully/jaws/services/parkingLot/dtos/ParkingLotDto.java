package com.switchfully.jaws.services.parkingLot.dtos;

import com.switchfully.jaws.domain.Address;
import com.switchfully.jaws.domain.parkingLot.Category;
import com.switchfully.jaws.domain.parkingLot.ContactPerson;

public record ParkingLotDto (long id, String name, Category category, int maxCapacity, ContactPerson contactPerson, Address address, double pricePerHour) {
}
