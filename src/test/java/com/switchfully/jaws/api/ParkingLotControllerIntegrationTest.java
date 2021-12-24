package com.switchfully.jaws.api;

import com.switchfully.jaws.domain.parkingLot.Category;
import com.switchfully.jaws.services.common.dto.ContactInformationDto;
import com.switchfully.jaws.services.common.dto.CreateAddressDto;
import com.switchfully.jaws.services.parkingLot.dtos.CreateContactPersonDto;
import com.switchfully.jaws.services.parkingLot.dtos.CreateParkingLotDto;
import com.switchfully.jaws.services.parkingLot.dtos.ParkingLotDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class ParkingLotControllerIntegrationTest {

    @Autowired
    private ParkingLotController parkingLotController;

    @Test
    @WithMockUser(authorities = "CREATE_PARKING_LOT")
    void createParkingLot_givenCorrectDto_thenParkingLotIsAdded() {
        ParkingLotDto expected = parkingLotController.createParkingLot(createValidCreateParkingLotDto("name3"));
        ParkingLotDto actual = parkingLotController.getParkingLotById(expected.id());
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @WithMockUser(authorities = "CREATE_PARKING_LOT")
    void createParkingLot_givenIncorrectDtoWithNullPhoneNumbers_thenThrowsIllegalArgumentException() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> parkingLotController.createParkingLot(createCreateParkingLotDtoWithInvalidPhoneNumbers()));
    }

    @Test
    @WithMockUser(authorities = {"GET_PARKING_LOT_OVERVIEW", "CREATE_PARKING_LOT"})
    void givenIncorrectDtoWithNullPhoneNumbers_WhenGetAll_thenAllParkingLotsAreReturned(){
        CreateParkingLotDto createParkingLotDto = createValidCreateParkingLotDto("name5");
        ParkingLotDto expectedParkingLotDto = parkingLotController.createParkingLot(createParkingLotDto);
        List<ParkingLotDto> parkingLotDtoList = parkingLotController.getAllParkingLots();
        Assertions.assertThat(parkingLotDtoList.contains(expectedParkingLotDto));
    }

    private CreateParkingLotDto createValidCreateParkingLotDto(String name) {
        ContactInformationDto contactInformationDto = createValidContactInformationDto();
        CreateAddressDto createAddressDto = new CreateAddressDto("contact person street", "contact person street number", "contact person city", "contact person country", 6666);
        CreateContactPersonDto createContactPersonDto = new CreateContactPersonDto("Lastname", "Firstname", contactInformationDto, createAddressDto);
        CreateAddressDto parkingAddressDto = new CreateAddressDto("parking street", "parking street number", "parking city", "parking country", 7777);
        return new CreateParkingLotDto(name, Category.UNDERGROUND_BUILDING.name(), 260, 2.99, createContactPersonDto, parkingAddressDto);
    }

    private CreateParkingLotDto createCreateParkingLotDtoWithInvalidPhoneNumbers() {
        ContactInformationDto contactInformationDto = createInvalidContactInformationDto();
        CreateAddressDto createAddressDto = new CreateAddressDto("contact person street", "contact person street number", "contact person city", "contact person country", 6666);
        CreateContactPersonDto createContactPersonDto = new CreateContactPersonDto("Lastname", "Firstname", contactInformationDto, createAddressDto);
        CreateAddressDto parkingAddressDto = new CreateAddressDto("parking street", "parking street number", "parking city", "parking country", 7777);
        return new CreateParkingLotDto("Parking lot name2", Category.UNDERGROUND_BUILDING.name(), 260, 2.99, createContactPersonDto, parkingAddressDto);
    }

    private ContactInformationDto createValidContactInformationDto() {
        return new ContactInformationDto("0123456789", "987654321", "test@test.com");
    }

    private ContactInformationDto createInvalidContactInformationDto() {
        return new ContactInformationDto(null, null, "test@test.com");
    }

}