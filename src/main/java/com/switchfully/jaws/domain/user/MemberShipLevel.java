package com.switchfully.jaws.domain.user;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum MemberShipLevel {
    BRONZE(0, 0, 4),
    SILVER(10, 0.20, 6 ),
    GOLD(40, 0.30, 24);

    @Enumerated(EnumType.STRING)
    private double MonthlyCostEuro;
    private double parkingSpotAllocationPricePerHourReduction;
    private int MaximumAllowedAllocationTimeHours;

    MemberShipLevel() {

    }

    MemberShipLevel(double monthlyCostEuro, double parkingSpotAllocationPricePerHourReduction, int maximumAllowedAllocationTimeHours) {
        MonthlyCostEuro = monthlyCostEuro;
        this.parkingSpotAllocationPricePerHourReduction = parkingSpotAllocationPricePerHourReduction;
        MaximumAllowedAllocationTimeHours = maximumAllowedAllocationTimeHours;
    }
}
