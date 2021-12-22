package com.switchfully.jaws.repositories;

import com.switchfully.jaws.domain.Division;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<Division, Long> {
}
