package com.switchfully.jaws.services.security;

import java.util.List;

import static com.switchfully.jaws.services.security.Feature.*;

public enum Role {
    MANAGER("manager", CREATE_PARKING_LOT, GET_DIVISION_OVERVIEW, CREATE_DIVISION, GET_PARKING_LOT_OVERVIEW, GET_PARKING_ALLOCATION_OVERVIEW, GET_MEMBER_OVERVIEW),
    MEMBER("member", SELECT_MEMBERSHIP, CREATE_ALLOCATION_PARKING_SPOT, STOP_ALLOCATION_PARKING_SPOT);

    private final String label;
    private final List<Feature> featureList;

    Role(String label, Feature... featureList) {
        this.label = label;
        this.featureList = List.of(featureList);
    }

    public String getLabel() {
        return label;
    }

    public List<Feature> getFeatures() {
        return featureList;
    }
}
