package com.switchfully.jaws.domain.user;

import java.util.stream.Stream;

public enum MemberShipLevel {
    BRONZE("bronze",0, 0, 4),
    SILVER("silver",10, 0.20, 6),
    GOLD("gold",40, 0.30, 24);

    private double MonthlyCostEuro;
    private double parkingSpotAllocationPricePerHourReduction;
    private int MaximumAllowedAllocationTimeHours;
    private String memberShipLevelName;

    MemberShipLevel() {

    }

    MemberShipLevel(String memberShipLevelName, double monthlyCostEuro, double parkingSpotAllocationPricePerHourReduction, int maximumAllowedAllocationTimeHours) {
        MonthlyCostEuro = monthlyCostEuro;
        this.memberShipLevelName=memberShipLevelName;
        this.parkingSpotAllocationPricePerHourReduction = parkingSpotAllocationPricePerHourReduction;
        MaximumAllowedAllocationTimeHours = maximumAllowedAllocationTimeHours;
    }

    public String getMemberShipLevelName() {
        return memberShipLevelName;
    }

    public static Stream<MemberShipLevel> getStreamOfLevels(){
        return Stream.of(MemberShipLevel.values());
    }
}
