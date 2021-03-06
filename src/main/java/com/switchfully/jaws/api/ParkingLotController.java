package com.switchfully.jaws.api;

import com.switchfully.jaws.services.division.dtos.DivisionDto;
import com.switchfully.jaws.services.parkingLot.ParkingLotService;
import com.switchfully.jaws.services.parkingLot.dtos.CreateParkingLotDto;
import com.switchfully.jaws.services.parkingLot.dtos.ParkingLotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PreAuthorize("hasAuthority('CREATE_PARKING_LOT')")
    public ParkingLotDto createParkingLot(@RequestBody CreateParkingLotDto createParkingLotDto) {
        return parkingLotService.createParkingLot(createParkingLotDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('GET_PARKING_LOT_OVERVIEW')")
    public List<ParkingLotDto> getAllParkingLots(){
        return parkingLotService.getAllParkingLots();
    }

    // Method for testing createParkingLot(CreateParkingLotDto)
    public ParkingLotDto getParkingLotById(Long id) {
        return parkingLotService.getParkingLotById(id);
    }

}
