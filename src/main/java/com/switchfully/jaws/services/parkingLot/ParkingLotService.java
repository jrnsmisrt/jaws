package com.switchfully.jaws.services.parkingLot;

import com.switchfully.jaws.domain.parkingLot.ParkingLot;
import com.switchfully.jaws.repositories.ParkingLotRepository;
import com.switchfully.jaws.services.parkingLot.dtos.CreateParkingLotDto;
import com.switchfully.jaws.services.parkingLot.dtos.ParkingLotDto;
import com.switchfully.jaws.services.parkingLot.dtos.ParkingLotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;
    private final ParkingLotMapper parkingLotMapper;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingLotMapper parkingLotMapper) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingLotMapper = parkingLotMapper;
    }

    public ParkingLotDto createParkingLot(CreateParkingLotDto createParkingLotDto) {
        ParkingLot parkingLot = parkingLotMapper.mapCreateDtoToEntity(createParkingLotDto);
        parkingLot = parkingLotRepository.save(parkingLot);

        return parkingLotMapper.mapEntityToDto(parkingLot);
    }

}
