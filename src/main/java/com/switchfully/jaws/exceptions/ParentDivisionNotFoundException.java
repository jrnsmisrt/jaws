package com.switchfully.jaws.exceptions;

public class ParentDivisionNotFoundException extends RuntimeException{
    public ParentDivisionNotFoundException() {
        this("Could not find parent division");
    };

    public ParentDivisionNotFoundException(String message) {
        super(message);
    }
}
