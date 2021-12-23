package com.switchfully.jaws.domain.user;

import java.util.stream.Stream;

public enum MemberShipLevel {
    BRONZE("bronze",0, 0, 4),
    SILVER("silver",10, 0.20, 6),
    GOLD("gold",40, 0.30, 24);

    private double monthlyCostEuro;
    private double parkingSpotAllocationPricePerHourReduction;
    private int maximumAllowedAllocationTimeHours;
    private String memberShipLevelName;


    MemberShipLevel(String memberShipLevelName, double monthlyCostEuro, double parkingSpotAllocationPricePerHourReduction, int maximumAllowedAllocationTimeHours) {
        this.monthlyCostEuro = monthlyCostEuro;
        this.memberShipLevelName=memberShipLevelName;
        this.parkingSpotAllocationPricePerHourReduction = parkingSpotAllocationPricePerHourReduction;
        this.maximumAllowedAllocationTimeHours = maximumAllowedAllocationTimeHours;
    }

    public String getMemberShipLevelName() {
        return memberShipLevelName;
    }

    public static Stream<MemberShipLevel> getStreamOfLevels(){
        return Stream.of(MemberShipLevel.values());
    }
}
