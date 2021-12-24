package com.switchfully.jaws.repositories;

import com.switchfully.jaws.domain.Name;
import com.switchfully.jaws.domain.common.Address;
import com.switchfully.jaws.domain.common.ContactInformation;
import com.switchfully.jaws.domain.parkingLot.Category;
import com.switchfully.jaws.domain.parkingLot.ContactPerson;
import com.switchfully.jaws.domain.parkingLot.ParkingLot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class ParkingLotRepositoryTest {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Test
    void save_givenValidEntity_thenFindByIdReturnsEntity() {
        ParkingLot expected = parkingLotRepository.save(createValidParkingLot());
        ParkingLot actual = parkingLotRepository.getById(expected.getId());

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenFindAll_thenItsValid(){
        List<ParkingLot> expected = parkingLotRepository.findAll();
        Assertions.assertThat(expected != null);
    }

    @Test
    void whenFindAll_thenReturnAllEntities(){
        ParkingLot parkingLot = createValidParkingLot();
        List<ParkingLot> expected = parkingLotRepository.findAll();
        Assertions.assertThat(expected.contains(parkingLot));
    }

    private ParkingLot createValidParkingLot() {
        ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber("0123456789")
                .withHomePhoneNumber("9876543210")
                .withEmailAddress("test@test.com")
                .build();
        Address contactPersonAddress = new Address.AddressBuilder()
                .withStreet("street")
                .withStreetNumber("street number")
                .withCountry("country")
                .withCity("city")
                .withZipCode(6666)
                .build();
        ContactPerson contactPerson = new ContactPerson(new Name("Lastname", "Firstname"), contactInformation, contactPersonAddress);

        Address parkingAddress = new Address.AddressBuilder()
                .withStreet("parking street")
                .withStreetNumber("parking street number")
                .withCity("parking city")
                .withCountry("parking country")
                .withZipCode(7777)
                .build();

        return new ParkingLot("Parking lot", Category.UNDERGROUND_BUILDING, 250, contactPerson, parkingAddress, 2.99);
    }




}