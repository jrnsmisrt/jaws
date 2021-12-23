package com.switchfully.jaws.api;

import com.switchfully.jaws.services.parkingLot.ParkingLotService;
import com.switchfully.jaws.services.parkingLot.dtos.CreateParkingLotDto;
import com.switchfully.jaws.services.parkingLot.dtos.ParkingLotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.*;


@RestController
@RequestMapping(path = "parking-lots", produces = APPLICATION_JSON_VALUE)
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @Autowired
    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLotDto createParkingLot(@RequestBody CreateParkingLotDto createParkingLotDto) {
        return parkingLotService.createParkingLot(createParkingLotDto);
    }

    // Method for testing createParkingLot(CreateParkingLotDto)
    public ParkingLotDto getParkingLotById(Long id) {
        return parkingLotService.getParkingLotById(id);
    }

}
