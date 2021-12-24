package com.switchfully.jaws.exceptions;

import com.switchfully.jaws.domain.user.MemberShipLevel;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InvalidMemberShipLevelInputException  extends InvalidParameterException {

    public InvalidMemberShipLevelInputException(String memberShipLevel) {
        super("The membership level you provided is not correct, please provide a correct one: " +
                MemberShipLevel.getStreamOfLevels().toList());

    }


}
