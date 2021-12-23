package com.switchfully.jaws.services.parkingLot.dtos;

import com.switchfully.jaws.domain.common.Address;
import com.switchfully.jaws.domain.parkingLot.Category;
import com.switchfully.jaws.domain.parkingLot.ContactPerson;
import com.switchfully.jaws.domain.parkingLot.ParkingLot;
import com.switchfully.jaws.services.common.dto.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {

    private final AddressMapper addressMapper;
    private final ContactPersonMapper contactPersonMapper;

    @Autowired
    public ParkingLotMapper(AddressMapper addressMapper, ContactPersonMapper contactPersonMapper) {
        this.addressMapper = addressMapper;
        this.contactPersonMapper = contactPersonMapper;
    }

    public ParkingLot mapCreateDtoToEntity(CreateParkingLotDto createParkingLotDto) {
        ContactPerson contactPerson = contactPersonMapper.mapCreateDtoToEntity(createParkingLotDto.contactPerson());
        Address parkingLotAddress = addressMapper.mapCreateDtoToEntity(createParkingLotDto.address());

        return new ParkingLot(createParkingLotDto.name(),
                Category.valueOf(createParkingLotDto.category()),
                createParkingLotDto.maxCapacity(),
                contactPerson,
                parkingLotAddress,
                createParkingLotDto.pricePerHour());
    }

    public ParkingLotDto mapEntityToDto(ParkingLot parkingLot) {
        CreateContactPersonDto contactPersonDto = contactPersonMapper.mapEntityToCreateDto(parkingLot.getContactPerson());

        return new ParkingLotDto(parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getCategory(),
                parkingLot.getMaxCapacity(),
                contactPersonDto,
                addressMapper.mapEntityToDto(parkingLot.getAddress()),
                parkingLot.getPricePerHour());
    }
}
