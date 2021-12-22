package com.switchfully.jaws.repositories;

import com.switchfully.jaws.domain.Division;
import com.switchfully.jaws.domain.parkingLot.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
}
