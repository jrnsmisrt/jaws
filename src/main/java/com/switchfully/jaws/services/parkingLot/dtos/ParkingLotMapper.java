package com.switchfully.jaws.services.parkingLot.dtos;

import com.switchfully.jaws.domain.Address;
import com.switchfully.jaws.domain.ContactInformation;
import com.switchfully.jaws.domain.Name;
import com.switchfully.jaws.domain.parkingLot.Category;
import com.switchfully.jaws.domain.parkingLot.ContactPerson;
import com.switchfully.jaws.domain.parkingLot.ParkingLot;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {

    public ParkingLot mapCreateDtoToEntity(CreateParkingLotDto createParkingLotDto) {
        Name contactPersonName = new Name(createParkingLotDto.contactPerson().lastName(), createParkingLotDto.contactPerson().firstName());
        ContactInformation contactPersonContactInformation =
                new ContactInformation.ContactInfoBuilder()
                        .withCellPhoneNumber(createParkingLotDto.contactPerson().cellphoneNumber())
                        .withHomePhoneNumber(createParkingLotDto.contactPerson().homePhoneNumber())
                        .withEmailAddress(createParkingLotDto.contactPerson().emailAddress())
                        .build();
        Address contactPersonAddress = new Address.AddressBuilder()
                .withStreet(createParkingLotDto.contactPerson().address().street())
                .withStreetNumber(createParkingLotDto.contactPerson().address().streetNumber())
                .withCity(createParkingLotDto.contactPerson().address().city())
                .withCountry(createParkingLotDto.contactPerson().address().country())
                .withZipCode(createParkingLotDto.contactPerson().address().zipCode())
                .build();

        Address parkingLotAddress = new Address.AddressBuilder()
                .withStreet(createParkingLotDto.address().street())
                .withStreetNumber(createParkingLotDto.address().streetNumber())
                .withCity(createParkingLotDto.address().city())
                .withCountry(createParkingLotDto.address().country())
                .withZipCode(createParkingLotDto.address().zipCode())
                .build();

        return new ParkingLot(createParkingLotDto.name(),
                Category.valueOf(createParkingLotDto.category()),
                createParkingLotDto.maxCapacity(),
                new ContactPerson(contactPersonName, contactPersonContactInformation, contactPersonAddress),
                parkingLotAddress,
                createParkingLotDto.pricePerHour());
    }

    public ParkingLotDto mapEntityToDto(ParkingLot parkingLot) {
        ContactPersonDto contactPersonDto = new ContactPersonDto(parkingLot.getContactPerson().getName().getLastName(),
                parkingLot.getContactPerson().getName().getFirstName(),
                parkingLot.getContactPerson().getContactInformation().getCellphoneNumber(),
                parkingLot.getContactPerson().getContactInformation().getHomePhoneNumber(),
                parkingLot.getContactPerson().getContactInformation().getEmailAddress(),
                mapAddressToDto(parkingLot.getContactPerson().getAddress()));



        return new ParkingLotDto(parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getCategory(),
                parkingLot.getMaxCapacity(),
                contactPersonDto,
                mapAddressToDto(parkingLot.getAddress()),
                parkingLot.getPricePerHour());
    }

    public AddressDto mapAddressToDto(Address address) {
        return new AddressDto(address.getStreet(), address.getStreetNumber(), address.getCity(), address.getCountry(), address.getZipCode());
    }
}
